# Distributed_Assignment1
In this Assignment we are creating a RMI calculator using java RMI

•	It supports stack operations and arithmetic operations also, and Multiple clients can connect to the same server also.

•	I had written my code in Eclipse Ide compiled and I had also compiled it using the windows Power shell.

Steps to compile:

	Since it is a Maven Project we need java 17 or later

Step 1: Copy the root directory and change the command to our root in power shell and rum the server program to start the rmi registry
Here I saved the program in C drive you can change your path where you saved it.

&nbsp;			C:\Users\anilg\Downloads\Distributed_Assignment1\distributed_Assignment1\src\main\java

&nbsp;       javac *.java    //This command will compile all java Files prseent in that path

&nbsp;				java -cp . calculator.CalculatorServer  //RMI registry will get started



Step 2: Now The server is ready Open new  terminal Window and run the client.

&nbsp;	**java -cp . calculator.CalculatorClient**

&nbsp; // Make sure to open it in new terminal or window. 

Here client can perform the actions also

Step 3: now we need to test the code we have writen whether it is working as intended or not.


&nbsp;	C:\Users\anilg\Downloads\Distributed_Assignment1\distributed_Assignment1\src\main\java
then give this command to test the code it gives us the test cases whether passed or failed

&nbsp;		java -cp . calculator.CalculatorTest.java // for single Client Calculator Test	

&nbsp;    java -cp . calculator.MultiClientTest  // for multi Client Calculator Test

We can see the Test cases have passed for our Test Programs.




