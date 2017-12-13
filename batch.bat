@ echo off
@ echo "pack project: app"
call gradlew.bat clean assembleRelease -p app
@ echo "pack project: plugin-one"
call gradlew.bat clean assemblePlugin -p plugin-one
@ echo.