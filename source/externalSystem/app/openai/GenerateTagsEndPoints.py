from flask import Blueprint, request as flaskRequest, jsonify
from http import HTTPStatus

from .._global.logger import CustomLogger
from .._global.logger import CustomLoggerType
from .._global.proxy.OpenAIProxyService import generateTagsByUsingGPT

bp = Blueprint(__name__.split(".")[-1], __name__, url_prefix="/openai")


class GenerateTagsReqDto:
    def __init__(self, request) :
        self.__jsonData = request.get_json()
        self.__query:str = self.__jsonData["query"] or ""

    def __str__(self) :
        return "<{} query: {}>".format(self.__class__.__name__, 
                    self.__query)


    @property
    def query(self) -> str :
        return self.__query

class GenerateTagsResDto:
    def __init__(self, tagNames:list[str]) :
        self.__tagNames:list[str] = tagNames

    def __str__(self) :
        return "<{} tagNames: {}>".format(self.__class__.__name__,
                    ", ".join(self.__tagNames))


    @property
    def tagNames(self) -> list[str] :
        return self.__tagNames
    
    
    def json(self) -> str :
        return jsonify({
            "tagNames": self.__tagNames
        })


# 주어진 쿼리에 해당하는 태그들을 생성하고, 관련 정보를 반환하기 위해서
@bp.route("/generateTags", methods=("PUT",))
def generateTags() -> GenerateTagsReqDto :
    try :

        reqDto:GenerateTagsReqDto = GenerateTagsReqDto(flaskRequest)
        CustomLogger.debugObj(CustomLoggerType.ENTER, reqDto)


        TAGS:list[str] = generateTagsByUsingGPT(reqDto.query)


        resDto:GenerateTagsResDto = GenerateTagsResDto(TAGS)
        CustomLogger.debugObj(CustomLoggerType.EXIT, resDto)
        return (resDto.json(), HTTPStatus.OK)

    except Exception as e :
        jsonData = flaskRequest.get_json()
        query = jsonData["query"] or ""
        CustomLogger.error(e, "", "<query: {}>".format(query))
        return ("", HTTPStatus.BAD_REQUEST)