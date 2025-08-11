package mainPack;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseclass.DriverSetup;
import listeners.Retry;
import testPages.TestRegisterPage;

public class RegisterPageTest extends DriverSetup {
	
	TestRegisterPage reg;

	@BeforeMethod(alwaysRun = true)
	public void launchPage() throws IOException {
		Properties prop	=loadProperties();	
		getdriver().get(prop.getProperty("Url"));
		reg = new TestRegisterPage(getdriver());
		reg.regPageVerification();
	}

	@Test(dataProvider = "registrationData", retryAnalyzer = Retry.class)
	public void registrationTest(String user, String pass, String confPass) {
		reg.register(user, pass, confPass);
	}

	@DataProvider(name = "registrationData")
	public Object[][] registrationData() {
		return new Object[][] { { "Hari", "1234", "1234" }, // Successfully registered, you can log in now.
				{ "Hari", "5678", "5678" }, // repeated 
				{ "", "1234", "1234" }, // All fields are required.
				{ "Quinn.jr", "", "1234" }, // All fields are required.
				{ "Quinn.jr", "1234", "" }, // All fields are required.
				{ "Admin", "12345", "54321" }, // Passwords do not match.
				{ "One", "1234", "1234" }, // Uesrname must be at least 4 characters long.
				{ "Allwin", "123", "123" }// Password must be at least 4 characters long.
		};
	}

}