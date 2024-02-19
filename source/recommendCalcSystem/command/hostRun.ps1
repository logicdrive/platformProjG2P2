.\venv\Scripts\activate


cp envs/development.env .flaskenv


if (Test-Path "log") {
    Remove-Item -Recurse -Force "log"
}

if (Test-Path "workDir") {
    Remove-Item -Recurse -Force "workDir"
}


python -m flask run