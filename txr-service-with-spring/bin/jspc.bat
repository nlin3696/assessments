@echo off
rem $Id: jspc.bat,v 1.4.4.1 2000/09/04 21:18:04 larryi Exp $
rem A batch file to run the JspC Compiler

rem This batch file written and tested under Windows NT
rem Improvements to this file are welcome

if not "%TOMCAT_HOME%" == "" goto start

SET TOMCAT_HOME=.
if exist "%TOMCAT_HOME%\bin\tomcat.bat" goto start

SET TOMCAT_HOME=..
if exist "%TOMCAT_HOME%\bin\tomcat.bat" goto start

SET TOMCAT_HOME=
echo Unable to determine the value of TOMCAT_HOME.
goto eof

:start
call "%TOMCAT_HOME%\bin\tomcat" jspc %1 %2 %3 %4 %5 %6 %7 %8 %9

:eof
