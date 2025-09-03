package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

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
    public UserAuthenticationPage(WebDriver driver){

        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void qaBrainLoginPage(String email,String password) throws InterruptedException, IOException {

        txtInputEmail.sendKeys(email);
//        Utils.getScreenShot(driver,"User name found as expected");
//        test.info("User name found as expected");
        Thread.sleep(1000);

        txtInputPassword.sendKeys(password);
//        test.info("Password found as expected");
        Thread.sleep(1000);
        loginButton.click();
//        test.pass("Login button Press Successfully");
//        Utils.getScreenShot(driver,"Login button Press Successfully");
        Thread.sleep(2000);
        registrationMenu.click();
        Thread.sleep(2000);
        forgetPasswordMenu.click();
    }
    public void navigateToRegistration(){
        registrationMenu.click();
    }
    public void navigateToForgotPassword(){
        forgetPasswordMenu.click();
    }

}
