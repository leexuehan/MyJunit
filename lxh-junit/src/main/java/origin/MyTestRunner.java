package origin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Developed by Lee Happily.
 */
public class MyTestRunner {
    public static void main(String[] args) throws Throwable {
        String[] params = new String[]{"origin.TestExampleª"};
        new MyTestRunner().start(params);
    }

    private void start(String[] args) throws Throwable {
        String testCase = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-c")) {
                testCase = args[++i];
            } else {
                testCase = args[i];
            }
        }

        if ("".equals(testCase)) {
            throw new Exception("Usage: TestRunner [-wait] testCaseName, where name is the name of the TestCase class");
        }

        Class<?> testClass;
        try {
            testClass = Class.forName(testCase);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found \"\" + clazz + \"\"");
            System.exit(2);
            return;
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            System.exit(2);
            return;
        }

        long startTime = System.currentTimeMillis();
        runTest(testClass);
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        System.out.println("total cost time:" + runTime + "ms");

    }

    private void runTest(Class<?> theClass) throws Throwable {
        Constructor<?> constructor = theClass.getConstructor();
        Object instance = constructor.newInstance();
        Method[] methods = theClass.getDeclaredMethods();
        for (Method each : methods) {
            try {
                each.invoke(instance);
            } catch (InvocationTargetException e) {
                e.fillInStackTrace();
                throw e.getTargetException();
            } catch (IllegalAccessException e) {
                e.fillInStackTrace();
                throw e;
            }
        }
    }
}
