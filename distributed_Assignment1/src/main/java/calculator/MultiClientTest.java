package calculator;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MultiClientTest {

    private static final int TOTAL_CLIENTS = 4;

    public static void main(String[] args) {
        try {
            // 1. Get the RMI registry running on localhost (default port 1099)
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // 2. Lookup the remote Calculator service
            Calculator calc = (Calculator) registry.lookup("CalculatorService");

            System.out.println("Connected to CalculatorService for multi-client test.");

            Thread[] clients = new Thread[TOTAL_CLIENTS];
            // using for loop for multi clients
            for (int i = 0; i < TOTAL_CLIENTS; i++) {
                final int clientId = i;
                
                //capturing calc method in lambda by making it final effectively
                clients[i] = new Thread(() -> {
                    try {
                        // Each client pushes two values
                        calc.pushValue(10 + clientId * 10); // 10, 20, 30, 40
                        calc.pushValue(5 + clientId * 5);   // 5, 10, 15, 20

                        // Decide operation based on client ID
                        String op;
                        switch (clientId) {
                            case 0 -> op = "min";
                            case 1 -> op = "max";
                            case 2 -> op = "gcd";
                            case 3 -> op = "lcm";
                            default -> op = "min";
                        }

                        // Apply operation
                        calc.pushOperation(op);

                        // Pop result
                        int result = calc.pop();
                        System.out.println("Client-" + clientId + " applied " + op + ", got: " + result);

                    } catch (RemoteException e) {
                        System.out.println("Client-" + clientId + " exception: " + e.getMessage());
                    }
                });
            }

            // Starting all Client Threads
            for (Thread t : clients) t.start();
           // waiting for all Clients to finish
            for (Thread t : clients) t.join();

            // Final stack status
            System.out.println("\nMulti-client test complete.");
            System.out.println("Stack empty? " + calc.isEmpty());

        } catch (Exception e) {
            System.err.println("MultiClientTest exception: " + e);
            e.printStackTrace();
        }
    }
}
