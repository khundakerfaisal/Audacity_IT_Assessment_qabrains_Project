package Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import static Config.BasePage.test;

public class Utility {
    public static ExtentReports extent;
    ExtentTest node;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    public static void getScreenShot(WebDriver driver, String message,ExtentTest node) throws IOException {
        Random randNumber = new Random();
        int generateAutoNumber = randNumber.nextInt(9999) + 1111;
        File srcFilePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotDir = "reports/screenshots/";
        new File(screenshotDir).mkdirs(); // Ensure directory exists
        String imageFileName = "screenshot_" + generateAutoNumber + ".png";
        String fullScreenshotPath = screenshotDir + imageFileName;
        File destFile = new File(fullScreenshotPath);
        FileUtils.copyFile(srcFilePath, destFile);
        // Use RELATIVE path from HTML file (ExtentReport.html is in /reports/)
        String relativePath = "screenshots/" + imageFileName;
        node.pass(message,
                MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());

    }
    public static void scrollBy(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
    }
}
