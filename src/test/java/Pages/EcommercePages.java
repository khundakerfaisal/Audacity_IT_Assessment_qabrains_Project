package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EcommercePages {
    WebDriver driver;
    @FindBy(xpath = "//span[text()='E-Commerce Site']")
    WebElement EcommerceSiteMenu;

    @FindBy(xpath = "//a[text()='Visit Demo Site']")
    WebElement viewDemoSiteLink;
    @FindBy(name = "email")
    WebElement txtInputEmail;
    @FindBy(name = "password")
    WebElement txtInputPassword;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginButton;

    public EcommercePages(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void ecommerceSiteLogin() throws InterruptedException {
        EcommerceSiteMenu.click();
        Thread.sleep(2000);
        viewDemoSiteLink.click();
        Thread.sleep(2000);
        txtInputEmail.sendKeys("test@qabrains.com");
        Thread.sleep(2000);
        txtInputPassword.sendKeys("Password123");
        Thread.sleep(2000);
        loginButton.click();
        Thread.sleep(2000);

    }
}
