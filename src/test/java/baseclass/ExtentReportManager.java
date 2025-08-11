package baseclass;

import java.io.File;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance(String classname) {
        if (extent == null) {
            String reportsDirPath = System.getProperty("user.dir") + File.separator + "reports";

            String reportPath = reportsDirPath + File.separator + classname + ".html";

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setDocumentTitle("Automation Report");
            reporter.config().setReportName("Test Results - " + classname);

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Goutham");
            extent.setSystemInfo("Environment", "QA");
        }
        
        return extent;
    }

	
}
