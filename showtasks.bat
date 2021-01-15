call runcrud.bat
if "%ERRORLEVEL%" == "0" goto firefox
echo.
echo Błąd przy wstawieniu aplikacji do serwera tomcat
:firefox
CD C:\Program Files\Mozilla Firefox\
start http://localhost:8080/crud/v1/task/getTasks \firefox.exe