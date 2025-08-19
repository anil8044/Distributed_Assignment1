package calculator;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Stack;

public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {
//stack stores integer values for its operations
    private final Stack<Integer> stack;

    public CalculatorImplementation() throws RemoteException {
        super();
        stack = new Stack<>();// A new stack will be created and we will perform the operations.
    }

    //  Stack Methods 
    //synchronized is used so that multiple clients cannot access the stack.
    @Override
    public synchronized void pushValue(int val) throws RemoteException {
        stack.push(val);//we will push value into the stack
    }

    @Override
    public synchronized void pushOperation(String operator) throws RemoteException {
        if (stack.isEmpty()) return;
        int output = stack.pop();
        while (!stack.isEmpty()) {
            int value = stack.pop();
            switch (operator.toLowerCase()) {
                case "min": output = Math.min(output, value); break;
                case "max": output = Math.max(output, value); break;
                case "gcd": output = (int) gcd(output, value); break;
                case "lcm": output = (int) lcm(output, value); break;
                default: throw new RemoteException("Unknown operation: " + operator);
            }
        }
        stack.push(output);// we will push the output to the stack
    }
    
    //check whether stack is empty 
    @Override
    public synchronized int pop() throws RemoteException {
        if (stack.isEmpty()) throw new RemoteException("Stack is empty.");
        return stack.pop();
    }

    @Override
    public synchronized boolean isEmpty() throws RemoteException {
        return stack.isEmpty();
    }

    	// delayPop is used to delay the Pop operation by Milli seconds
    @Override
    public synchronized int delayPop(int millis) throws RemoteException, InterruptedException {
        Thread.sleep(millis);
        return pop();
    }

    // ---- Math Methods ----
    	// to get gcd of two numbers
    @Override
    public double gcd(int s, int t) throws RemoteException {
        s = Math.abs(s);
        t = Math.abs(t);
            if (t == 0) {
                return s;
            }
            return gcd(t, s % t);
        }

    // to get lcm of two numbers
    @Override
    public double lcm(int s, int t) throws RemoteException {
        if (s == 0 || t == 0) return 0;
        return Math.abs((double) s / gcd(s, t) * t);
    }
    // to get minimum value
    @Override
    public double min(double s, double t) throws RemoteException {
        return Math.min(s, t);
    }
    // to get maximum value
    @Override
    public double max(double s, double t) throws RemoteException {
        return Math.max(s, t);
    }
}
