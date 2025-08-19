package calculator;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// this is server's main class, It is used to bind the rmi registry
public class CalculatorServer {
    public static final String Name = "CalculatorService";
    //final is used because 
    // here the bind name we gave is CalculatorService
    public static void main(String[] args) {
        try {
            // the default port is 1099, the registry will start on this port
            try {
                LocateRegistry.createRegistry(1099);
                System.out.println("The Registry is created on port no 1099");
            } catch (Exception e) {
                System.out.println("Registry is already running, stop is or try using another port");
            }

            CalculatorImplementation impl = new CalculatorImplementation();
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(Name, impl);
            System.out.println("CalculatorService bound. Server ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
