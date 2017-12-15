@ echo off
@ echo "pack project: cleanPackRelease"
call gradlew.bat clean cleanPackRelease
@ echo "pack project:init host"
call gradlew.bat clean assembleRelease -p app
@ echo "pack project:plugin-> plugin-one"
call gradlew.bat clean assemblePlugin -p plugin-one
@ echo "pack project:plugin-> assets"
call gradlew.bat assetsPlugin
@ echo "pack project:host-> app"
call gradlew.bat clean appRelease -p app
@ echo "pack release: zipRelease"
call gradlew.bat zipRelease
@ echo.