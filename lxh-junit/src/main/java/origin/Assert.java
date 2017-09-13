package origin;

/**
 * Developed by Lee Happily.
 */
public class Assert {
    public static void assertEquals(Object expected, Object actual) throws AssertionFailedError {
        assertEquals(null, expected, actual);
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
