from flask import Blueprint, request as flaskRequest, jsonify
from http import HTTPStatus

from .._global.logger import CustomLogger
from .._global.logger import CustomLoggerType
from .._global.proxy.OpenAIProxyService import generateProblemsByUsingGPT

bp = Blueprint(__name__.split(".")[-1], __name__, url_prefix="/openai")


class GenerateProblemsReqDto:
    def __init__(self, request) :
        self.__jsonData = request.get_json()
        self.__query:str = self.__jsonData["query"] or ""

    def __str__(self) :
        return "<{} query: {}>".format(self.__class__.__name__, 
                    self.__query)


    @property
    def query(self) -> str :
        return self.__query

class GenerateProblemsResDto:
    def __init__(self, contents:list[str], answers:list[str]) :
        self.__contents:list[str] = contents
        self.__answers:list[str] = answers

    def __str__(self) :
        return "<{} contents: {} answers: {}>".format(self.__class__.__name__,
                    ", ".join(self.__contents), ", ".join(self.__answers))


    @property
    def contents(self) -> list[str] :
        return self.__contents

    @property
    def answers(self) -> list[str] :
        return self.__answers
    
    
    def json(self) -> str :
        return jsonify({
            "contents": self.__contents,
            "answers": self.__answers
        })


# 주어진 쿼리에 해당하는 문제들을 생성하고, 관련 정보를 반환하기 위해서
@bp.route("/generateProblems", methods=("PUT",))
def generateProblems() -> GenerateProblemsReqDto :
    try :

        reqDto:GenerateProblemsReqDto = GenerateProblemsReqDto(flaskRequest)
        CustomLogger.debugObj(CustomLoggerType.ENTER, reqDto)


        problems, answers = generateProblemsByUsingGPT(reqDto.query)


        resDto:GenerateProblemsResDto = GenerateProblemsResDto(problems, answers)
        CustomLogger.debugObj(CustomLoggerType.EXIT, resDto)
        return (resDto.json(), HTTPStatus.OK)

    except Exception as e :
        jsonData = flaskRequest.get_json()
        query = jsonData["query"] or ""
        CustomLogger.error(e, "", "<query: {}>".format(query))
        return ("", HTTPStatus.BAD_REQUEST)