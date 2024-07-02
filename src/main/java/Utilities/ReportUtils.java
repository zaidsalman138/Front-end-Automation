package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

@SuppressWarnings("deprecation")
public class ReportUtils {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    /**
     * Initializes the ExtentReports with the HTML reporter
     */
    
    public static void initReport() {
        // Ensure PropertyReader is properly implemented to fetch reportPath and reportName
        PropertyReader propertyReader = new PropertyReader();
        String reportPath = propertyReader.getProperty("reportPath");
        String reportName = propertyReader.getProperty("reportName");

        if (reportPath == null || reportName == null) {
            throw new IllegalArgumentException("Report path or name is not provided");
        }

        
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath + reportName + ".html");
        htmlReporter.config().setDocumentTitle("Automation Test Results");
        htmlReporter.config().setReportName("Test Execution Report");
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Application", "Test Application");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "Test User");
    }

    /**
     * Starts a test.
     *
     * @param testName the name of the test
     * @return the ExtentTest instance
     */
    public static ExtentTest startTest(String testName) {
        if (extent == null) {
            throw new IllegalStateException("ExtentReports is not initialized. Call initReport() first.");
        }
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
        return extentTest;
    }

    /**
     * Ends the test.
     */
    public static void endTest() {
        if (extent == null) {
            throw new IllegalStateException("ExtentReports is not initialized. Call initReport() first.");
        }
        extent.flush();
    }

    /**
     * Gets the thread-local ExtentTest instance.
     *
     * @return the ExtentTest instance for the current thread
     */
    public static ExtentTest getTest() {
        if (test.get() == null) {
            throw new IllegalStateException("ExtentTest is not initialized for this thread. Call startTest() first.");
        }
        return test.get();
    }
}
