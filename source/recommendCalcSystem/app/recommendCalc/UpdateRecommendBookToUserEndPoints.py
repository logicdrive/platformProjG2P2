from flask import Blueprint, jsonify
from http import HTTPStatus

from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity

from .._global.logger import CustomLogger
from .._global.logger import CustomLoggerType
from .._global.proxy.CollectedDataProxy import CollectedDataProxy

bp = Blueprint(__name__.split(".")[-1], __name__, url_prefix="/recommendCalc")


class UpdateRecommendBookToUserResDto:
    def __init__(self, updatedUserCount:int) :
        self.__updatedUserCount:int = updatedUserCount

    def __str__(self) :
        return "<{} updatedUserCount: {}>".format(self.__class__.__name__,
                    self.__updatedUserCount)


    @property
    def updatedUserCount(self) -> int :
        return self.__updatedUserCount
    
    
    def json(self) -> str :
        return jsonify({
            "updatedUserCount": self.__updatedUserCount
        })


# 특정 유저에게 추천할 책 목록들을 새로 갱신하기 위해서
@bp.route("/updateRecommendBookToUser", methods=("PUT",))
def updateRecommendBookToUser() -> UpdateRecommendBookToUserResDto :
    try :

        CustomLogger.debug(CustomLoggerType.ENTER)


        TOP_N = 3

        userPreferTags, reversedUserPreferTagsIndices, relatedBookIds = getUserPreferTags()
        bookTags, reversedBookTagsIndices = getBookTags()
        userPrderAndBookSoups = getUserPreferTagsSoups(userPreferTags) + getBookTagsSoups(bookTags)

        countMatrix = CountVectorizer(stop_words="english").fit_transform(userPrderAndBookSoups)
        cosineSim2 = cosine_similarity(countMatrix, countMatrix)
    
        for userPreferTag in userPreferTags:
            recommendBooks = getRecommendBooks(userPreferTag["userId"], 
                                userPreferTags, reversedUserPreferTagsIndices, relatedBookIds,
                                bookTags, reversedBookTagsIndices, cosineSim2, TOP_N)
            CollectedDataProxy.recreateRecommendBookToUsers(
                userPreferTag["userId"], 
                recommendBooks,
                list(range(1, 1+TOP_N))
            )


        resDto = UpdateRecommendBookToUserResDto(len(userPreferTags))
        CustomLogger.debugObj(CustomLoggerType.EXIT, resDto)
        return (resDto.json(), HTTPStatus.OK)

    except Exception as e :
        CustomLogger.error(e)
        return ("", HTTPStatus.BAD_REQUEST)


def getUserPreferTags():
    userPreferTags = []
    relatedBookIds = []
    for userId in [user["userId"] for user in CollectedDataProxy.getUsers()["_embedded"]["users"]] :
        userCreatedBookIds = [book["bookId"] for book in CollectedDataProxy.getBooksByCreaterId(userId)["_embedded"]["books"]]
        likeHistoryBookIds = [likeHistory["bookId"] for likeHistory in CollectedDataProxy.getLikeHistoriesByUserId(userId)["_embedded"]["likeHistories"]]
        
        userCreatedBookShelfIds = [bookShelf["bookShelfId"] for bookShelf in CollectedDataProxy.getBookShelfsByCreaterId(userId)["_embedded"]["bookShelfs"]]
        bookShelfBookBookIds = []
        for bookShelfId in userCreatedBookShelfIds :
            bookShelfBookBookIds.extend([bookShelfBook["bookId"]  for bookShelfBook in CollectedDataProxy.getBookShelfBooksByBookShelfId(bookShelfId)["_embedded"]["bookShelfBooks"]])

        relatedBookIds = list(set(userCreatedBookIds + likeHistoryBookIds + bookShelfBookBookIds))


        relatedBookTags = []
        for bookId in relatedBookIds:
            relatedBookTags.extend([tag["name"].upper().replace(" ", "") for tag in CollectedDataProxy.getTagsByBookId(bookId)["_embedded"]["tags"]])

        userPreferTags.append({
            "userId": userId,
            "relatedBookTags": relatedBookTags
        })
    
    reversedUserPreferTagsIndices = {}
    for i, userPreferTag in enumerate(userPreferTags):
        reversedUserPreferTagsIndices[userPreferTag["userId"]] = i
    
    return userPreferTags, reversedUserPreferTagsIndices, relatedBookIds

def getUserPreferTagsSoups(userPreferTags):
    return [" ".join(userPreferTag["relatedBookTags"]) for userPreferTag in userPreferTags]


def getBookTags():
    bookTags = []
    for bookId in [book["bookId"] for book in CollectedDataProxy.getBooks()["_embedded"]["books"]] :
        bookTags.append({
            "bookId": bookId,
            "tags": [tag["name"].upper().replace(" ", "") for tag in CollectedDataProxy.getTagsByBookId(bookId)["_embedded"]["tags"]]
        })
    
    reversedBookTagsIndices = {}
    for i, bookTag in enumerate(bookTags):
        reversedBookTagsIndices[bookTag["bookId"]] = i
    
    return bookTags, reversedBookTagsIndices

def getBookTagsSoups(bookTags):
    return [" ".join(bookTag["tags"]) for bookTag in bookTags]


def getRecommendBooks(userId, userPreferTags, reversedUserPreferTagsIndices, relatedBookIds, 
                      bookTags, reversedBookTagsIndices, cosineSim2, topN=3):
    
    scores = cosineSim2[reversedUserPreferTagsIndices[userId]]
    bookEnumScores = list(enumerate(scores))[len(userPreferTags):]

    bookIdsToFilter = [reversedBookTagsIndices[relatedBookId]+len(userPreferTags) for relatedBookId in relatedBookIds]
    filterdBookEnumScores = [bookEnumScore for bookEnumScore in bookEnumScores if bookEnumScore[0] not in bookIdsToFilter]
    filterdBookEnumScores = sorted(filterdBookEnumScores, key=lambda x: x[1], reverse=True)

    return [bookTags[i-len(userPreferTags)]["bookId"] for i, _ in filterdBookEnumScores[:topN]]