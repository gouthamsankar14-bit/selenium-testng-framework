package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;

import baseclass.DriverSetup;
import reuse.Abstarct;
import reuse.ExtentTestManager;

public class listen extends DriverSetup implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = DriverSetup.getdriver();
		String methodName = result.getMethod().getMethodName();

		if (driver != null) {
			String screenshotPath = Abstarct.takeScreenshot(driver, methodName);
			ExtentTestManager.getTest().fail("Test Failed: " + result.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} else {
			ExtentTestManager.getTest().fail("Driver was null, screenshot skipped.");
		}
	}

	@Override
	public void onFinish(ITestContext context) {	
		if (DriverSetup.extent != null) {
	        DriverSetup.extent.flush();
	    }
	}
}
