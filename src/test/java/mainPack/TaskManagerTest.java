package mainPack;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseclass.DriverSetup;
import testPages.TaskManagerPage;

public class TaskManagerTest extends DriverSetup {
	TaskManagerPage dyn;

	@BeforeMethod
	public void launchPage() throws IOException {
		Properties prop	=loadProperties();	
		getdriver().get(prop.getProperty("Url"));
		dyn = new TaskManagerPage(getdriver());
		dyn.verifyPage();
	}
	
	@Test(dataProvider = "datas")
	public void taskManager(String tagName, String hardwareColumn, String nameColumn, String nameValue) throws InterruptedException {
		dyn.getHardwareValue(tagName, hardwareColumn, nameColumn, nameValue);
	}
	@DataProvider(name="datas")
	public Object[][] getdata() {
		return new Object[][] {{"td","cpu","name","chrome"},
								{"td","cpu","name","chr"}};
	}
}
	


