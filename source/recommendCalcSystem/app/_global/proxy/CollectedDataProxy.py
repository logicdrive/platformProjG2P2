from ..util.HttpUtil import HttpUtil, HttpMethod
import os


COLLECTED_DATA_HOST = os.environ.get("COLLECTED_DATA_HOST")
COLLECTED_DATA_PORT = os.environ.get("COLLECTED_DATA_PORT")

COLLECTED_DATA_URL = f"http://{COLLECTED_DATA_HOST}:{COLLECTED_DATA_PORT}"
collectedDataPath = lambda path: f"{COLLECTED_DATA_URL}/{path}"


class CollectedDataProxy:
    @staticmethod
    def sanityCheck():
        HttpUtil.jsonRequest(HttpMethod.GET, collectedDataPath("sanityCheck"))
    

    @staticmethod
    def getUsers():
        return HttpUtil.jsonRequest(HttpMethod.GET, collectedDataPath("users"))
    

    @staticmethod
    def getBooks():
        return HttpUtil.jsonRequest(HttpMethod.GET, collectedDataPath("books"))
    
    @staticmethod
    def getBooksByCreaterId(createrId):
        return HttpUtil.jsonRequest(HttpMethod.GET, collectedDataPath(f"books/search/findByCreaterId?createrId={createrId}"))

    
    @staticmethod
    def getLikeHistoriesByUserId(userId):
        return HttpUtil.jsonRequest(HttpMethod.GET, collectedDataPath(f"likeHistories/search/findByUserId?userId={userId}"))

    
    @staticmethod
    def getBookShelfsByCreaterId(createrId):
        return HttpUtil.jsonRequest(HttpMethod.GET, collectedDataPath(f"bookShelfs/search/findByCreaterIdOrderByTitle?createrId={createrId}"))

    @staticmethod
    def getBookShelfBooksByBookShelfId(bookShelfId):
        return HttpUtil.jsonRequest(HttpMethod.GET, collectedDataPath(f"bookShelfBooks/search/findByBookShelfIdOrderByCreatedDateDesc?bookShelfId={bookShelfId}"))

    
    @staticmethod
    def getTagsByBookId(bookId):
        return HttpUtil.jsonRequest(HttpMethod.GET, collectedDataPath(f"tags/search/findByBookIdOrderByName?bookId={bookId}"))


    @staticmethod
    def recreateRecommendBookToUsers(userId, recommendedBookIds, priorities):
        return HttpUtil.jsonRequest(HttpMethod.PUT, collectedDataPath("recommendBookToUsers/recreate"), {
            "userId": userId,
            "recommendedBookIds": recommendedBookIds,
            "priorities": priorities
        })
    
    @staticmethod
    def recreateRecommendBookToBooks(bookId, recommendedBookIds, priorities):
        return HttpUtil.jsonRequest(HttpMethod.PUT, collectedDataPath("recommenedBookToBooks/recreate"), {
            "bookId": bookId,
            "recommendedBookIds": recommendedBookIds,
            "priorities": priorities
        })