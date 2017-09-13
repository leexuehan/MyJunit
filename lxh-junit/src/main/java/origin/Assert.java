package origin;

import java.util.Observable;

/**
 * Developed by Lee Happily.
 */
public class Assert {
    static public void assertTrue(boolean condition) throws AssertionFailedError {
        if (!condition) {
            fail(null);
        }
    }


    public static void assertEquals(Object expected, Object actual) throws AssertionFailedError {
        assertEquals(null, expected, actual);
    }


    static public void failSame(String message) throws AssertionFailedError {
        String formatted = (message != null) ? message + " " : "";
        fail(formatted + "expected not same");
    }

    public static void assertEquals(String message, Object expected, Object actual) throws AssertionFailedError {
        if (expected == null && actual == null) {
            return;
        }
        if (expected != null && expected.equals(actual)) {
            return;
        }
        fail(format(message, expected, actual));
    }

    /**
     * Fails a test with the given message.
     */
    public static void fail(String message) throws AssertionFailedError {
        if (message == null) {
            throw new AssertionFailedError();
        }
        throw new AssertionFailedError(message);
    }

    public static String format(String message, Object expected, Object actual) {
        String formatted = "";
        if (message != null && message.length() > 0) {
            formatted = message + " ";
        }
        return formatted + "expected:<" + expected + "> but was:<" + actual + ">";
    }

}
