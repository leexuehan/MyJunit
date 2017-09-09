import java.io.PrintStream;

public class TestRunner {

    private static final int FAILURE_EXIT = 1;
    private static final int SUCCESS_EXIT = 0;
    private static final int EXCEPTION_EXIT = 2;

    private ResultPrinter fPrinter;

    public TestRunner() {
        this(System.out);
    }

    /**
     * Constructs a TestRunner using the given stream for all the output
     */
    public TestRunner(PrintStream writer) {
        this(new ResultPrinter(writer));
    }

    /**
     * Constructs a TestRunner using the given ResultPrinter all the output
     */
    public TestRunner(ResultPrinter printer) {
        fPrinter = printer;
    }


    public static void main(String[] args) throws Exception {
        TestRunner aTestRunner = new TestRunner();
        try {
            TestResult r = aTestRunner.start(args);
            if (!r.wasSuccessful()) {
                System.exit(FAILURE_EXIT);
            }
            System.exit(SUCCESS_EXIT);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(EXCEPTION_EXIT);
        }


    }

    private TestResult start(String[] args) throws Exception {
        String testCase = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-c")) {
                testCase = extractClassName(args[++i]);
            } else {
                testCase = args[i];
            }
        }

        if ("".equals(testCase)) {
            throw new Exception("Usage: TestRunner [-wait] testCaseName, where name is the name of the TestCase class");
        }

        Test suite = getTest(testCase);
        return doRun(suite);

    }

    private static String extractClassName(String className) {
        if (className.startsWith("Default package for")) {
            return className.substring(className.lastIndexOf(".") + 1);
        }
        return className;
    }

    public TestResult doRun(Test suite) {
        TestResult result = createTestResult();
        result.addListener(fPrinter);
        long startTime = System.currentTimeMillis();
        suite.run(result);
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        fPrinter.print(result, runTime);
        return result;
    }

    /**
     * Returns the loaded Class for a suite name.
     */
    private Class<?> loadSuiteClass(String suiteClassName) throws ClassNotFoundException {
        return Class.forName(suiteClassName);
    }

    private Test getTest(String suiteClassName) {
        if (suiteClassName.length() <= 0) {
            return null;
        }
        Class<?> testClass;
        try {
            testClass = loadSuiteClass(suiteClassName);
        } catch (ClassNotFoundException e) {
            String clazz = e.getMessage();
            if (clazz == null) {
                clazz = suiteClassName;
            }
            runFailed("Class not found \"" + clazz + "\"");
            return null;
        } catch (Exception e) {
            runFailed("Error: " + e.toString());
            return null;
        }

        return new TestSuite(testClass);
    }

    /**
     * Creates the TestResult to be used for the test run.
     */
    protected TestResult createTestResult() {
        return new TestResult();
    }

    private void runFailed(String message) {
        System.err.println(message);
        System.exit(FAILURE_EXIT);
    }
}
