package mainPack;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseclass.DriverSetup;
import testPages.Pagination_TablePage;

public class PaginationTest extends DriverSetup{
	
	
	Pagination_TablePage page;
	String index = "3";
	String name = "Jane Smith";
	
	@BeforeMethod
	public void launchpage() throws IOException {
		Properties prop	=loadProperties();	
		getdriver().get(prop.getProperty("Url"));
		page = new Pagination_TablePage(getdriver());
		page.verifyPage();
	}
	@Test
	public void pagenations() {
		page.pageNext(index,index);
	}
	@Test
	public void checkEntry() {
		page.entry(index);
	}
	@Test
	public void searchBox() {
		page.search(name);
	}
}
