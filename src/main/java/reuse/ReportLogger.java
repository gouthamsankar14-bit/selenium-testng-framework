package reuse;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class ReportLogger extends Abstarct {

    public ReportLogger(WebDriver driver) {
		super(driver);
	}

	public static void logInfo(String message) {
        ExtentTestManager.getTest().log(Status.INFO, message);
    }

    public static void logPass(String message) {
        ExtentTestManager.getTest().log(Status.PASS, message);
    }

    public static void logFail(String message) {
        ExtentTestManager.getTest().log(Status.FAIL, message);
    }
    public static void logScreenShot(WebDriver driver, String name) {
        String screenshotPath = takeScreenshot(driver, name);

        ExtentTestManager.getTest().info(
		    name, // This will appear as the caption
		    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build()
		);
    }


	
}
