@echo off
rem $Id: shutdown.bat,v 1.5.4.1 2000/09/04 21:18:05 larryi Exp $
rem Startup batch file for tomcat server.

if not "%TOMCAT_HOME%" == "" goto start

SET TOMCAT_HOME=.
if exist "%TOMCAT_HOME%\bin\tomcat.bat" goto start

SET TOMCAT_HOME=..
if exist "%TOMCAT_HOME%\bin\tomcat.bat" goto start

SET TOMCAT_HOME=
echo Unable to determine the value of TOMCAT_HOME.
goto eof

:start
call "%TOMCAT_HOME%\bin\tomcat" stop %1 %2 %3 %4 %5 %6 %7 %8 %9

:eof
