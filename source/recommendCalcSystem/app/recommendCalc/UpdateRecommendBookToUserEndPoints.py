from flask import Blueprint, jsonify
from http import HTTPStatus

from .._global.logger import CustomLogger
from .._global.logger import CustomLoggerType

bp = Blueprint(__name__.split(".")[-1], __name__, url_prefix="/recommendCalc")


class UpdateRecommendBookToUserResDto:
    def __init__(self, updatedUserCount:int) :
        self.__updatedUserCount:int = updatedUserCount

    def __str__(self) :
        return "<{} updatedUserCount: {}>".format(self.__class__.__name__,
                    self.__updatedUserCount)


    @property
    def updatedUserCount(self) -> int :
        return self.__updatedUserCount
    
    
    def json(self) -> str :
        return jsonify({
            "updatedUserCount": self.__updatedUserCount
        })


# 특정 유저에게 추천할 책 목록들을 새로 갱신하기 위해서
@bp.route("/updateRecommendBookToUser", methods=("PUT",))
def updateRecommendBookToUser() -> UpdateRecommendBookToUserResDto :
    try :

        CustomLogger.debug(CustomLoggerType.ENTER)



        resDto = UpdateRecommendBookToUserResDto(1)
        CustomLogger.debugObj(CustomLoggerType.EXIT, resDto)
        return (resDto.json(), HTTPStatus.OK)

    except Exception as e :
        CustomLogger.error(e)
        return ("", HTTPStatus.BAD_REQUEST)