from flask import Blueprint, request as flaskRequest, jsonify
from http import HTTPStatus

from .._global.logger import CustomLogger
from .._global.logger import CustomLoggerType

bp = Blueprint(__name__.split(".")[-1], __name__, url_prefix="/sanityCheck")


class EchoWithJsonReqDto:
    def __init__(self, request) :
        self.__jsonData = request.get_json()
        self.__message:str = self.__jsonData["message"] or ""

    def __str__(self) :
        return "<{} message: {}>".format(self.__class__.__name__, 
                    self.__message)


    @property
    def message(self) -> str :
        return self.__message

class EchoWithJsonResDto:
    def __init__(self, message:str) :
        self.__message:str = message

    def __str__(self) :
        return "<{} message: {}>".format(self.__class__.__name__,
                    self.__message)


    @property
    def message(self) -> str :
        return self.__message
    
    
    def json(self) -> str :
        return jsonify({
            "message": self.__message
        })


# JSON 송수신 여부를 간편하게 테스트해보기 위해서
@bp.route("/echoWithJson", methods=("PUT",))
def echoWithJson() -> EchoWithJsonResDto :
    try :

        reqDto:EchoWithJsonReqDto = EchoWithJsonReqDto(flaskRequest)
        CustomLogger.debugObj(CustomLoggerType.ENTER_EXIT, reqDto)
        return (EchoWithJsonResDto(reqDto.message).json(), HTTPStatus.OK)

    except Exception as e :
        CustomLogger.error(e, "<message: {}>".format(flaskRequest.args.get("message") or "None"))
        return ("", HTTPStatus.BAD_REQUEST)