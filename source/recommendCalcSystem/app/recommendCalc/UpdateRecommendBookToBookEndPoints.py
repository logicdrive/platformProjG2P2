from flask import Blueprint, jsonify
from http import HTTPStatus

from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity

from .._global.logger import CustomLogger
from .._global.logger import CustomLoggerType
from .._global.proxy.CollectedDataProxy import CollectedDataProxy

bp = Blueprint(__name__.split(".")[-1], __name__, url_prefix="/recommendCalc")


class UpdateRecommendBookToBookResDto:
    def __init__(self, updatedBookCount:int) :
        self.__updatedBookCount:int = updatedBookCount

    def __str__(self) :
        return "<{} updatedBookCount: {}>".format(self.__class__.__name__,
                    self.__updatedBookCount)


    @property
    def updatedBookCount(self) -> int :
        return self.__updatedBookCount
    
    
    def json(self) -> str :
        return jsonify({
            "updatedBookCount": self.__updatedBookCount
        })


# 특정 책에 대한 추천할 책 목록들을 새로 갱신하기 위해서
@bp.route("/updateRecommendBookToBook", methods=("PUT",))
def updateRecommendBookToBook() -> UpdateRecommendBookToBookResDto :
    try :

        CustomLogger.debug(CustomLoggerType.ENTER)
        

        TOP_N = 3

        bookTags, reversedBookTagsIndices = getBookTags()

        countMatrix = CountVectorizer(stop_words="english").fit_transform(getSoups(bookTags))
        cosineSim2 = cosine_similarity(countMatrix, countMatrix)

        for bookTag in bookTags:
            recommendBooks = getRecommendBooks(bookTag["bookId"], bookTags, reversedBookTagsIndices, cosineSim2, TOP_N)
            CollectedDataProxy.recreateRecommendBookToBooks(
                bookTag["bookId"], 
                recommendBooks,
                list(range(1, 1+TOP_N))
            )


        resDto = UpdateRecommendBookToBookResDto(len(bookTags))
        CustomLogger.debugObj(CustomLoggerType.EXIT, resDto)
        return (resDto.json(), HTTPStatus.OK)

    except Exception as e :
        CustomLogger.error(e)
        return ("", HTTPStatus.BAD_REQUEST)


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

def getSoups(bookTags):
    return [" ".join(bookTag["tags"]) for bookTag in bookTags]

def getRecommendBooks(bookId, bookTags, reversedBookTagsIndices, cosineSim2, topN=3):
    scores = cosineSim2[reversedBookTagsIndices[bookId]]
    enumScores = list(enumerate(scores))
    enumScores = sorted(enumScores, key=lambda x: x[1], reverse=True)
    return [bookTags[i]["bookId"] for i, _ in enumScores[1:1+topN]]