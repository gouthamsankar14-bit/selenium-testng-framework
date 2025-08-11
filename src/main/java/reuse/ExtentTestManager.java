package reuse;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    // ThreadLocal ensures thread-safety for parallel execution
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return extentTest.get();
    } 
	public static void setTest(ExtentTest string) {
		extentTest.set(string);
		
	}

	
}
