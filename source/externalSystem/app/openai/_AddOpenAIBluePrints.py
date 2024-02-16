from flask import Flask

from .GenerateTagsEndPoints import bp as GenerateTagsEndPointsBp
from .GenerateIndexesEndPoints import bp as GenerateIndexesEndPointsBp
from .GenerateContentEndPoints import bp as GenerateContentEndPointsBp
from .GenerateProblemsEndPoints import bp as GenerateProblemsEndPointsBp

def addOpenAIBluePrints(app:Flask) :
    app.register_blueprint(GenerateTagsEndPointsBp)
    app.register_blueprint(GenerateIndexesEndPointsBp)
    app.register_blueprint(GenerateContentEndPointsBp)
    app.register_blueprint(GenerateProblemsEndPointsBp)