from flask import Blueprint
from http import HTTPStatus

from .._global.logger import CustomLogger
from .._global.logger import CustomLoggerType

bp = Blueprint(__name__.split(".")[-1], __name__, url_prefix="/sanityCheck")


@bp.route("", methods=("GET",))
def sanityCheck() -> str :
    CustomLogger.debug(CustomLoggerType.ENTER_EXIT)
    return ("", HTTPStatus.OK)