import uuid
import os
import shutil

from ..logger import CustomLogger
from ..logger import CustomLoggerType

class WorkDirManager:
    def __init__(self, isAfterClear:bool=True):
        self.workDirPath = "./workDir/" + str(uuid.uuid4()) + "/"
        self.isAfterClear = isAfterClear
          
    def __enter__(self):
        CustomLogger.debug(CustomLoggerType.EFFECT, "Try to make workdir", "<workDirPath: {}>".format(self.workDirPath))
        os.makedirs(self.workDirPath)
        return lambda subPath="" : self.workDirPath + subPath
      
    def __exit__(self, exc_type, exc_value, exc_traceback):
        if self.isAfterClear :
            CustomLogger.debug(CustomLoggerType.EFFECT, "Try to remove workdir", "<workDirPath: {}>".format(self.workDirPath))
            shutil.rmtree(self.workDirPath)