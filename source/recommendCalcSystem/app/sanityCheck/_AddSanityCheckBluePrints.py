from flask import Flask

from .SanityCheckEndPoints import bp as SanityCheckEndPointsBp
from .LogsEndPoints import bp as LogsEndPointsBp
from .DivByZeroCheckEndPoints import bp as DivByZeroCheckEndPointsBp
from .EchoWithJsonEndPoints import bp as EchoWithJsonEndPointsBp

def addSanityCheckBluePrints(app:Flask) :
    app.register_blueprint(SanityCheckEndPointsBp)
    app.register_blueprint(LogsEndPointsBp)
    app.register_blueprint(DivByZeroCheckEndPointsBp)
    app.register_blueprint(EchoWithJsonEndPointsBp)