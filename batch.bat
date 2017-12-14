@ echo off
@ echo "step one.pack project: app"
call gradlew.bat clean appRelease -p app
@ echo "step two.pack project: plugin-one"
call gradlew.bat clean assemblePlugin -p plugin-one
@ echo "step three.pack release: zipRelease"
call gradlew.bat zipRelease
@ echo.