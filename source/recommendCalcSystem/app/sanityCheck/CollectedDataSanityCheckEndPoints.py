from flask import Blueprint
from http import HTTPStatus

from .._global.logger import CustomLogger
from .._global.logger import CustomLoggerType

from .._global.proxy.CollectedDataProxy import CollectedDataProxy

bp = Blueprint(__name__.split(".")[-1], __name__, url_prefix="/sanityCheck")


@bp.route("collectedDataSanityCheck", methods=("GET",))
def collectedDataSanityCheck() -> str :
    CustomLogger.debug(CustomLoggerType.ENTER_EXIT)

    CollectedDataProxy.sanityCheck()
    
    return ("", HTTPStatus.OK)