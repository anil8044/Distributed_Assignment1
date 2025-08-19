package calculator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorMultiClientTest {

    private static Calculator calculator;

    @BeforeAll
    static void setup() throws Exception {
        // Start RMI registry programmatically on port 1099 (if not already running)
        try {
            LocateRegistry.createRegistry(1099);
        } catch (Exception e) {
            // Registry might already be running
        }

        // Bind server
        CalculatorImplementation impl = new CalculatorImplementation();
        Naming.rebind("rmi://localhost:1099/CalculatorService", impl);

        // Lookup from client
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        calculator = (Calculator) registry.lookup("CalculatorService");
    }

    @Test
    void testMultipleClientsSimultaneously() throws Exception {
        int clientCount = 3;
        CountDownLatch latch = new CountDownLatch(clientCount);

        Runnable clientTask = () -> {
            try {
                // Each client does some operations
                double minVal = calculator.min(5, 10);
                double maxVal = calculator.max(8, 3);
                double gcdVal = calculator.gcd(20, 8);
                double lcmVal = calculator.lcm(4, 6);

                // Print results to console
                System.out.println("Thread " + Thread.currentThread().getName() +
                        " → min=" + minVal + ", max=" + maxVal +
                        ", gcd=" + gcdVal + ", lcm=" + lcmVal);

                // Assertions
                assertEquals(5.0, minVal);
                assertEquals(8.0, maxVal);
                assertEquals(4.0, gcdVal);
                assertEquals(12.0, lcmVal);
            } catch (Exception e) {
                fail("Client failed with exception: " + e.getMessage());
            } finally {
                latch.countDown();
            }
        };

        // Spawn client threads
        for (int i = 0; i < clientCount; i++) {
            new Thread(clientTask, "Client-" + (i + 1)).start();
        }

        // Wait for all clients to finish
        latch.await();
    }
}
