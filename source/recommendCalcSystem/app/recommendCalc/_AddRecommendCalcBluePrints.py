from flask import Flask

from .UpdateRecommendBookToUserEndPoints import bp as UpdateRecommendBookToUserEndPointsBp
from .UpdateRecommendBookToBookEndPoints import bp as UpdateRecommendBookToBookEndPointsBp

def addRecommendCalcBluePrints(app:Flask) :
    app.register_blueprint(UpdateRecommendBookToUserEndPointsBp)
    app.register_blueprint(UpdateRecommendBookToBookEndPointsBp)