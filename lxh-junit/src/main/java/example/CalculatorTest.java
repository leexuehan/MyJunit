package example;

import origin.Assert;
import origin.AssertionFailedError;

/**
 * Developed by Lee Happily.
 */
public class CalculatorTest {

    public void testCalculatorShouldFail() throws AssertionFailedError {
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 2);
        Assert.assertEquals(0, result);
    }

    public void testCalculatorShouldSuccess() throws AssertionFailedError {
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 2);
        Assert.assertEquals(3, result);
    }


}
