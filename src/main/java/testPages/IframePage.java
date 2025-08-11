package testPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import reuse.Abstarct;
import reuse.ReportLogger;

public class IframePage extends Abstarct {
	WebDriver driver;

	public IframePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "IFrame")
	WebElement loginLink;
	@FindBy(id = "email-subscribe")
	WebElement frame;
	@FindBy(css = ".form-control")
	WebElement textbox;
	@FindBy(id = "btn-subscribe")
	WebElement Subscribe;
	@FindBy(css = ".invalid-feedback")
	WebElement invalidation;
	@FindBy(id = "success-message")
	WebElement successmsg;
	String errmsg = "Please enter a valid email address.";
	String success = "You are now subscribed!";

	public void verifyPage() {
		waitForVisiblewebElement(loginLink);
		scrollAndClick(driver, loginLink);
		String tiltle = driver.getTitle();
		System.out.println("Title: " + tiltle);
	}

	public void internalFrame(String mail) {

		driver.switchTo().frame(frame);
		waitForVisiblewebElement(textbox);
		scrolltoElement(driver, textbox);
		textbox.sendKeys(mail);
		String value = textbox.getAttribute("value").trim();
		scrollAndClick(driver, Subscribe);
		

		if (value.isBlank()) {
			waitForVisiblewebElement(invalidation);
			String invalid = invalidation.getText().trim();
			if (invalidation.isDisplayed() && invalid.contains(errmsg)) {
				ReportLogger.logPass(invalid + " is displayed when field is empty");
				ReportLogger.logScreenShot(driver, invalid + " is displayed when field is empty");
			} else {
				ReportLogger.logFail("Validation message text mismatch or not displayed");			
				Assert.fail();
			}
		}
		if (!value.isBlank()) {
			waitForVisiblewebElement(successmsg);
			String valid = successmsg.getText().trim();
			if (successmsg.isDisplayed() && valid.contains(success)) {
				ReportLogger.logPass(valid + " is displayed when field is not empty");
				ReportLogger.logScreenShot(driver, valid + " is displayed when field is not empty");
			} else {
				ReportLogger.logFail("Success message text mismatch or not displayed");
				Assert.fail();
			}
		}
		driver.switchTo().defaultContent();
	}
}
