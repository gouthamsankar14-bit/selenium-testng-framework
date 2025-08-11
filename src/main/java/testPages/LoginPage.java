package testPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reuse.Abstarct;
import reuse.ReportLogger;

public class LoginPage extends Abstarct {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Test Login Page")
	WebElement loginLink;
	@FindBy(css = "div[class='container'] h1")
	WebElement verifyTestPage;
	@FindBy(id = "username")
	WebElement username;
	@FindBy(id = "password")
	WebElement password;
	@FindBy(css = ".btn-primary")
	WebElement loginButton;
	@FindBy(css = "div[class='container'] h4")
	WebElement verifyAfterLogin;
	@FindBy(css = ".secondary")
	WebElement logoutButton;
	@FindBy(id = "flash")
	WebElement validation;

	public void verifyPage() {
		waitForVisiblewebElement(loginLink);
		loginLink.click();
		String tiltle = driver.getTitle();
		System.out.println("Title: " + tiltle);
	}

	public void loginValidation(String user, String pass) {
		username.clear();
		password.clear();
		username.sendKeys(user);
		password.sendKeys(pass);
		scrollAndClick(driver, loginButton);
		waitForVisiblewebElement(validation);
		String msg = validation.getText().trim();
		if (msg.contains("Your password is invalid!")) {
			//ReportLogger.logInfo(msg);
			ReportLogger.logScreenShot(driver, msg);
			System.out.println("Test pass: invalid Password");
		} else if (msg.contains("Your username is invalid!")) {
			//ReportLogger.logInfo(msg);
			ReportLogger.logScreenShot(driver, msg);
			ReportLogger.logScreenShot(driver, msg);
			System.out.println("Test pass: invalid UserName");
		} else if (msg.contains("You logged into a secure area!")) {
			//ReportLogger.logInfo(msg);
			ReportLogger.logScreenShot(driver, msg);
			waitForVisiblewebElement(logoutButton);
			System.out.println("Test pass: valid Creadentials");
			if (logoutButton.isDisplayed()) {
				scrollAndClick(driver, logoutButton);
				ReportLogger.logScreenShot(driver, msg);
			}
		}
	}
}
