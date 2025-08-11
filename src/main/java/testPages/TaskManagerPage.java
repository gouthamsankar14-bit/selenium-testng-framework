package testPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import reuse.Abstarct;
import reuse.ReportLogger;

public class TaskManagerPage extends Abstarct {

	WebDriver driver;

	public TaskManagerPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Dynamic Table")
	WebElement loginLink;
	@FindBy(xpath = "//table[@class='table table-striped']/thead/tr/th")
	List<WebElement> currentHeader;

	@FindBy(xpath = "//table[@class='table table-striped']/tbody/tr")
	List<WebElement> currentRows;
	
	@FindBy(xpath = "(//div[@class='col-md-10']/h2)[2]")
	WebElement Playground;

	public void verifyPage() {
		waitForVisiblewebElement(loginLink);
		scrollAndClick(driver, loginLink);
		System.out.println(driver.getTitle());
	}

	public void getHardwareValue(String tagName, String hardwareColumn, String nameColumn, String nameValue)
			throws InterruptedException {

		boolean valueFound = false;

		for (int run = 0; run < 3; run++) {

			if (run > 0) {
				driver.navigate().refresh();
				waitForVisiblewebElement(Playground);
				scrolltoElement(driver, Playground);
			}
			int hardwareIndex = -1;
			int nameIndex = -1;
			for (int i = 0; i < currentHeader.size(); i++) {
				String columnText = currentHeader.get(i).getText().trim();
				if (columnText.equalsIgnoreCase(hardwareColumn)) {
					hardwareIndex = i;
				} else if (columnText.equalsIgnoreCase(nameColumn)) {
					nameIndex = i;
				}
				if (hardwareIndex != -1 && nameIndex != -1)
					break;
			}

			if (hardwareIndex == -1 || nameIndex == -1) {
				System.out.println("Column not found. Please check column names.");
				return;
			}

			for (WebElement row : currentRows) {
				List<WebElement> cells = row.findElements(By.tagName(tagName));
				if (cells.size() <= Math.max(hardwareIndex, nameIndex))
					continue;

				String currentName = cells.get(nameIndex).getText().trim();

				if (currentName.equalsIgnoreCase(nameValue)) {
					String value = cells.get(hardwareIndex).getText().trim();
					System.out.println(nameValue + " " + hardwareColumn + " is at : " + value);
					ReportLogger.logInfo(nameValue + " " + hardwareColumn + " is at : " + value);
					scrolltoElement(driver, row);
					ReportLogger.logScreenShot(driver, nameValue + " " + hardwareColumn + " is at : " + value);
					valueFound = true;
					break; 
				}
			}
		}

		if (!valueFound) {
			ReportLogger.logFail("Value for " + nameValue + " not found in the table.");
			Assert.fail(nameValue + " with " + hardwareColumn + " not found ");
		}
	}

}
