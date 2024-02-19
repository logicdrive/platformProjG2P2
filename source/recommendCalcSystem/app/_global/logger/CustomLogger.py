from flask import current_app
import traceback

from . import CustomLoggerType


customLoggerTypeMap = {
    CustomLoggerType.ENTER_EXIT: "ENTER/EXIT",
    CustomLoggerType.ENTER: "ENTER",
    CustomLoggerType.EXIT: "EXIT",
    CustomLoggerType.EFFECT: "EFFECT"
}


def debug(type:CustomLoggerType, params:str="{}", message:str="") :
    current_app.logger.debug("[{}] [{}] {}: {}".format(traceback.extract_stack()[-2], customLoggerTypeMap[type], message, params))

def debugObj(type:CustomLoggerType, obj:object, message:str="") :
    current_app.logger.debug("[{}] [{}] {}: <{}: {}>".format(traceback.extract_stack()[-2], customLoggerTypeMap[type], message, obj.__class__.__name__, obj))

def error(e:Exception, params:str="{}", message:str="") :
    current_app.logger.error("[{}] [{}] {}: {}".format(traceback.extract_stack()[-2], e.__class__, e.__doc__ + " / " + message, params) + "\n" + ''.join(traceback.format_tb(e.__traceback__)))
