package testPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import reuse.Abstarct;
import reuse.ReportLogger;

public class TestRegisterPage extends Abstarct {
	WebDriver driver;

	public TestRegisterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Test Register Page")
	WebElement loginLink;

	@FindBy(id = "username")
	WebElement username;
	@FindBy(id = "password")
	WebElement password;
	@FindBy(id = "confirmPassword")
	WebElement confirmPassword;
	@FindBy(css = ".btn-primary")
	WebElement Register;
	@FindBy(id = "flash")
	WebElement validation;
	String msg;
	String rerun = "An error occurred during registration. Please try again.";

	public void regPageVerification() {
		waitForVisiblewebElement(loginLink);
		loginLink.click();
		System.out.println(driver.getTitle());
	}

	public void register(String user, String pass, String confpass) {

		username.clear();
		password.clear();
		username.sendKeys(user);
		password.sendKeys(pass);
		confirmPassword.sendKeys(confpass);
		scrollAndClick(driver, Register);
		msg = validation.getText().trim();
		ReportLogger.logInfo(msg);
		ReportLogger.logScreenShot(driver, msg);
		if (rerun.contains(msg)) {
			Assert.fail("Test failed due to unexpected error");
		}
		System.out.println(msg);

	}

	public void LoginWithNewUser(String user, String pass, String confpass) {
		username.clear();
		password.clear();
		username.sendKeys(user);
		password.sendKeys(pass);
		confirmPassword.sendKeys(confpass);
		scrollAndClick(driver, Register);
		LoginPage log = new LoginPage(driver);
		log.loginValidation(user, pass);
	}

}
