from flask import Flask

from .SanityCheckEndPoints import bp as SanityCheckEndPointsBp
from .LogsEndPoints import bp as LogsEndPointsBp
from .DivByZeroCheckEndPoints import bp as DivByZeroCheckEndPointsBp
from .EchoWithJsonEndPoints import bp as EchoWithJsonEndPointsBp
from .CollectedDataSanityCheckEndPoints import bp as CollectedDataSanityCheckEndPointsBp

def addSanityCheckBluePrints(app:Flask) :
    app.register_blueprint(SanityCheckEndPointsBp)
    app.register_blueprint(LogsEndPointsBp)
    app.register_blueprint(DivByZeroCheckEndPointsBp)
    app.register_blueprint(EchoWithJsonEndPointsBp)
    app.register_blueprint(CollectedDataSanityCheckEndPointsBp)