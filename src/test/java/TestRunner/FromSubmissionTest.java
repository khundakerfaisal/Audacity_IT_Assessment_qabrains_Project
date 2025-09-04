package TestRunner;


import Config.BasePage;
import Pages.FromSubmission;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class FromSubmissionTest extends BasePage {
    @Test(priority = 1,description = "Locate and Fill All Required Fields")
    public void testFillRequiredFields() throws IOException {
        driver.get("https://practice.qabrains.com/form-submission");
        test = extent.createTest("Locate and Fill Required Fields");
        FromSubmission fromSubmission = new FromSubmission(driver, test);

        fromSubmission.enterName("QA Brain");
        fromSubmission.enterEmail("qa@test.com");
        fromSubmission.enterPhone("1234567890");
//        fromSubmission.enterMessage("Hello QA Brains");

        test.pass("All required fields located and filled successfully");
    }

    @Test(priority = 2,description = "From Submit Successfully")
    public void testSubmitAndAssertSuccessMessage() throws IOException {
        driver.get("https://practice.qabrains.com/form-submission");
        test = extent.createTest("Submit Form and Assert Success Message");
        FromSubmission fromSubmission = new FromSubmission(driver, test);

        fromSubmission.enterName("QA Brain");
        fromSubmission.enterEmail("qa@test.com");
        fromSubmission.enterPhone("1234567890");
//        fromSubmission.enterMessage("Hello QA Brains");
        fromSubmission.clickSubmit();

        Assert.assertTrue(fromSubmission.getSuccessMessage().contains("successfully"),
                "Success message not displayed as expected!");
        test.pass("Form submitted successfully and success message verified");
    }

    @Test(priority = 3,description = "Form Validation Successfully with Missing/Invalid Data ")
    public void testFormValidationWithInvalidData() throws IOException {
        driver.get("https://practice.qabrains.com/form-submission");
        test = extent.createTest("Form Validation with Missing/Invalid Data");
        FromSubmission fromSubmission = new FromSubmission(driver, test);

        // Case 1: Missing name
        fromSubmission.enterEmail("qa@test.com");
        fromSubmission.enterPhone("1234567890");
//        fromSubmission.enterMessage("Missing Name Field");
        fromSubmission.clickSubmit();

        Assert.assertTrue(fromSubmission.getErrorMessage().contains("required"),
                "Error message not displayed for missing data!");
        test.pass("Validation error verified for missing name");

        // Case 2: Invalid email
        fromSubmission.enterName("QA Brain");
        fromSubmission.enterEmail("invalidEmail");
        fromSubmission.enterPhone("1234567890");
//        fromSubmission.enterMessage("Invalid email test");
        fromSubmission.clickSubmit();
        Assert.assertTrue(fromSubmission.getErrorMessage().contains("valid email"),
                "Error message not displayed for invalid email!");
        test.pass("Validation error verified for invalid email");
    }

    @Test(priority = 4,description = "Accessibility and Labels Test Successfully")
    public void testAccessibilityOfFieldsAndLabels() {
        driver.get("https://practice.qabrains.com/form-submission");
        test = extent.createTest("Accessibility and Labels Test");
        FromSubmission fromSubmission = new FromSubmission(driver, test);
        Assert.assertTrue(fromSubmission.isFieldDisplayed(), "Some fields are not accessible!");
        test.pass("All form fields are accessible");

    }
}
