import uuid
import os
import boto3
import urllib

from ..._global.workdir.WorkDirManager import WorkDirManager

from ..logger import CustomLogger
from ..logger import CustomLoggerType

AWS_ACCESS_KEY = os.environ.get("AWS_ACCESS_KEY")
AWS_SECRET_ACCESS_KEY = os.environ.get("AWS_SECRET_ACCESS_KEY")
AWS_BUCKET_NAME = os.environ.get("AWS_BUCKET_NAME")
AWS_REGION_CODE = os.environ.get("AWS_REGION_CODE")

CLIENT = boto3.Session(
    aws_access_key_id=AWS_ACCESS_KEY,
    aws_secret_access_key=AWS_SECRET_ACCESS_KEY,
)
CLIENT_S3 = CLIENT.resource("s3")


# 주어진 DataUrl을 기반으로 파일을 S3에 업로드시키고, 관련 URL을 반환하기 위해서
def uploadDataUrlToPublicS3(dataUrl:str) -> str :
    with WorkDirManager() as path:
        uploadFilePath:str = __writeFileByUsingDataUrl(dataUrl, "uploadFile", path())
        return uploadToPublicS3(uploadFilePath)

# 임의의 파일명을 이용해서 주어진 환경변수에 맞는 버킷에 파일을 업로드시키고, 경로를 반환하기 위해서
def uploadToPublicS3(filePath:str) -> str :
    objectKey = str(uuid.uuid4()) + "." + filePath.split(".")[-1]

    CustomLogger.debug(CustomLoggerType.EFFECT, "Try to upload file", "<filePath: {}, objectKey: {}>".format(filePath, objectKey))
    CLIENT_S3.meta.client.upload_file(Filename=filePath, Bucket=AWS_BUCKET_NAME, Key=objectKey)

    CustomLogger.debug(CustomLoggerType.EFFECT, "Try to access public object")
    obj = CLIENT_S3.Object(AWS_BUCKET_NAME, objectKey)
    obj.Acl().put(ACL="public-read")

    uploadUrl:str = f"https://{AWS_BUCKET_NAME}.s3.{AWS_REGION_CODE}.amazonaws.com/{objectKey}"
    CustomLogger.debug(CustomLoggerType.EFFECT, "File was successfully uploaded to S3", "<uploadUrl: {}>".format(uploadUrl))
    return uploadUrl

# 주어진 DataUrl을 파일로 변환해서 출력하고, 관련 경로를 반환시키기 위해서
def __writeFileByUsingDataUrl(dataUrl:str, fileName:str, extractPath:str) :
    dataUrlRes = urllib.request.urlopen(dataUrl)

    fileExt = dataUrl.split(",")[0].split(";")[0].split("/")[1]
    filePath = extractPath + f"{fileName}.{fileExt}"

    CustomLogger.debug(CustomLoggerType.EFFECT, "Try to write file", "<filePath: {}>".format(filePath))
    with open(filePath, "wb") as f:
        f.write(dataUrlRes.file.read())
    
    return filePath


# 주어진 경로에 있는 S3 파일을 삭제시키기 위해서
def deleteToPublic3ByUsingFileUrl(fileUrl:str) -> str :
    __deleteToPublic3(fileUrl.split("/")[-1])
    return fileUrl

# 주어진 버킷에 있는 해당 키를 가진 파일을 삭제시키기 위해서
def __deleteToPublic3(objectKey:str) -> None :
    CLIENT_S3.Object(AWS_BUCKET_NAME, objectKey).delete()
