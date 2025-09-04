package Config;

import Utility.Utility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class BasePage {
    public static ExtentReports extent;
    public  static ExtentTest test;
    public WebDriver driver;
    @BeforeClass
    public void StartBrowser(){
        extent=Utility.getInstance();
        test=extent.createTest("Browser Configuration Successfully");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }
    @AfterClass
    public void CloseBrowser(){

        driver.quit();
        extent.flush();

    }
}
