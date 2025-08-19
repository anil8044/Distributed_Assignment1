@echo off
REM ===============================
REM Step 0: Navigate to project folder
REM ===============================
cd /d E:\distributed_Assignment1

REM ===============================
REM Step 1: Clean and compile main classes
REM ===============================
echo Compiling main classes...
mvn clean compile
IF %ERRORLEVEL% NEQ 0 (
    echo Compile failed!
    exit /b
)

REM ===============================
REM Step 2: Compile test classes
REM ===============================
echo Compiling test classes...
mvn test-compile
IF %ERRORLEVEL% NEQ 0 (
    echo Test compile failed!
    exit /b
)

REM ===============================
REM Step 3: Start RMI registry
REM ===============================
echo Starting RMI registry on port 1099...
start cmd /k "cd /d %CD%\target\classes && rmiregistry 1099"
timeout /t 3

REM ===============================
REM Step 4: Start RMI server
REM ===============================
echo Starting RMI server...
start cmd /k "cd /d %CD% && mvn exec:java -Dexec.mainClass=calculator.CalculatorServer"
timeout /t 3

REM ===============================
REM Step 5: Run JUnit tests
REM ===============================
echo Running JUnit tests...
mvn test

echo ===============================
echo ALL DONE
pause
