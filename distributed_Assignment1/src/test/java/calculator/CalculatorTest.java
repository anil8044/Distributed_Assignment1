package calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
// this is used to test  whether the code we have written is working or not as we needed
    @Test
    void testMin() throws Exception {
        CalculatorImplementation calc = new CalculatorImplementation();
        assertEquals(3, calc.min(3, 7));
        assertEquals(-5, calc.min(-5, 2));
    }

    @Test
    void testMax() throws Exception {
        CalculatorImplementation calc = new CalculatorImplementation();
        assertEquals(7, calc.max(3, 7));
        assertEquals(1, calc.max(-100, 1));
    }

    @Test
    void testGcd() throws Exception {
        CalculatorImplementation calc = new CalculatorImplementation();
        assertEquals(6, calc.gcd(54, 24));
        assertEquals(1, calc.gcd(7, 5));
    }
    // test Lcm is used 

    @Test
    void testLcm() throws Exception {
        CalculatorImplementation calc = new CalculatorImplementation();
        assertEquals(216, calc.lcm(54, 24));
        assertEquals(35, calc.lcm(7, 5));
    }

    @Test
    void testStackPushAndPop() throws Exception {
        CalculatorImplementation calc = new CalculatorImplementation();
        calc.pushValue(57);
        calc.pushValue(80);

        assertFalse(calc.isEmpty());
        assertEquals(80, calc.pop());
        assertEquals(57, calc.pop());
        assertTrue(calc.isEmpty());
    }

    @Test
    void testDelayPop() throws Exception {
        CalculatorImplementation calc = new CalculatorImplementation();
        calc.pushValue(42);
        int result = calc.delayPop(200); // waits 200 ms before popping
        assertEquals(42, result);
        assertTrue(calc.isEmpty());
    }
}
