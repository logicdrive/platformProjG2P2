from flask import Flask

from .UploadFileEndPoints import bp as UploadFileEndPointsBp
from .RemoveFileEndPoints import bp as RemoveFileEndPointsBp

def addS3BluePrints(app:Flask) :
    app.register_blueprint(UploadFileEndPointsBp)
    app.register_blueprint(RemoveFileEndPointsBp)
