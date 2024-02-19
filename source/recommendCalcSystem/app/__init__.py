import os
from flask import Flask

from ._global.logger import LoggingConfig

from .sanityCheck import _AddSanityCheckBluePrints

dirPathsToCreate = ["./log", "./workDir"]

def create_app():
    for dirPathToCreate in dirPathsToCreate :
        if not os.path.exists(dirPathToCreate):
            os.makedirs(dirPathToCreate)


    app = Flask(__name__)
    LoggingConfig.setupLoggingConfig()

    _AddSanityCheckBluePrints.addSanityCheckBluePrints(app)
    
    return app
