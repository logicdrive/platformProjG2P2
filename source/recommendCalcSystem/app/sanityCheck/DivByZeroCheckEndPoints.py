from flask import Blueprint
from http import HTTPStatus

from .._global.logger import CustomLogger

bp = Blueprint(__name__.split(".")[-1], __name__, url_prefix="/sanityCheck")


# 정상적인 에러 로그 출력 여부를 테스트해보기 위해서
@bp.route("/divByZeroCheck", methods=("GET",))
def divByZeroCheck() -> str :
    try :

        returnNum:int = 1/0
        return (returnNum, HTTPStatus.OK)

    except Exception as e :
        CustomLogger.error(e, "<returnNum: {}>".format("Undefined"), "Div By Zero Check Message")
        return ("", HTTPStatus.INTERNAL_SERVER_ERROR)