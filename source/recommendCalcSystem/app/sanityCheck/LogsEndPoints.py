from flask import Blueprint, request as flaskRequest, jsonify
from http import HTTPStatus
import re

from .._global.logger import CustomLogger
from .._global.logger import CustomLoggerType

bp = Blueprint(__name__.split(".")[-1], __name__, url_prefix="/sanityCheck")


class LogsReqDto:
    def __init__(self, request) :
        self.__lineLength:int = request.args.get("lineLength") or 10
        self.__regFilter:str = request.args.get("regFilter") or ""

    def __str__(self) :
        return "<{} lineLength: {}, regFilter: {}>".format(self.__class__.__name__, 
                    self.__lineLength, self.__regFilter)


    @property
    def lineLength(self) -> int :
        return self.__lineLength

    @property
    def regFilter(self) -> int :
        return self.__regFilter

class LogsResDto:
    def __init__(self, logs:list[str]) :
        self.__logs:list[str] = logs

    def __str__(self) :
        return "<{} logsLength: {}>".format(self.__class__.__name__,
                    len(self.__logs))


    @property
    def logs(self) -> list[str] :
        return self.__logs
    
    
    def json(self) -> str :
        return jsonify({
            "logs": self.__logs
        })


@bp.route("/logs", methods=("GET",))
def logs() -> LogsResDto :
    logFilePath:str = "./log/logback.log"

    try :

        reqDto:LogsReqDto = LogsReqDto(flaskRequest)
        CustomLogger.debugObj(CustomLoggerType.ENTER, reqDto)


        readLogs:list[str] = []
        with open(logFilePath, 'r') as f:
            for readline in f.readlines() :
                if reqDto.regFilter : 
                    if re.match(reqDto.regFilter, readline) : readLogs.append(readline)
                else : 
                    readLogs.append(readline)
        readLogs = readLogs[-reqDto.lineLength:]


        resDto:LogsResDto = LogsResDto(readLogs)
        CustomLogger.debugObj(CustomLoggerType.EXIT, resDto)
        return (resDto.json(), HTTPStatus.OK)

    except Exception as e :
        CustomLogger.error(e, "<lineLength: {}>".format(flaskRequest.args.get("lineLength") or "None"))
        return ("", HTTPStatus.BAD_REQUEST)