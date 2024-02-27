import os
from openai import OpenAI
from slack_bolt.adapter.socket_mode import SocketModeHandler
from slack import WebClient
from slack_bolt import App


botTrainMessages = []
for index, file in enumerate(os.listdir("botTrainMessages")):
    message = "\n".join(open(f"botTrainMessages/{file}", "rt", encoding="UTF8").readlines())
    if index%2 == 0:
        botTrainMessages.append({
            "role": "system",
            "content": message
        })
    else:
        botTrainMessages.append({
            "role": "user",
            "content": message
        })


CLIENT = OpenAI()


# ChatGPT와 통신해서 관련 응답을 얻어내기 위해서
def getChatGptAnswer(messages:list[str]) -> str :
    answer:str = CLIENT.chat.completions.create(
                model="gpt-3.5-turbo",
                temperature=1.0,
                max_tokens=512,
                messages=messages
            ).choices[0].message.content
    return answer

# ChatGPT로부터 문서 검색 결과를 얻기 위해서
def getDocumentSearchAnswerByChatGpt(query) -> str :
    return getChatGptAnswer(
        botTrainMessages + [{"role": "user", "content": query}]
    )


SLACK_BOT_TOKEN = os.environ.get("SLACK_BOT_TOKEN")
SLACK_APP_TOKEN = os.environ.get("SLACK_APP_TOKEN")
OPENAI_API_KEY  = os.environ.get("OPENAI_API_KEY")

app = App(token=SLACK_BOT_TOKEN) 
client = WebClient(SLACK_BOT_TOKEN)


@app.event("app_mention")
def handle_message_events(body, logger):

    USER_PROMPT = str(body["event"]["text"]).split(">")[1]
    CURRENT_CHANNEL = body["event"]["channel"]
    CURRENT_THREAD = body["event"]["event_ts"]


    client.chat_postMessage(channel=CURRENT_CHANNEL, 
                            thread_ts=CURRENT_THREAD,
                            text=f"응답을 기다리는 중입니다. 잠시만 기다려주세요. :robot_face:")


    BOT_ANSWER = getDocumentSearchAnswerByChatGpt(USER_PROMPT)


    client.chat_postMessage(channel=CURRENT_CHANNEL, 
                            thread_ts=CURRENT_THREAD,
                            text=f"{BOT_ANSWER}")


if __name__ == "__main__":
    SocketModeHandler(app, SLACK_APP_TOKEN).start()