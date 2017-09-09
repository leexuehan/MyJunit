import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * A <code>TestResult</code> collects the results of executing
 * a test case. It is an instance of the Collecting Parameter pattern.
 * The test framework distinguishes between <i>failures</i> and <i>errors</i>.
 * A failure is anticipated and checked for with assertions. Errors are
 * unanticipated problems like an {@link ArrayIndexOutOfBoundsException}.
 */
public class TestResult {
    protected List<TestFailure> fFailures;
    protected List<TestFailure> fErrors;
    protected List<TestListener> fListeners;
    protected int fRunTests;


    /**
     * Registers a TestListener.
     */
    public synchronized void addListener(TestListener listener) {
        fListeners.add(listener);
    }

    /**
     * Unregisters a TestListener.
     */
    public synchronized void removeListener(TestListener listener) {
        fListeners.remove(listener);
    }

    public boolean wasSuccessful() {
        return false;
    }

    public Enumeration<TestFailure> failures() {
        return Collections.enumeration(fFailures);
    }

    public int failureCount() {
        return fFailures.size();
    }

    public int runCount() {
        return fRunTests;
    }

    public int errorCount() {
        return fErrors.size();
    }

    public Enumeration<TestFailure> errors() {
        return Collections.enumeration(fErrors);
    }
}
