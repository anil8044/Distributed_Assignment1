package calculator;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//Single Client code for testing the operations on the stack
public class CalculatorTest {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Calculator a = (Calculator) registry.lookup("CalculatorService");

            System.out.println("Connected to CalculatorService for single-client test.\n");

            // Pushing  values into the stack
            a.pushValue(12);
            a.pushValue(18);
            a.pushValue(6);
            System.out.println("Pushed values: 12, 18, 6");

            // Test GCD
            a.pushOperation("gcd");
            int gcdOutput = a.pop();
            System.out.println("GCD operation result (expect 6): " + gcdOutput);

            // Testing Minimum code
            a.pushValue(10);
            a.pushValue(30);
            a.pushValue(20);
            a.pushOperation("min");
            System.out.println("Min operation result (expect 10): " + a.pop());

            // Test Maximum code
            a.pushValue(10);
            a.pushValue(30);
            a.pushValue(20);
            a.pushOperation("max");
            System.out.println("Max operation result (expect 30): " + a.pop());

            // Test LCM code
            a.pushValue(4);
            a.pushValue(6);
            a.pushOperation("lcm");
            System.out.println("LCM operation result (expect 12): " + a.pop());

            // Test delayPop
            a.pushValue(42);
            int delayed = a.delayPop(7000); // delayed by 7 seconds 
            System.out.println("DelayPop test by (7s) result: " + delayed);

            // Check whether the stack isEmpty or not?
            System.out.println("Stack empty? " + a.isEmpty());

        } catch (Exception e) {
            System.err.println("Single-client test exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
