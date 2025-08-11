package testPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import reuse.Abstarct;
import reuse.ReportLogger;
public class Pagination_TablePage extends Abstarct {
	WebDriver driver;

	public Pagination_TablePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Dynamic Pagination Table")
	WebElement loginLink;
	@FindBy(css = ".form-select")
	WebElement entry;

	@FindBy(xpath = "//tbody[@id='demo']//tr/td[1]")
	List<WebElement> selectName;
	@FindBy(xpath = "//tbody[@id='demo']//tr/td[1]")
	WebElement searchName;
	

	
	@FindBy(linkText = "Next")
	WebElement Next;
	@FindBy(css = ".form-control")
	WebElement searchBox;
	//String[] storedIndex = {"3", "5", "10"};
	List<WebElement> Name = null;


	public void verifyPage() {
		waitForVisiblewebElement(loginLink);
		scrollAndClick(driver, loginLink);
		String tiltle = driver.getTitle();
		System.out.println("Title: " + tiltle);
	}
	
	public void pageNext(String index, String name) {
		Select select = new Select(entry);
		select.selectByValue(index);
		boolean isLastPage = false;
		
		do {
			waitForAllVisiblewebElement(selectName);		 
			for (WebElement element : selectName) {
				if (element.getText().contains(name)) {
					Name.add(element);
				}
			}
			System.out.println(Name);
			ReportLogger.logInfo("Found: " + Name);
			ReportLogger.logScreenShot(driver, name);

			if (Name.size() > 0) {
				ReportLogger.logPass("Name '" + name + "' found in table.");
				return;
			}
			waitForVisiblewebElement(Next);
			scrolltoElement(driver, Next);

			if (Next.isEnabled()) {
				scrollAndClick(driver, Next);
			} else {
				isLastPage = true;
			}
		} while (!isLastPage);
		ReportLogger.logFail("Name '" + name + "' not found in any page.");
		Assert.fail("Name '" + name + "' not found in any page.");
	}
	
	public void entry(String index) {
		Select select = new Select(entry);	
		select.selectByValue(index);	
		waitForAllVisiblewebElement(selectName);
		int gotIntex = selectName.size();
		System.out.println(gotIntex);
		String gotIndexStr = String.valueOf(gotIntex);
		if (gotIndexStr.equals(index)) {
			ReportLogger.logPass(gotIndexStr);
			scrolltoElement(driver, Next);
			ReportLogger.logScreenShot(driver, gotIndexStr);
		} else if (!gotIndexStr.equals(index)) {
			ReportLogger.logFail("No.of row displayed " + gotIndexStr + " does not match the given " + index);
			ReportLogger.logScreenShot(driver, gotIndexStr);
			Assert.fail("No.of row displayed " + gotIndexStr + " does not match the given " + index);
			
		}
	}

	public void search(String name) {
		searchBox.sendKeys(name);
		ReportLogger.logScreenShot(driver, name);
		String gotName = searchName.getText().trim();
		if (gotName.equalsIgnoreCase(name)) {
			System.out.println(gotName);
			ReportLogger.logPass("Result for the Name search: " + gotName);			
		} else if (!gotName.equalsIgnoreCase(name)) {
			Assert.fail("cannot find the given " + name);
			ReportLogger.logFail("cannot find the given " + name);
		}
	}
}
