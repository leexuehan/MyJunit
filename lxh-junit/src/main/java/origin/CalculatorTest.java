package origin;

/**
 * Developed by Lee Happily.
 */
public class CalculatorTest {
    public void testCalculator() throws Exception {
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 2);
        shouldEquals(0, result);
    }

    private void shouldEquals(int expect, int actual) throws Exception {
        if (expect != actual) {
            throw new Exception("not equals");
        }else {
            System.out.println("pass test");
        }
    }

}
