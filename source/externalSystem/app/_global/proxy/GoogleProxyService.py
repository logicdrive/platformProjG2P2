from google_images_search import GoogleImagesSearch
import os

from ..._global.workdir.WorkDirManager import WorkDirManager
from ..._global.proxy.S3ProxyService import uploadToPublicS3

GOOGLE_API_KEY = os.environ.get("GOOGLE_API_KEY")
GOOGLE_PROJECT_CX = os.environ.get("GOOGLE_PROJECT_CX")
gis = GoogleImagesSearch(GOOGLE_API_KEY, GOOGLE_PROJECT_CX)


def getSearchImageAndGetS3Url(query:str) -> str :
    with WorkDirManager() as path:
        IMAGE_FILE_PATH:str = generateSearchImage(query, path(), "searchImage")

        searchedImageFilePath = ""
        for filePath in os.listdir(path()):
            if IMAGE_FILE_PATH.split("/")[-1] in filePath:
                searchedImageFilePath = os.path.join(path(), filePath)
                break
        
        if searchedImageFilePath == "":
            raise Exception("S3에 저장시킬 이미지 파일을 찾을 수 없습니다.")
        
        return uploadToPublicS3(searchedImageFilePath)

def generateSearchImage(query:str, dirPath:str, imageName:str) -> str :
    gis.search(search_params={
        "q": query,
        "num": 1,
        "fileType": "jpg|gif|png"
    }, path_to_dir=dirPath, custom_image_name=imageName)
    return os.path.join(dirPath, imageName)