from flask import Blueprint, request as flaskRequest, jsonify
from http import HTTPStatus

from .._global.proxy.S3ProxyService import uploadDataUrlToPublicS3

from .._global.logger import CustomLogger
from .._global.logger import CustomLoggerType

bp = Blueprint(__name__.split(".")[-1], __name__, url_prefix="/s3")


class UploadFileReqDto:
    def __init__(self, request) :
        self.__jsonData = request.get_json()
        self.__dataUrl:str = self.__jsonData["dataUrl"] or ""

    def __str__(self) :
        return "<{} dataUrlSize: {}>".format(self.__class__.__name__, 
                    len(self.__dataUrl))


    @property
    def dataUrl(self) -> str :
        return self.__dataUrl

class UploadFileResDto:
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


# 주어진 DataUrl을 기반으로 파일을 S3에 업로드시키고, 관련 URL을 반환하기 위해서
@bp.route("/uploadFile", methods=("PUT",))
def uploadFile() -> UploadFileReqDto :
    try :

        reqDto:UploadFileReqDto = UploadFileReqDto(flaskRequest)
        CustomLogger.debugObj(CustomLoggerType.ENTER, reqDto)


        fileUrl:str = uploadDataUrlToPublicS3(reqDto.dataUrl)


        resDto:UploadFileResDto = UploadFileResDto(fileUrl)
        CustomLogger.debugObj(CustomLoggerType.EXIT, resDto)
        return (resDto.json(), HTTPStatus.OK)

    except Exception as e :
        jsonData = flaskRequest.get_json()
        dataUrl = jsonData["dataUrl"] or ""
        CustomLogger.error(e, "", "<dataUrlSize: {}>".format(len(dataUrl)))
        return ("", HTTPStatus.BAD_REQUEST)