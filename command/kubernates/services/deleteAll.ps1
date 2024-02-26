Start-Process powershell -ArgumentList "-Command", "cd ./source/user;./command/kubernetes/deleteAll.ps1"

Start-Process powershell -ArgumentList "-Command", "cd ./source/book;./command/kubernetes/deleteAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/tag;./command/kubernetes/deleteAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/index;./command/kubernetes/deleteAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/content;./command/kubernetes/deleteAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/problem;./command/kubernetes/deleteAll.ps1"

Start-Process powershell -ArgumentList "-Command", "cd ./source/comment;./command/kubernetes/deleteAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/bookShelf;./command/kubernetes/deleteAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/bookShelfBook;./command/kubernetes/deleteAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/file;./command/kubernetes/deleteAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/collectedData;./command/kubernetes/deleteAll.ps1"

Start-Process powershell -ArgumentList "-Command", "cd ./source/externalSystemProxy;./command/kubernetes/deleteAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/externalSystem;./command/kubernetes/deleteAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/recommendCalcSystem;./command/kubernetes/deleteAll.ps1"

Start-Process powershell -ArgumentList "-Command", "cd ./source/gateway;./command/kubernetes/deleteAll.ps1"
Start-Process powershell -ArgumentList "-Command", "cd ./source/frontend;./command/kubernetes/deleteAll.ps1"