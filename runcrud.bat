call gradlew build
if "%ERRORLEVEL%" == "0" goto rename
echo.
echo GRADLEW BUILD ma błędy - praca przerwana
goto fail

:rename
del build\libs\crud.war
ren build\libs\tasks-0.0.2-SNAPSHOT.war crud.war
if "%ERRORLEVEL%" == "0" goto stoptomcat
echo Nie można zastopować Tomcat z powodu błedów w zmianie nazwy
goto fail

:stoptomcat
call %CATALINA_HOME%\bin\shutdown.bat

:copyfile
copy build\libs\crud.war %CATALINA_HOME%\webapps
if "%ERRORLEVEL%" == "0" goto runtomcat
echo Nie mogę skopiować pliku
goto fail

:runtomcat
call %CATALINA_HOME%\bin\startup.bat
goto end

:fail
echo.
echo Znaleziono błędy

:end
echo.
echo Praca wykonana.