import requests
import json
from dataclasses import dataclass

@dataclass
class HttpMethod:
    GET = 1
    PUT = 2

class HttpUtil:
    @staticmethod
    def jsonRequest(method:HttpMethod, url:str, data=None):
        RES = ""
        if method == HttpMethod.GET:
            RES = requests.get(url)
        elif method == HttpMethod.PUT:
            RES = requests.put(url, json=data, headers={"Content-type": "application/json"})
        else:
            raise ValueError("Invalid method")

        if str(RES.status_code)[0] != "2":
            raise ValueError("Request failed")

        return json.loads(RES.text) if RES.text else ""