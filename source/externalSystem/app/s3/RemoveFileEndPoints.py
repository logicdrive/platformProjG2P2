from flask import Blueprint, request as flaskRequest, jsonify
from http import HTTPStatus

from .._global.proxy.S3ProxyService import deleteToPublic3ByUsingFileUrl

from .._global.logger import CustomLogger
from .._global.logger import CustomLoggerType

bp = Blueprint(__name__.split(".")[-1], __name__, url_prefix="/s3")


class RemoveFileReqDto:
    def __init__(self, request) :
        self.__jsonData = request.get_json()
        self.__fileUrl:str = self.__jsonData["fileUrl"] or ""

    def __str__(self) :
        return "<{} fileUrl: {}>".format(self.__class__.__name__, 
                    self.__fileUrl)


    @property
    def fileUrl(self) -> str :
        return self.__fileUrl

class RemoveFileResDto:
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


# 주어진 경로에 있는 파일을 삭제시키기 위해서
@bp.route("/removeFile", methods=("PUT",))
def removeFile() -> RemoveFileResDto :
    try :

        reqDto:RemoveFileReqDto = RemoveFileReqDto(flaskRequest)
        CustomLogger.debugObj(CustomLoggerType.ENTER, reqDto)


        deletedFileUrl:str = deleteToPublic3ByUsingFileUrl(reqDto.fileUrl)


        resDto:RemoveFileResDto = RemoveFileResDto(deletedFileUrl)
        CustomLogger.debugObj(CustomLoggerType.EXIT, resDto)
        return (resDto.json(), HTTPStatus.OK)

    except Exception as e :
        jsonData = flaskRequest.get_json()
        fileUrl = jsonData["fileUrl"] or ""
        CustomLogger.error(e, "", "<fileUrl: {}>".format(fileUrl))
        return ("", HTTPStatus.BAD_REQUEST)