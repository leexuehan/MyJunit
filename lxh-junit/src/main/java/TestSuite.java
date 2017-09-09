import java.lang.reflect.Constructor;
import java.util.Vector;

/**
 * A <code>TestSuite</code> is a <code>Composite</code> of Tests.
 * It runs a collection of test cases. Here is an example using
 * the dynamic test definition.
 * <pre>
 * TestSuite suite= new TestSuite();
 * suite.addTest(new MathTest("testAdd"));
 * suite.addTest(new MathTest("testDivideByZero"));
 * </pre>
 * <p>
 * Alternatively, a TestSuite can extract the tests to be run automatically.
 * To do so you pass the class of your TestCase class to the
 * TestSuite constructor.
 * <pre>
 * TestSuite suite= new TestSuite(MathTest.class);
 * </pre>
 * <p>
 * This constructor creates a suite with all the methods
 * starting with "test" that take no arguments.
 * <p>
 * A final option is to do the same for a large array of test classes.
 * <pre>
 * Class[] testClasses = { MathTest.class, AnotherTest.class };
 * TestSuite suite= new TestSuite(testClasses);
 * </pre>
 */
public class TestSuite implements Test {
    private Vector<Test> fTests = new Vector<Test>(10);


    /**
     * Constructs a TestSuite from the given class. Adds all the methods
     * starting with "test" as test cases to the suite.
     * Parts of this method were written at 2337 meters in the Hueffihuette,
     * Kanton Uri
     */
    public TestSuite(final Class<?> theClass) {
        addTestsFromTestCase(theClass);
    }

    private void addTestsFromTestCase(Class<?> theClass) {
        Constructor<?> constructor;
        try {
            getTestConstructor(theClass);
        } catch (NoSuchMethodException e) {
            addTest(warning("Class " + theClass.getName() + " has no public constructor TestCase(String name) or TestCase()"));
            return;
        }
    }

    private void addTest(Test warning) {

    }


    public int countTestCases() {
        return 0;
    }

    public void run(TestResult result) {
        for (Test each : fTests) {
            runTest(each, result);
        }

    }

    public void runTest(Test test, TestResult result) {
        test.run(result);
    }

    /**
     * Gets a constructor which takes a single String as
     * its argument or a no arg constructor.
     */
    public static Constructor<?> getTestConstructor(Class<?> theClass) throws NoSuchMethodException {
        try {
            return theClass.getConstructor(String.class);
        } catch (NoSuchMethodException e) {
            // fall through
        }
        return theClass.getConstructor();
    }

    /**
     * Returns a test which will fail and log a warning message.
     */
    public static Test warning(final String message) {
        return null;
//        return new TestCase("warning") {
//            @Override
//            protected void runTest() {
//                fail(message);
//            }
//        };
    }
}
