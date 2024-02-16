from flask import Flask

from .GenerateSearchImageEndPoints import bp as GenerateSearchImageEndPointsBp

def addGoogleBluePrints(app:Flask) :
    app.register_blueprint(GenerateSearchImageEndPointsBp)
