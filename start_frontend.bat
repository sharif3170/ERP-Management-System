@echo off
echo Starting ERP Frontend (HTTP Server)...
echo The app will be available at http://localhost:8081
cd frontend
call npx http-server -p 8081 -c-1
pause
