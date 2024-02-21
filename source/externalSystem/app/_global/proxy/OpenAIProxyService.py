import re
from openai import OpenAI

from ..._global.logger import CustomLogger
from ..._global.logger import CustomLoggerType

CLIENT = OpenAI()


# ChatGPT와 통신해서 관련 응답을 얻어내기 위해서
def getChatGptAnswer(messages:list[str]) -> str :
    CustomLogger.debug(CustomLoggerType.EFFECT, "Try to get reponse by using ChatGPT", "<messages: {}>".format(messages))

    answer:str = CLIENT.chat.completions.create(
                model="gpt-3.5-turbo",
                temperature=1.0,
                max_tokens=512,
                messages=messages
            ).choices[0].message.content
    
    CustomLogger.debug(CustomLoggerType.EFFECT, "Answer was acquired from ChatGPT", "<answer: {}>".format(answer))
    return answer

# System, User 역할을 가진 메시지들을 이용해서 ChatGPT로부터 응답을 얻어내기 위해서
def getChatGptAnswerWithSystemRole(systemMessage:str, userMessage:str) -> str :
    return getChatGptAnswer([
        { "role": "system", "content": systemMessage },
        { "role": "user", "content": userMessage }
    ])

# ChatGPT로부터 응답을 얻어내기 위해서, 특정 조건을 만족할 때까지 여러번 시도하기 위해서
def getChatGptAnswerWithSystemRoleWithRetry(systemMessage:str, userMessage:str, checkFunction, retryCount:int=2) -> str :
    answer:str = getChatGptAnswerWithSystemRole(systemMessage, userMessage)
    if checkFunction(answer) :
        return answer

    for _ in range(retryCount) :
        answer = getChatGptAnswerWithSystemRole(systemMessage, userMessage)
        if checkFunction(answer) :
            return answer
    
    CustomLogger.error(Exception("Failed to get the answer from ChatGPT"), "", "<systemMessage: {}>, <userMessage: {}>".format(systemMessage, userMessage))
    raise Exception("Failed to get the answer from ChatGPT: " + answer + " (systemMessage: " + systemMessage + ", userMessage: " + userMessage + ")")


# 주어진 쿼리에 대한 인덱스들을 GPT를 통해서 생성하기 위해서
def generateIndexesByUsingGPT(query:str, maxIndexCount:int=5) -> list[str] :
    CustomLogger.debug(CustomLoggerType.EFFECT, "Try to get indexes by using ChatGPT", "<query: {}>".format(query))

    GPT_ANSWER:list[str] = getChatGptAnswerWithSystemRoleWithRetry(
"""You are a blogger who makes educational content for learners.
When I give you the title, you need to print suitable indexes.
Please ensure that the contents of each index are unique.
Please follow the print format below.
\"\"\"
1. ...
2. ...
3. ...
...
\"\"\"""",
query,
lambda answer : answer.find("1. ") != -1
    )

    CustomLogger.debug(CustomLoggerType.EFFECT, "Lastly acquired answer", "<answer: {}>".format(GPT_ANSWER))
    return [" ".join(index.split()[1:]) for index in GPT_ANSWER.split("\n") if re.match("^\d+\.", index)][:maxIndexCount]

# 주어진 쿼리에 대한 태그들을 GPT를 통해서 생성하기 위해서
def generateTagsByUsingGPT(query:str, maxTagCount:int=5) -> list[str] :
    CustomLogger.debug(CustomLoggerType.EFFECT, "Try to get tags by using ChatGPT", "<query: {}>".format(query))

    GPT_ANSWER:str = getChatGptAnswerWithSystemRoleWithRetry(
"""When I give you the title and indexes, You should extract the five most related tags for searching.
Please follow the print format below.
\"\"\"
Tags: ...
\"\"\"""",
query,
lambda answer : (answer.find("Tags: ") != -1) and (answer.find(", ") != -1)
    )

    CustomLogger.debug(CustomLoggerType.EFFECT, "Lastly acquired answer", "<answer: {}>".format(GPT_ANSWER))
    return re.sub('[^A-Za-z0-9, ]+', '', GPT_ANSWER.replace("Tags: ", "")).split(", ")[:maxTagCount]

# 주어진 쿼리에 대한 컨텐츠를 GPT를 통해서 생성하기 위해서
def generateContentByUsingGPT(query:str) -> str :
    CustomLogger.debug(CustomLoggerType.EFFECT, "Try to get content by using ChatGPT", "<query: {}>".format(query))

    GPT_ANSWER:str = getChatGptAnswerWithSystemRoleWithRetry(
"""You are a blogger who makes educational content for learners.
When I give you a subject, you must write content within 500 characters.
When you end your contents, you need to attach '[END]' string to the end.""",
query,
lambda answer : answer.find("[END]") != -1
    )

    CustomLogger.debug(CustomLoggerType.EFFECT, "Lastly acquired answer", "<answer: {}>".format(GPT_ANSWER))
    return GPT_ANSWER.split("[END]")[0].strip()

# 주어진 쿼리에 대한 문제와 정답들을 GPT를 통해서 생성하기 위해서
def generateProblemsByUsingGPT(query:str, maxProblemCount:int=3) -> tuple[list[str], list[str]] :
    CustomLogger.debug(CustomLoggerType.EFFECT, "Try to get problems by using ChatGPT", "<query: {}>".format(query))

    GPT_ANSWER:str = getChatGptAnswerWithSystemRoleWithRetry(
"""When I give you some content, you need to make three multiple-choice questions.
Please follow the below format,
\"\"\"
Q1. ...

A. ...
B. ...
C. ...

Answer: ...
\"\"\"""",
query,
lambda answer : (answer.find("Q1. ") != -1) and (answer.find("A. ") != -1) and (answer.find("Answer: ") != -1) and (answer.find("Q2. ") != -1) and (answer.find("Q3. ") != -1)
    )

    CustomLogger.debug(CustomLoggerType.EFFECT, "Lastly acquired answer", "<answer: {}>".format(GPT_ANSWER))


    ANSWER_SPLIT = GPT_ANSWER.split("\n\n")

    problems = []
    answers = []
    for splitIndex in range(0, len(ANSWER_SPLIT)-1, 3):
        problems.append("\n\n".join(ANSWER_SPLIT[splitIndex:splitIndex+2])[4:])
        answers.append(ANSWER_SPLIT[splitIndex+2][8:])

    return problems[:maxProblemCount], answers[:maxProblemCount]