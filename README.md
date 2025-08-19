# Distributed_Assignment1
In this Assignment we are creating a RMI calculator using java RMI

•	It supports stack operations and arithmetic operations also, and Multiple clients can connect to the same server also.

•	I had written my code in Eclipse Ide compiled and I had also compiled it using the windows Power shell.

Steps to compile:

	Since it is a Maven Project we need java 17 or later

Step 1: Copy the root directory and change the command to our root in power shell.

&nbsp;			cd E:\\distributed\_Assignment1

&nbsp;				**mvn clean compile**

&nbsp;

Step 2: Now copy the directory path where our class flies are saved and we will start the RMI Registry.

&nbsp;			cd E:\\distributed\_Assignment1\\src\\main\\java

&nbsp;	**javac (Get-ChildItem -Path calculator -Filter \*.java | ForEach-Object { $\_.FullName })**

&nbsp;	**java -cp . calculator.CalculatorServer**



Step 3: Now The server is ready Open new Window and run the client.

&nbsp;	**java -cp . calculator.CalculatorClient**

&nbsp;



Here client can perform the actions also

Step 4: now we need to test the code we have writen whether it is working as intended or not.

	Before that we need to come to our root directory because maven needs to read POM.XML file.

&nbsp;	E:\\distributed\_Assignment1

&nbsp;		**mvn test**	

&nbsp;

We can see the Test cases have passed for our Test Programs.




