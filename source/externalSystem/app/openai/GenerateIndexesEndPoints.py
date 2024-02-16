from flask import Blueprint, request as flaskRequest, jsonify
from http import HTTPStatus

from .._global.logger import CustomLogger
from .._global.logger import CustomLoggerType
from .._global.proxy.OpenAIProxyService import generateIndexesByUsingGPT

bp = Blueprint(__name__.split(".")[-1], __name__, url_prefix="/openai")


class GenerateIndexesReqDto:
    def __init__(self, request) :
        self.__jsonData = request.get_json()
        self.__query:str = self.__jsonData["query"] or ""

    def __str__(self) :
        return "<{} query: {}>".format(self.__class__.__name__, 
                    self.__query)


    @property
    def query(self) -> str :
        return self.__query

class GenerateIndexesResDto:
    def __init__(self, indexNames:list[str]) :
        self.__indexNames:list[str] = indexNames

    def __str__(self) :
        return "<{} indexNames: {}>".format(self.__class__.__name__,
                    ", ".join(self.__indexNames))


    @property
    def indexNames(self) -> list[str] :
        return self.__indexNames
    
    
    def json(self) -> str :
        return jsonify({
            "indexNames": self.__indexNames
        })


# 주어진 쿼리에 해당하는 인덱스들을 생성하고, 관련 정보를 반환하기 위해서
@bp.route("/generateIndexes", methods=("PUT",))
def generateIndexes() -> GenerateIndexesReqDto :
    try :

        reqDto:GenerateIndexesReqDto = GenerateIndexesReqDto(flaskRequest)
        CustomLogger.debugObj(CustomLoggerType.ENTER, reqDto)


        INDEXES:list[str] = generateIndexesByUsingGPT(reqDto.query)


        resDto:GenerateIndexesResDto = GenerateIndexesResDto(INDEXES)
        CustomLogger.debugObj(CustomLoggerType.EXIT, resDto)
        return (resDto.json(), HTTPStatus.OK)

    except Exception as e :
        jsonData = flaskRequest.get_json()
        query = jsonData["query"] or ""
        CustomLogger.error(e, "", "<query: {}>".format(query))
        return ("", HTTPStatus.BAD_REQUEST)