package TestRunner;


import Config.BasePage;
import Pages.FromSubmission;
import Utility.Utility;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class FromSubmissionTest extends BasePage {
    @Test(priority = 1,description = "Locate and Fill All Required Fields")
    public void testFillRequiredFields() throws IOException, InterruptedException {
        driver.get("https://practice.qabrains.com/form-submission");
        test=extent.createTest("From Submission");
        ExtentTest node= test.createNode("From Submission successfully With Valid input");

        FromSubmission fromSubmission = new FromSubmission(driver, node);

        fromSubmission.enterName("QA Brain");
        fromSubmission.enterEmail("qa@test.com");
        fromSubmission.enterPhone("01234567890");
        Utility.scrollBy(driver);
        Thread.sleep(2000);
        fromSubmission.uploadFile();
        Thread.sleep(4000);
        fromSubmission.radioBoxColor();
        Thread.sleep(2000);
        Utility.scrollBy(driver);
        Thread.sleep(2000);
        fromSubmission.clickPastaCheckbox();
        Thread.sleep(2000);
        fromSubmission.selectCountry();
        Thread.sleep(2000);
        fromSubmission.clickSubmit();
        Thread.sleep(2000);
        String expectedText = "Form submit successfully.";
        String actualText = driver.findElement(By.cssSelector("div.toaster span.title")).getText();
        Assert.assertEquals(actualText, expectedText);
        Thread.sleep(1000);
        node.pass("All required fields located and filled successfully");
    }

    @Test(priority = 2,description = "Form Validation  Missing/Invalid Data ")
    public void testFillRequiredFieldsWithInvalidData() throws IOException, InterruptedException {
        driver.get("https://practice.qabrains.com/form-submission");
        ExtentTest node = test.createNode("Locate and Fill Required Fields with Invalid Data");

        FromSubmission fromSubmission = new FromSubmission(driver, node);

        // Enter invalid/missing data
        fromSubmission.enterInvalidName("");   // empty name
        fromSubmission.enterInvalidEmail("");  // empty email
        fromSubmission.enterInvalidPhone("12ab"); // invalid phone
        Thread.sleep(4000);
        Utility.scrollBy(driver);
        Thread.sleep(2000);
        fromSubmission.radioBoxColor();
        Thread.sleep(2000);
        Utility.scrollBy(driver);
        Thread.sleep(2000);
        fromSubmission.clickPastaCheckbox();
        Thread.sleep(2000);
        fromSubmission.selectCountry();
        Thread.sleep(2000);
        fromSubmission.clickSubmit();
        Thread.sleep(2000);
        // Wait for validation errors
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        List<WebElement> errorMessages = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("p.text-red-500"))
        );

        // Collect texts
        List<String> actualErrors = errorMessages.stream()
                .map(WebElement::getText)
                .toList();

        // Expected errors
        List<String> expectedErrors = Arrays.asList(
                "Name is a required field",
                "Email is a required field",
                "Only numbers are allowed",
                "Upload File is a required field"
        );

        // Assert all
        Assert.assertEquals(actualErrors, expectedErrors, "Validation messages did not match!");

        node.pass("Invalid data correctly triggered validation errors: " + actualErrors);
    }

    @Test(priority = 3,description = "Accessibility and Labels Test Successfully")
    public void testAccessibilityOfFieldsAndLabels() {
        driver.get("https://practice.qabrains.com/form-submission");
        ExtentTest node = test.createNode("Accessibility and Labels Test");
        FromSubmission fromSubmission = new FromSubmission(driver, node);
        Assert.assertTrue(fromSubmission.isFieldDisplayed());
        node.pass("All form fields are accessible");

    }
}
