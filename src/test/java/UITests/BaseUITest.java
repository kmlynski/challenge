package UITests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseUITest {

    public static RemoteWebDriver webDriver;
    public static String path = "src/main/resources/index.html";


    @BeforeMethod
    public void beforeMethodSetup(Method testMethod)
    {
        System.out.println("Running: " + testMethod.getName() + "\n");
    }

    @BeforeSuite
    public void suiteSetUp() throws IOException {
        setupDesktopChromeRun(path);
    }
    private void setupDesktopChromeRun(String path) throws IOException {
        System.setProperty("webdriver.chrome.driver",
                new File("./src/test/resources/drivers/chromedriver.exe").getCanonicalPath());
        ChromeOptions optionsDesktop= new ChromeOptions();
        webDriver = new ChromeDriver(optionsDesktop);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get(new File(path).getCanonicalPath());
    }

    @AfterSuite
    public void closeTheDriver(){
        try{
            webDriver.quit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
