.\venv\Scripts\activate

$env:COLLECTED_DATA_HOST = "localhost"
$env:COLLECTED_DATA_PORT = "8093"
cp envs/development.env .flaskenv


if (Test-Path "log") {
    Remove-Item -Recurse -Force "log"
}

if (Test-Path "workDir") {
    Remove-Item -Recurse -Force "workDir"
}


python -m flask run