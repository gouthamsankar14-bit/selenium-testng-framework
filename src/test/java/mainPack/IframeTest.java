package mainPack;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseclass.DriverSetup;
import testPages.IframePage;

public class IframeTest extends DriverSetup {
	IframePage frame;

	@BeforeMethod
	public void launchPage() throws IOException {
		Properties prop	=loadProperties();	
		getdriver().get(prop.getProperty("Url"));
		frame = new IframePage(getdriver());
		frame.verifyPage();
	}
	@Test(dataProvider = "mailId")
	public void framesTest(String mail) {
	frame.internalFrame(mail);
	}
	@DataProvider (name = "mailId")
	public Object[][] getMailId() {
		return new Object[][] {{" "},{"kgouthamsankar@gmail.com"}};
	}

}
