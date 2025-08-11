package mainPack;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseclass.DriverSetup;
import testPages.DragAndDropPage;

public class DragAndDropTest extends DriverSetup {

	
	String path = "C:\\Users\\kgouthamsankar\\Downloads\\Automation Task Sheet.xlsx";
	DragAndDropPage drag;

	@BeforeMethod
	public void launchpage() throws IOException {
		Properties prop	=loadProperties();	
		getdriver().get(prop.getProperty("Url"));
		drag = new DragAndDropPage(getdriver());
	}

	@Test
	public void dragAB() {
		drag.dragAtoB();
	}

	@Test
	public void drag3to1() {
		drag.dragCircleToBox();
	}

	@Test
	public void flieup() {
		drag.fileupload(path);
	}
}
