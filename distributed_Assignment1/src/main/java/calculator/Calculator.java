package calculator;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
	//push value is of type integer 
	void pushValue(int val) throws RemoteException;
	// user entered value is pushed onto the stack.
	void pushOperation(String operator) throws RemoteException;
	// Pop is used to Pop the elements from the stack
	int pop() throws RemoteException;
	// isEmpty is used to check whether the stack is empty or not. 
	boolean isEmpty() throws RemoteException;
	// delay pop is used to delay the pop operation by milli seconds
	int delayPop(int millis) throws RemoteException, InterruptedException;
	double gcd(int num1, int num2) throws RemoteException;
	double lcm(int num1, int num2) throws RemoteException;
	double min(double num1, double num2) throws RemoteException;
	double max(double num1, double num2) throws RemoteException;
	
	
	
	
	
	
	
}


