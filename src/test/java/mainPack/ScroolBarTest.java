package mainPack;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseclass.DriverSetup;
import testPages.ScrollBarPage;

public class ScroolBarTest extends DriverSetup{
	
	ScrollBarPage scroll;
	@BeforeMethod
	public void launchPage() throws IOException {
		Properties prop	=loadProperties();	
		getdriver().get(prop.getProperty("Url"));
		scroll = new ScrollBarPage(getdriver());
		scroll.verifyPage();		
	}
	@Test
	public void screenShot() {
		scroll.scrollandTakescreenshot();
	}
	
	
	

}
