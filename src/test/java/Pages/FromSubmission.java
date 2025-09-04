package Pages;

import Utility.Utility;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FromSubmission {
    WebDriver driver;
    ExtentTest test;


    @FindBy(name = "name")
    WebElement nameInput;

    @FindBy(name = "email")
    WebElement emailInput;

    @FindBy(name = "contact")
    WebElement phoneInput;

    @FindBy(id = "file")
    WebElement fileUpload;


    @FindBy(xpath = "//label[@for='Green']")
    WebElement radioBoxSelect;
    @FindBy(id = "Pasta")
    WebElement pastaCheckbox;
    @FindBy(name = "country")
    WebElement countryDropdown;
    @FindBy(css = "button[type='submit']")
    WebElement submitBtn;
    public FromSubmission(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        PageFactory.initElements(driver, this);
    }

    public void enterName(String name) throws IOException {
        nameInput.sendKeys(name);
        test.info("Name column found as expected");
        Utility.getScreenShot(driver, "Enter Name Successfully");

    }
    public void enterInvalidName(String name) throws IOException {
        nameInput.sendKeys(name);
        test.info("Input invalid name");
        Utility.getScreenShot(driver, "invalid name");

    }

    public void enterEmail(String email) throws IOException {
        emailInput.sendKeys(email);
        test.info("Email column found as expected");
        Utility.getScreenShot(driver, "Enter Email Successfully");
    }
    public void enterInvalidEmail(String email) throws IOException {
        emailInput.sendKeys(email);
        test.info("Input invalid Email");
        Utility.getScreenShot(driver, "invalid  Email ");
    }

    public void enterPhone(String phone) throws IOException {
        phoneInput.sendKeys(phone);
        test.info("Phone column found as expected");
        Utility.getScreenShot(driver, "Enter Phone Number Successfully");
    }
    public void enterInvalidPhone(String phone) throws IOException {
        phoneInput.sendKeys(phone);
        test.info("input Invalid Phone");
        Utility.getScreenShot(driver, "Invalid contact");
    }

    public void uploadFile() throws IOException, InterruptedException {
        File file = new File("src/test/resources/chaldal.pdf");
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
        }

        String absoluteFilePath = file.getAbsolutePath();

        // Upload the file by sending the path directly (no click needed!)
        fileUpload.sendKeys(absoluteFilePath);

        // Verify file was uploaded
        String uploadedFileName = fileUpload.getAttribute("value");
        Assert.assertTrue(uploadedFileName.contains("chaldal.pdf"), "File upload failed!");

        test.pass("File uploaded successfully: " + file.getName());
        Utility.getScreenShot(driver, "File_Upload_Successfully");
    }
    public void radioBoxColor() throws IOException {
        if (!radioBoxSelect.isSelected()) {
            radioBoxSelect.click();
            test.pass("Radio button selected successfully");
        } else {
            test.pass("Radio button was already selected");
        }

        Utility.getScreenShot(driver, "RadioButton_Selection_Successfully");
    }
    public void clickPastaCheckbox() throws IOException {
        if(!pastaCheckbox.isSelected()) {
            pastaCheckbox.click();
            test.pass("Pasta checkbox clicked successfully");
            Utility.getScreenShot(driver, "checkbox Submission Successfully");
        } else {
            test.info("Pasta checkbox was already selected");
        }
    }
    public void selectCountry() throws IOException {
        Select select = new Select(countryDropdown);
        select.selectByVisibleText("Bangladesh");
        test.pass("Country selected: ");
        Utility.getScreenShot(driver, "country Submission Successfully");
    }

    public void clickSubmit() throws IOException {
        submitBtn.click();
        test.pass("Submit button found and clicked successfully");
        Utility.getScreenShot(driver, "From Submission Successfully");
    }

    public boolean isFieldDisplayed() {
        return nameInput.isDisplayed() && emailInput.isDisplayed()
                && phoneInput.isDisplayed() && fileUpload.isDisplayed()
                && radioBoxSelect.isDisplayed() && pastaCheckbox.isDisplayed() && countryDropdown.isDisplayed();
    }
}
