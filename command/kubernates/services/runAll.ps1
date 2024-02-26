Start-Process powershell -ArgumentList "-Command", "cd ./source/user;./command/kubernetes/makeAll.ps1"

Start-Process powershell -ArgumentList "-Command", "cd ./source/book;./command/kubernetes/makeAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/tag;./command/kubernetes/makeAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/index;./command/kubernetes/makeAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/content;./command/kubernetes/makeAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/problem;./command/kubernetes/makeAll.ps1"

Start-Process powershell -ArgumentList "-Command", "cd ./source/comment;./command/kubernetes/makeAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/bookShelf;./command/kubernetes/makeAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/bookShelfBook;./command/kubernetes/makeAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/file;./command/kubernetes/makeAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/collectedData;./command/kubernetes/makeAll.ps1"

Start-Process powershell -ArgumentList "-Command", "cd ./source/externalSystemProxy;./command/kubernetes/makeAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/externalSystem;./command/kubernetes/makeAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/recommendCalcSystem;./command/kubernetes/makeAll.ps1"

Start-Process powershell -ArgumentList "-Command", "cd ./source/gateway;./command/kubernetes/makeAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/frontend;./command/kubernetes/makeAll.ps1"