from flask import Blueprint, request as flaskRequest, jsonify
from http import HTTPStatus

from .._global.logger import CustomLogger
from .._global.logger import CustomLoggerType
from .._global.proxy.OpenAIProxyService import generateContentByUsingGPT

bp = Blueprint(__name__.split(".")[-1], __name__, url_prefix="/openai")


class GenerateContentReqDto:
    def __init__(self, request) :
        self.__jsonData = request.get_json()
        self.__query:str = self.__jsonData["query"] or ""

    def __str__(self) :
        return "<{} query: {}>".format(self.__class__.__name__, 
                    self.__query)


    @property
    def query(self) -> str :
        return self.__query

class GenerateContentResDto:
    def __init__(self, content:str) :
        self.__content:str = content

    def __str__(self) :
        return "<{} content: {}>".format(self.__class__.__name__,
                    self.__content)


    @property
    def content(self) -> str :
        return self.__content
    
    
    def json(self) -> str :
        return jsonify({
            "content": self.__content
        })


# 주어진 쿼리에 해당하는 컨텐츠를 생성하고, 관련 정보를 반환하기 위해서
@bp.route("/generateContent", methods=("PUT",))
def generateContent() -> GenerateContentReqDto :
    try :

        reqDto:GenerateContentReqDto = GenerateContentReqDto(flaskRequest)
        CustomLogger.debugObj(CustomLoggerType.ENTER, reqDto)


        content:str = generateContentByUsingGPT(reqDto.query)


        resDto:GenerateContentResDto = GenerateContentResDto(content)
        CustomLogger.debugObj(CustomLoggerType.EXIT, resDto)
        return (resDto.json(), HTTPStatus.OK)

    except Exception as e :
        jsonData = flaskRequest.get_json()
        query = jsonData["query"] or ""
        CustomLogger.error(e, "", "<query: {}>".format(query))
        return ("", HTTPStatus.BAD_REQUEST)