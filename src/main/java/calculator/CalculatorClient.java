package calculator;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

// This is client server, client will use this to connect the server and perform the operations.
public class CalculatorClient {

    public static void main(String[] args) {
        try {
            // look up for the calculator service
        	Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Calculator input = (Calculator) registry.lookup("CalculatorService");

            Scanner sc = new Scanner(System.in);
            boolean updating = true;

            while (updating) {
                System.out.println("\n---> RMI Calculator <---");
                System.out.println("1. Push Value into Stack");
                System.out.println("2. Push Operation (min, max, gcd, lcm)");
                System.out.println("3. Pop from Stack");
                System.out.println("4. Check if Stack is Empty or not");
                System.out.println("5. Delay Pop");
                System.out.println("6. Math Operations (gcd, lcm, min, max)");
                System.out.println("7. Exit");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();
                //switch case is used to switch the choices 
                switch (choice) {
                    case 1:
                        System.out.print("Enter a value: ");
                        int val = sc.nextInt();
                        input.pushValue(val);
                        System.out.println("Value pushed.");
                        break;

                    case 2:
                        System.out.print("Enter operation (min, max, gcd, lcm): ");
                        String op = sc.next();
                        try {
                            input.pushOperation(op);
                            System.out.println("Operation applied.");
                        } catch (RemoteException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 3:
                        if (!input.isEmpty()) {
                            int popped = input.pop();
                            System.out.println("Popped: " + popped);
                        } else {
                            System.out.println("Stack is empty.");
                        }
                        break;

                    case 4:
                        System.out.println("Stack empty? " + input.isEmpty());
                        break;
                        // delay is used to delay the pop operation onto the stack.
                    case 5:
                        System.out.print("Enter delay in ms: ");
                        int delay = sc.nextInt();
                        int result = input.delayPop(delay);
                        System.out.println("Popped after delay: " + result);
                        break;
                        // here we are using equalsIgnoreCase because it ignores whether its lower or upper case.
                    case 6:
                        System.out.print("Choose math operation (gcd, lcm, min, max): ");
                        String operation = sc.next();
                        if (operation.equalsIgnoreCase("gcd")) {
                            System.out.print("Enter first int: ");
                            int a = sc.nextInt();
                            System.out.print("Enter second int: ");
                            int b = sc.nextInt();
                            System.out.println("GCD: " + input.gcd(a, b));
                        } else if (operation.equalsIgnoreCase("lcm")) {
                            System.out.print("Enter first int: ");
                            int a = sc.nextInt();
                            System.out.print("Enter second int: ");
                            int b = sc.nextInt();
                            System.out.println("LCM: " + input.lcm(a, b));
                        } else if (operation.equalsIgnoreCase("min")) {
                            System.out.print("Enter first double: ");
                            double x = sc.nextDouble();
                            System.out.print("Enter second double: ");
                            double y = sc.nextDouble();
                            System.out.println("Min: " + input.min(x, y));
                        } else if (operation.equalsIgnoreCase("max")) {
                            System.out.print("Enter first double: ");
                            double x = sc.nextDouble();
                            System.out.print("Enter second double: ");
                            double y = sc.nextDouble();
                            System.out.println("Max: " + input.max(x, y));
                        } else {
                            System.out.println("Invalid operation.");
                        }
                        break;

                    case 7:
                        updating = false;
                        System.out.println("Exit");
                        break;
                        // if user enters than other above cases it will comes into default
                    default:
                        System.out.println("Please enter from the above numbers only.");
                }
            }

            sc.close();

        } catch (Exception e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
