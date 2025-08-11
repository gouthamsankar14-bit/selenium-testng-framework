package mainPack;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseclass.DriverSetup;
import testPages.FormValidationPage;

public class FormValidationTest extends DriverSetup {

	FormValidationPage form;
	

	@BeforeMethod
	public void launch() throws IOException {
		Properties prop	=loadProperties();	
		getdriver().get(prop.getProperty("Url"));
		form = new FormValidationPage(getdriver());
		form.verifyPage();
	}

	@Test(dataProvider = "RegisterData")
	public void register(String name, String number, String date, String index) {
		form.validRegister(name, number, date, index);
	}

	@DataProvider(name = "RegisterData")
	public Object[][] getdataForResgister() {
		return new Object[][] { { "Goutham", "784-5965874", "08/06/2025", "card" },
				{ "Goutham", "784-5965874", "08/07/2025", "cash on delivery" },
				{ "Goutham", "784-5965874", "08/08/2025", "UPI" } };
	}

	@Test
	public void invalidValidations() {
		form.invalidValidation();
	}
}
