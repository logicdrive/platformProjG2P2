import os
import openai
from slack_bolt.adapter.socket_mode import SocketModeHandler
from slack import WebClient
from slack_bolt import App

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

    # openai.api_key = OPENAI_API_KEY
    # response = openai.Completion.create(
    #     engine="text-davinci-003",
    #     prompt=prompt,
    #     max_tokens=1024,
    #     n=1,
    #     stop=None,
    #     temperature=0.5).choices[0].text

    client.chat_postMessage(channel=CURRENT_CHANNEL, 
                            thread_ts=CURRENT_THREAD,
                            text=f"ECHO: {USER_PROMPT}")


if __name__ == "__main__":
    SocketModeHandler(app, SLACK_APP_TOKEN).start()