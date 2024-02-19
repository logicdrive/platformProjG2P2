import requests
import json
from dataclasses import dataclass

from ..logger import CustomLogger
from ..logger import CustomLoggerType

@dataclass
class HttpMethod:
    GET = "GET"
    PUT = "PUT"

class HttpUtil:
    @staticmethod
    def jsonRequest(method:HttpMethod, url:str, data=None):
        CustomLogger.debug(CustomLoggerType.ENTER, "<jsonRequest method: {}, url: {}, dataLength: {}>"\
                                .format(method, url, len(data) if data else 0))

        RES = ""
        if method == HttpMethod.GET:
            RES = requests.get(url)
        elif method == HttpMethod.PUT:
            RES = requests.put(url, json=data, headers={"Content-type": "application/json"})
        else:
            raise ValueError("Invalid method")

        if str(RES.status_code)[0] != "2":
            raise ValueError("Request failed")


        CustomLogger.debug(CustomLoggerType.EXIT, "<jsonRequest url: {}, statusCode: {}, responseLength: {}>"\
                            .format(url, RES.status_code, len(RES.text) if RES.text else 0))
        
        return json.loads(RES.text) if RES.text else ""