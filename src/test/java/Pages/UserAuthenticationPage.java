package Pages;

import Utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import static Config.BasePage.test;

public class UserAuthenticationPage {
    WebDriver driver;
    @FindBy(name = "email")
    WebElement txtInputEmail;
    @FindBy(name = "password")
    WebElement txtInputPassword;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginButton;

    @FindBy(xpath = "//span[text()='Registration']")
    WebElement registrationMenu;
    @FindBy(xpath = "//span[text()='Forgot Password']")
    WebElement forgetPasswordMenu;

    @FindBy(xpath = "//textarea[@placeholder='Write Comment...']")
    WebElement inputFeedback;
    @FindBy(xpath = "//button[text()='Submit']")
    WebElement feedbackSubmitButton;
    public UserAuthenticationPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void qaBrainLoginPage(String email, String password) throws InterruptedException, IOException {

        txtInputEmail.sendKeys(email);
        test.info("Email column found as expected");
        Utility.getScreenShot(driver, "Email input Successfully");
        Thread.sleep(1000);
        txtInputPassword.sendKeys(password);
        test.info("Password column found as expected");
        Utility.getScreenShot(driver, "Password input successfully");
        Thread.sleep(1000);
        loginButton.click();
        test.pass("Login button found as expected");
        Utility.getScreenShot(driver, "Login Successfully");
        Thread.sleep(2000);

    }

    public void navigateToRegistration() throws IOException {
        registrationMenu.click();
        test.info("Registration menu found as expected");
        Utility.getScreenShot(driver, "Navigate to the Registration Menu Successfully");
    }

    public void navigateToForgotPassword() throws IOException {
        forgetPasswordMenu.click();
        test.info("Forget Password menu found as expected");
        Utility.getScreenShot(driver, "Navigate to the Forget Password Menu Successfully");
    }

    public void inputFeedback() throws IOException, InterruptedException {
        inputFeedback.sendKeys("Test QA Brain");
        test.info("Feedback column found as expected");
        Utility.getScreenShot(driver, "Feedback input Successfully");
        feedbackSubmitButton.click();
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dialogBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='dialog']"))
        );
        WebElement signUpBtn = dialogBox.findElement(By.xpath(".//button[text()='Sign Up']"));
        signUpBtn.click();
        Thread.sleep(2000);
        driver.switchTo().newWindow(WindowType.TAB);
        Thread.sleep(2000);
        driver.get("https://qabrains.com/auth/login");
        Thread.sleep(2000);
        txtInputEmail.sendKeys("qa.brain@yopmail.com");
        Thread.sleep(2000);
        txtInputPassword.sendKeys("Abc@123456");
        Thread.sleep(2000);
        loginButton.click();
        Thread.sleep(2000);
        driver.close();
        Thread.sleep(2000);
        inputFeedback.sendKeys("Test QA Brain");
        test.info("Feedback column found as expected");
        Utility.getScreenShot(driver, "Feedback input Successfully");
        feedbackSubmitButton.click();
        Thread.sleep(2000);
        test.info("Feedback submit button found as expected");
        Utility.getScreenShot(driver, "Feedback submit Successfully");
    }

    public boolean isPasswordMasked() {
        return txtInputPassword.getAttribute("type").equals("password");
    }

    public String getPasswordAutocomplete() {
        return txtInputPassword.getAttribute("autocomplete");
    }

}
