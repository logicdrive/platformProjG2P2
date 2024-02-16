import os
from flask import Flask

from ._global.logger import LoggingConfig

from .sanityCheck import _AddSanityCheckBluePrints
from .s3 import _AddS3BluePrints
from .google import _AddGoogleBluePrints
from .openai import _AddOpenAIBluePrints

dirPathsToCreate = ["./log", "./workDir"]

def create_app():
    for dirPathToCreate in dirPathsToCreate :
        if not os.path.exists(dirPathToCreate):
            os.makedirs(dirPathToCreate)


    app = Flask(__name__)
    LoggingConfig.setupLoggingConfig()

    _AddSanityCheckBluePrints.addSanityCheckBluePrints(app)
    _AddS3BluePrints.addS3BluePrints(app)
    _AddGoogleBluePrints.addGoogleBluePrints(app)
    _AddOpenAIBluePrints.addOpenAIBluePrints(app)

    return app
