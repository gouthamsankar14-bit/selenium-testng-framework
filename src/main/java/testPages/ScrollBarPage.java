package testPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reuse.Abstarct;
import reuse.ReportLogger;

public class ScrollBarPage extends Abstarct {
	WebDriver driver;

	public ScrollBarPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Scrollbars")
	WebElement loginLink;

	@FindBy(css = ".btn-primary")
	WebElement hidingButton;

	public void verifyPage() {
		waitForVisiblewebElement(loginLink);
		scrollAndClick(driver, loginLink);
		String tiltle = driver.getTitle();
		System.out.println("Title: " + tiltle);
	}

	public void scrollandTakescreenshot() {
		waitForVisiblewebElement(hidingButton);
		scrolltoElement(driver, hidingButton);
		ReportLogger.logScreenShot(driver, "Button Found");
		

	}

}
