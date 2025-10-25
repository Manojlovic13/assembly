@echo off

REM Set the classpath
set CLASSPATH=..\lib\*;..\lib\assembly-1.0-SNAPSHOT.jar

REM Run the JAR file
call java -cp "%CLASSPATH%" com.assembly.Main

REM Pause to keep the window open after execution
pause