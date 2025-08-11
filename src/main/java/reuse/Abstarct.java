package reuse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Abstarct {
	WebDriver driver;

	public Abstarct(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForVisiblewebElement(WebElement findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findby));

	}

	public void waitForAllVisiblewebElement(List<WebElement> findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(findby));

	}

	public void scrollAndClick(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
		js.executeScript("arguments[0].click();", element);
	}

	public void scrolltoElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public static String takeScreenshot(WebDriver driver, String fileName) {

	    // Sanitize the filename
	    fileName = fileName.replaceAll("[\\\\/:*?\"<>|]", "_");

	    // Add timestamp + thread ID
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String fullFileName = fileName + "_" + timeStamp + "_T" + Thread.currentThread().getId() + ".png";

	    // Save screenshots inside the same folder as the report (for relative linking)
	    String reportFolder = System.getProperty("user.dir") + File.separator + "reports";
	    String screenshotFolder = reportFolder + File.separator + "screenshots";
	    String filePath = screenshotFolder + File.separator + fullFileName;

	    // Capture screenshot
	    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    File destFile = new File(filePath);

	    // Create folder if it doesn't exist
	    destFile.getParentFile().mkdirs();

	    try {
	        FileUtils.copyFile(srcFile, destFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Return RELATIVE path (for HTML accessibility)
	    return "screenshots" + File.separator + fullFileName;
	}


}
