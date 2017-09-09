import java.io.PrintStream;
import java.text.NumberFormat;
import java.util.Enumeration;

public class ResultPrinter implements TestListener {
    PrintStream fWriter;

    public ResultPrinter(PrintStream writer) {
        fWriter = writer;
    }


    public void addError(Test test, Throwable e) {

    }

    public void addFailure(Test test, AssertionFailedError e) {

    }

    public void endTest(Test test) {

    }

    public void startTest(Test test) {

    }

    synchronized void print(TestResult result, long runTime) {
        printHeader(runTime);
        printErrors(result);
        printFailures(result);
        printFooter(result);
    }

    /* Internal methods */

    protected void printHeader(long runTime) {
        getWriter().println();
        getWriter().println("Time: " + elapsedTimeAsString(runTime));
    }

    protected String elapsedTimeAsString(long runTime) {
        return NumberFormat.getInstance().format((double) runTime / 1000);
    }

    protected void printErrors(TestResult result) {
        printDefects(result.errors(), result.errorCount(), "error");
    }

    protected void printFailures(TestResult result) {
        printDefects(result.failures(), result.failureCount(), "failure");
    }

    protected void printFooter(TestResult result) {
        if (result.wasSuccessful()) {
            getWriter().println();
            getWriter().print("OK");
            getWriter().println(" (" + result.runCount() + " test" + (result.runCount() == 1 ? "" : "s") + ")");

        } else {
            getWriter().println();
            getWriter().println("FAILURES!!!");
            getWriter().println("Tests run: " + result.runCount() +
                    ",  Failures: " + result.failureCount() +
                    ",  Errors: " + result.errorCount());
        }
        getWriter().println();
    }

    public PrintStream getWriter() {
        return fWriter;
    }

    protected void printDefects(Enumeration<TestFailure> booBoos, int count, String type) {
        if (count == 0) return;
        if (count == 1) {
            getWriter().println("There was " + count + " " + type + ":");
        } else {
            getWriter().println("There were " + count + " " + type + "s:");
        }
        for (int i = 1; booBoos.hasMoreElements(); i++) {
            printDefect(booBoos.nextElement(), i);
        }
    }

    public void printDefect(TestFailure booBoo, int count) { // only public for testing purposes
        printDefectHeader(booBoo, count);
        printDefectTrace(booBoo);
    }

    protected void printDefectHeader(TestFailure booBoo, int count) {
        // I feel like making this a println, then adding a line giving the throwable a chance to print something
        // before we get to the stack trace.
        getWriter().print(count + ") " + booBoo.failedTest());
    }

    protected void printDefectTrace(TestFailure booBoo) {
//        getWriter().print(BaseTestRunner.getFilteredTrace(booBoo.trace()));
    }
}
