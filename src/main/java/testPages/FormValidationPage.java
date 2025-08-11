package testPages;

import java.util.List;

import org.testng.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reuse.Abstarct;
import reuse.ReportLogger;
import reuse.SelectClass;

public class FormValidationPage extends Abstarct {
	WebDriver driver;

	public FormValidationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Form Validation")
	WebElement loginLink;
	@FindBy(id = "validationCustom01")
	WebElement ContactName;
	@FindBy(id = "validationCustom05")
	WebElement ContactNumber;
	@FindBy(xpath = "//input[@type='date']")
	WebElement PickUpDate;
	@FindBy(xpath = "//select[@id='validationCustom04']/option")
	List<WebElement> PaymentMethodList;
	@FindBy(xpath = "//select[@class='form-select']")
	WebElement dropdown;
	@FindBy(css = ".btn-primary")
	WebElement rigister;
	@FindBy(css = ".alert.alert-info")
	WebElement conformMsg;
	@FindBy(css = ".invalid-feedback")
	List<WebElement> invalidValidation;
	String contactinvalid = "Please enter your Contact name.";
	String contactNumInvalid = "Please provide your Contact number.";
	String dateInvalid = "Please provide valid Date.";
	String paymentInvalid = "Please select the Paymeny Method.";

	public void verifyPage() {
		waitForVisiblewebElement(loginLink);
		scrollAndClick(driver, loginLink);
		String tiltle = driver.getTitle();
		System.out.println("Title: " + tiltle);
	}

	public void validRegister(String name, String number, String date, String index) {
		boolean found = false;
		ContactName.clear();
		ContactName.sendKeys(name);

		ContactNumber.sendKeys(number);
		PickUpDate.sendKeys(date);
		for (WebElement method : PaymentMethodList) {
			String gotmethod = method.getText().trim();
			if (gotmethod.contains(index)) {
				SelectClass.selectByVisibleText(dropdown, index);
				found = true;
				break;
			}
		}
		if (found) {
			System.out.println(name + " / " + number + " / " + date + " / " + index + " / entered successfully");
			ReportLogger.logPass("Name: " + name + "\n" + " Contact Number: " + number + "\n" + " Date: " + date + "\n"
					+ "Payment Method: " + index + "\n" + "Entered successfully");
			
		} else if (!found) {
			ReportLogger.logFail(index + " was not found");
			Assert.fail(index + " was not found in payment method list.");
		}
		ReportLogger.logScreenShot(driver, "AllFieldsEntered");
		ReportLogger.logScreenShot(driver, "AllFieldsEntered");
		scrollAndClick(driver, rigister);
		
		waitForVisiblewebElement(conformMsg);
		if(!conformMsg.isDisplayed()) {
		
		waitForAllVisiblewebElement(invalidValidation);
		String conName = ContactName.getAttribute("value").trim();
		String conNum = ContactNumber.getAttribute("value").trim();
		String datefield = PickUpDate.getAttribute("value").trim();
		String PayMethod = dropdown.getAttribute("value").trim();
		for (WebElement s : invalidValidation) {
			String validation = s.getText().trim();
			if (!conName.isBlank() && s.isDisplayed() && contactinvalid.contains(validation)) {
				ReportLogger.logFail("Invalid Validation is displayed when ContactName field has value");
				Assert.fail();
			}
			if (!conNum.isBlank() && s.isDisplayed() && contactNumInvalid.contains(validation)) {
				ReportLogger.logFail("Invalid Validation is displayed when ContactName field has value");
				Assert.fail();
			}
			if (!datefield.isBlank() && s.isDisplayed() && dateInvalid.contains(validation)) {
				ReportLogger.logFail("Invalid Validation is displayed when ContactName field has value");
				Assert.fail();
			}
			if (!PayMethod.isBlank() && s.isDisplayed() && paymentInvalid.contains(validation)) {
				ReportLogger.logFail("Invalid Validation is displayed when Payment field has value");
				Assert.fail();
			}
		}
		}
		System.out.println(conformMsg.getText());
		ReportLogger.logInfo(conformMsg.getText());
	}

	public void invalidValidation() {
		ContactName.clear();
		scrollAndClick(driver, rigister);
		ReportLogger.logScreenShot(driver, "invalidValidations");
		String conName = ContactName.getAttribute("value").trim();
		String conNum = ContactNumber.getAttribute("value").trim();
		String datefield = PickUpDate.getAttribute("value").trim();
		String PayMethod = dropdown.getAttribute("value").trim();
		waitForAllVisiblewebElement(invalidValidation);
		for (WebElement s : invalidValidation) {
			String validation = s.getText().trim();
			if (conName.isEmpty() && s.isDisplayed() && contactinvalid.contains(validation)) {
				ReportLogger.logInfo(validation);
				System.out.println(validation);

			} else if (conName.isEmpty() && !s.isDisplayed()) {
				ReportLogger.logFail("Validation is not displayed when conName field is empty");
				Assert.fail();
			}
			if (conNum.isEmpty() && s.isDisplayed() && contactNumInvalid.contains(validation)) {
				ReportLogger.logInfo(validation);
				System.out.println(validation);
			} else if (conNum.isEmpty() && !s.isDisplayed()) {
				ReportLogger.logFail("Validation is not displayed when conNum field is empty");
				Assert.fail();
			}
			if (datefield.isEmpty() && s.isDisplayed() && dateInvalid.contains(validation)) {
				ReportLogger.logInfo(validation);
				System.out.println(validation);
			} else if (datefield.isEmpty() && !s.isDisplayed()) {
				ReportLogger.logFail("Validation is not displayed when datefield field is empty");
				Assert.fail();
			}
			if (PayMethod.isEmpty() && s.isDisplayed() && paymentInvalid.contains(validation)) {
				ReportLogger.logInfo(validation);
				System.out.println(validation);
			} else if (PayMethod.isEmpty() && !s.isDisplayed()) {
				ReportLogger.logFail("Validation is not displayed when Payment field is empty");
				Assert.fail();
			}
		}
		ReportLogger.logPass("All invalid validations are displayed");
	}
}
