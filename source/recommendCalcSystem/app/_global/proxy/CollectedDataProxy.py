from ..util.HttpUtil import HttpUtil, HttpMethod
import os


COLLECTED_DATA_HOST = os.environ.get("COLLECTED_DATA_HOST")
COLLECTED_DATA_PORT = os.environ.get("COLLECTED_DATA_PORT")

COLLECTED_DATA_URL = f"http://{COLLECTED_DATA_HOST}:{COLLECTED_DATA_PORT}"
collectedDataPath = lambda path: f"{COLLECTED_DATA_URL}/{path}"


class CollectedDataProxy:
    @staticmethod
    def sanityCheck():
        HttpUtil.jsonRequest(HttpMethod.GET, collectedDataPath("sanityCheck"))