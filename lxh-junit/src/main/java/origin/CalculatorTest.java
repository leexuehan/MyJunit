package origin;

/**
 * Developed by Lee Happily.
 */
public class CalculatorTest {
    public void testCalculatorShouldFail() throws Exception {
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 2);
        shouldEquals(0, result);
    }

    public void testCalculatorShouldSuccess() throws Exception {
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 2);
        shouldEquals(3, result);
    }


    private void shouldEquals(int expect, int actual) throws Exception {
        if (expect != actual) {
            System.out.println("Test Failed: actual is " + actual + ", expect is: " + expect);
        } else {
            System.out.println("Test Passed");
        }
    }

}
