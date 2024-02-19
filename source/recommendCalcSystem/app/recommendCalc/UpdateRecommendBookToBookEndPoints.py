from flask import Blueprint, jsonify
from http import HTTPStatus

from .._global.logger import CustomLogger
from .._global.logger import CustomLoggerType

bp = Blueprint(__name__.split(".")[-1], __name__, url_prefix="/recommendCalc")


class UpdateRecommendBookToBookResDto:
    def __init__(self, updatedBookCount:int) :
        self.__updatedBookCount:int = updatedBookCount

    def __str__(self) :
        return "<{} updatedBookCount: {}>".format(self.__class__.__name__,
                    self.__updatedBookCount)


    @property
    def updatedBookCount(self) -> int :
        return self.__updatedBookCount
    
    
    def json(self) -> str :
        return jsonify({
            "updatedBookCount": self.__updatedBookCount
        })


# 특정 책에 대한 추천할 책 목록들을 새로 갱신하기 위해서
@bp.route("/updateRecommendBookToBook", methods=("PUT",))
def updateRecommendBookToBook() -> UpdateRecommendBookToBookResDto :
    try :

        CustomLogger.debug(CustomLoggerType.ENTER)
        


        resDto = UpdateRecommendBookToBookResDto(1)
        CustomLogger.debugObj(CustomLoggerType.EXIT, resDto)
        return (resDto.json(), HTTPStatus.OK)

    except Exception as e :
        CustomLogger.error(e)
        return ("", HTTPStatus.BAD_REQUEST)