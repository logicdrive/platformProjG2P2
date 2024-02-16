.\venv\Scripts\activate


$env:AWS_BUCKET_NAME = "book-generator"
$env:AWS_REGION_CODE = "ap-northeast-2"
cp envs/development.env .flaskenv


if (Test-Path "log") {
    Remove-Item -Recurse -Force "log"
}

if (Test-Path "workDir") {
    Remove-Item -Recurse -Force "workDir"
}


python -m flask run