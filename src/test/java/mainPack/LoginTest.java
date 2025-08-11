package mainPack;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseclass.DriverSetup;
import rederfiles.GetJsonData;
import testPages.LoginPage;

public class LoginTest extends DriverSetup {
	static LoginPage login;

	@BeforeMethod(alwaysRun = true)
	public void loginpage() throws IOException {
		Properties prop	=loadProperties();	
		getdriver().get(prop.getProperty("Url"));
		login = new LoginPage(getdriver());
		login.verifyPage();

	}

	@Test(dataProvider = "validCreds")
	public void validLogin(HashMap<String, String> input) {

		login.loginValidation(input.get("username"), input.get("password"));
		
	}

	@DataProvider(name = "validCreds")
	public Object[][] validCreds() throws IOException {
		GetJsonData data = new GetJsonData();
		List<HashMap<String,String>>values = data.getJasonDatas();
		Object[][] result = new Object[values.size()][1];
		for(int i=0;i<values.size();i++) {
			result[i][0]=values.get(i);	
		}
			return result;	
	}

	@Test(dataProvider = "invalidUsername")
	public void invalidUsername(String user, String pass) {
    
		login.loginValidation(user, pass);
		// Assert.fail();
	}

	@DataProvider(name = "invalidUsername")
	public Object[][] invalidUsername() {
		return new Object[][] { { "prac_word", "SuperSecretPassword!" } };
	}

	@Test(dataProvider = "invalidPassword")
	public void invalidPassword(String user, String pass) {

		login.loginValidation(user, pass);
	}

	@DataProvider(name = "invalidPassword")
	public Object[][] invalidPassword() {
		return new Object[][] { { "practice", "SuperSecret" } };
	}
}
