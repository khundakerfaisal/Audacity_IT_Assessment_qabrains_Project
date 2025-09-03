package TestRunner;

import Config.BasePage;
import Pages.UserAuthenticationPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserAuthenticationTest extends BasePage {
    @Test(priority = 1,description = "Login With Valid Credential")
    public void doLoginWithValidCred() throws InterruptedException, IOException {
        driver.get("https://practice.qabrains.com/");
        test=extent.createTest("User Authentication");
        test= test.createNode("Login successfully With Valid Credential");
        UserAuthenticationPage loginPages=new UserAuthenticationPage(driver);
        loginPages.qaBrainLoginPage("qa_testers@qabrains.com","Password123");
        Thread.sleep(1000);

        String expectedText = "Login Successful";
        String actualText = driver.findElement(By.cssSelector("div.toaster span.title")).getText();
        Assert.assertEquals(actualText, expectedText);
        Thread.sleep(1000);
    }
    @Test(priority = 2,description = "Login With Invalid Credential")
    public void doLoginWithInvalidCred() throws InterruptedException, IOException {
        driver.get("https://practice.qabrains.com/");

        test= test.createNode("Login Failed !!  Invalid credential ");
        UserAuthenticationPage loginPages=new UserAuthenticationPage(driver);
        loginPages.qaBrainLoginPage("qa_testers@qabrains.com","WrongPass123");
        Thread.sleep(1000);
        String expectedText = "Your password is invalid!";
        String actualText = driver.findElement(By.cssSelector("div.toaster span.title")).getText();
        Assert.assertEquals(actualText, expectedText);
        Thread.sleep(1000);
    }
    @Test(priority = 3,description = "Navigate to Registration Menu")
    public void registrationMenu() throws InterruptedException, IOException {
        driver.get("https://practice.qabrains.com/");
        test= test.createNode("Navigate to Registration Menu successfully");
        UserAuthenticationPage loginPages=new UserAuthenticationPage(driver);
        loginPages.navigateToRegistration();
        Thread.sleep(1000);
    }
    @Test(priority = 4,description = "Navigate to Forget Password Menu")
    public void forgetPasswordMenu() throws InterruptedException, IOException {
        driver.get("https://practice.qabrains.com/");
        test= test.createNode("Navigate to Forget Password successfully");
        UserAuthenticationPage loginPages=new UserAuthenticationPage(driver);
        loginPages.navigateToForgotPassword();
        Thread.sleep(1000);
    }
    @Test(priority = 5,description = "Check masking and Autocomplete attributes to Forget Password Menu")
    public void passwordMaskAndAutoCompleteCheck() throws InterruptedException, IOException {
        driver.get("https://practice.qabrains.com/");
        test= test.createNode("Check masking and Autocomplete attributes Successfully");
        UserAuthenticationPage loginPages=new UserAuthenticationPage(driver);
        loginPages.isPasswordMasked();
        Thread.sleep(1000);
        loginPages.getPasswordAutocomplete();
        Thread.sleep(1000);
    }
    @Test(priority = 6,description = "Leave Feedback Submit Entry")
    public void leaveFeedbackTextarea() throws InterruptedException, IOException {
        driver.get("https://practice.qabrains.com/");
        test= test.createNode("Leave Feedback Submit successfully");
        UserAuthenticationPage loginPages=new UserAuthenticationPage(driver);
        loginPages.inputFeedback();
        Thread.sleep(1000);
        String expectedText = "Test QA Brain";
        String actualText = driver.findElement(By.xpath("//div[@class='feed-content']//p")).getText();
        Assert.assertEquals(actualText, expectedText);
    }


}
