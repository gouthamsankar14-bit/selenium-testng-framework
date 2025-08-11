package baseclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import reuse.ExtentTestManager;

public class DriverSetup {
    public static ExtentReports extent;
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

   
    public Properties loadProperties() throws IOException {
        String propPath = System.getProperty("user.dir") + "\\src\\main\\resources\\global.property";
        FileInputStream file = new FileInputStream(propPath);
        Properties prop = new Properties();
        prop.load(file);
        return prop;
    }

    @BeforeClass
    public void setupReport() {
        String className = this.getClass().getSimpleName();
        extent = ExtentReportManager.getInstance(className);
        ExtentTest test = extent.createTest("Test Execution - " + className);
        ExtentTestManager.setTest(test);
    }

    @BeforeMethod
    public void launchBrowser(Method method) throws IOException {
    	
        Properties prop = loadProperties();
        String browserName = prop.getProperty("browser").toLowerCase();
      

        String className = this.getClass().getSimpleName();
        ExtentTestManager.setTest(ExtentReportManager.getInstance(className).createTest(method.getName()));

        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;

            case "edge":
            	System.setProperty("webdriver.edge.driver", "C:\\Users\\kgouthamsankar\\edge driver.exe");   
                driver.set(new EdgeDriver());
                break;

            default:
                throw new IllegalArgumentException("❌ Browser not supported: " + browserName);
        }

        getdriver().manage().window().maximize();
        getdriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public static WebDriver getdriver() {
        return driver.get();
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        getdriver().quit();
        driver.remove();
    }
}
