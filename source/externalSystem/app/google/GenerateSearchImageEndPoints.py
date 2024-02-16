from flask import Blueprint, request as flaskRequest, jsonify
from http import HTTPStatus

from .._global.proxy.GoogleProxyService import getSearchImageAndGetS3Url

from .._global.logger import CustomLogger
from .._global.logger import CustomLoggerType

bp = Blueprint(__name__.split(".")[-1], __name__, url_prefix="/google")


class GenerateSearchImageReqDto:
    def __init__(self, request) :
        self.__jsonData = request.get_json()
        self.__query:str = self.__jsonData["query"] or ""

    def __str__(self) :
        return "<{} query: {}>".format(self.__class__.__name__, 
                    self.__query)


    @property
    def query(self) -> str :
        return self.__query

class GenerateSearchImageResDto:
    def __init__(self, fileUrl:str) :
        self.__fileUrl:str = fileUrl

    def __str__(self) :
        return "<{} fileUrl: {}>".format(self.__class__.__name__,
                    self.__fileUrl)


    @property
    def fileUrl(self) -> str :
        return self.__fileUrl
    
    
    def json(self) -> str :
        return jsonify({
            "fileUrl": self.__fileUrl
        })


# 주어진 주제에 관한 이미지를 구글에 검색하고, 파일을 저장한 후, S3에 저장된 URL을 반환하기 위해서
@bp.route("/generateSearchImage", methods=("PUT",))
def generateSearchImage() -> GenerateSearchImageReqDto :
    try :

        reqDto:GenerateSearchImageReqDto = GenerateSearchImageReqDto(flaskRequest)
        CustomLogger.debugObj(CustomLoggerType.ENTER, reqDto)

        fileUrl:str = getSearchImageAndGetS3Url(reqDto.query)

        resDto:GenerateSearchImageResDto = GenerateSearchImageResDto(fileUrl)
        CustomLogger.debugObj(CustomLoggerType.EXIT, resDto)
        return (resDto.json(), HTTPStatus.OK)

    except Exception as e :
        jsonData = flaskRequest.get_json()
        query = jsonData["query"] or ""
        CustomLogger.error(e, "", "<query: {}>".format(query))
        return ("", HTTPStatus.BAD_REQUEST)