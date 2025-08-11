package testPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reuse.Abstarct;
import reuse.ReportLogger;

public class DragAndDropPage extends Abstarct {
	WebDriver driver;

	public DragAndDropPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(linkText = "Drag and Drop")
	WebElement dragA_Bpage;
	@FindBy(id = "column-a")
	WebElement a;
	@FindBy(id = "column-b")
	WebElement b;

	@FindBy(linkText = "Drag and Drop Circles")
	WebElement dragCirclepage;
	@FindBy(id = "target")
	WebElement gray;
	@FindBy(xpath = "//div[@class='red']")
	WebElement red;
	@FindBy(xpath = "//div[@class='green']")
	WebElement green;
	@FindBy(xpath = "//div[@class='blue']")
	WebElement blue;
	

	public void dragAtoB() {
		waitForVisiblewebElement(dragA_Bpage);
		scrollAndClick(driver, dragA_Bpage);
		System.out.println(driver.getTitle());
		waitForVisiblewebElement(a);
		scrolltoElement(driver, a);
		ReportLogger.logScreenShot(driver, "befor droping b");
		Actions act = new Actions(driver);
		act.dragAndDrop(b, a).build().perform();
		waitForVisiblewebElement(a);
		if (a.getText().trim().contains("B")) {
			ReportLogger.logScreenShot(driver, "after droping b");
		}
	}

	public void dragCircleToBox() {
		waitForVisiblewebElement(dragCirclepage);
		scrollAndClick(driver, dragCirclepage);
		System.out.println(driver.getTitle());
		waitForVisiblewebElement(gray);
		scrolltoElement(driver, gray);
		ReportLogger.logScreenShot(driver, "before drag");
		Actions act = new Actions(driver);
		act.dragAndDrop(red, gray).build().perform();
		ReportLogger.logScreenShot(driver, "after dragind red");
		act.dragAndDrop(green, gray).build().perform();
		ReportLogger.logScreenShot(driver, "before dragind green");
		act.dragAndDrop(blue, gray).build().perform();
		ReportLogger.logScreenShot(driver, "before dragind blue");
	}

	@FindBy(linkText = "File Upload")
	WebElement fileup;
	@FindBy (css = ".form-control")
	WebElement uploadbox;
	@FindBy (id = "fileSubmit")
	WebElement uploadButton; 
	
	public void fileupload(String path) {
		waitForVisiblewebElement(fileup);
		scrollAndClick(driver, fileup);
		waitForVisiblewebElement(uploadbox);
		scrolltoElement(driver, uploadbox);
		uploadbox.sendKeys(path);
		ReportLogger.logScreenShot(driver, "File Upload");
		uploadButton.click();
		
	}

}
