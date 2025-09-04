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
    ExtentTest node;


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
    public FromSubmission(WebDriver driver, ExtentTest node) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
        this.node = node;
    }

    public void enterName(String name) throws IOException {
        nameInput.sendKeys(name);
        node.info("Name column found as expected");
        Utility.getScreenShot(driver, "Enter Name Successfully",node);

    }
    public void enterInvalidName(String name) throws IOException {
        nameInput.sendKeys(name);
        node.info("Input invalid name");
        Utility.getScreenShot(driver, "invalid name",node);

    }

    public void enterEmail(String email) throws IOException {
        emailInput.sendKeys(email);
        node.info("Email column found as expected");
        Utility.getScreenShot(driver, "Enter Email Successfully",node);
    }
    public void enterInvalidEmail(String email) throws IOException {
        emailInput.sendKeys(email);
        node.info("Input invalid Email");
        Utility.getScreenShot(driver, "invalid  Email ",node);
    }

    public void enterPhone(String phone) throws IOException {
        phoneInput.sendKeys(phone);
        node.info("Phone column found as expected");
        Utility.getScreenShot(driver, "Enter Phone Number Successfully",node);
    }
    public void enterInvalidPhone(String phone) throws IOException {
        phoneInput.sendKeys(phone);
        node.info("input Invalid Phone");
        Utility.getScreenShot(driver, "Invalid contact",node);
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

        node.pass("File uploaded successfully: " + file.getName());
        Utility.getScreenShot(driver, "File_Upload_Successfully",node);
    }
    public void radioBoxColor() throws IOException {
        if (!radioBoxSelect.isSelected()) {
            radioBoxSelect.click();
            node.pass("Radio button selected successfully");
        } else {
            node.pass("Radio button was already selected");
        }

        Utility.getScreenShot(driver, "RadioButton_Selection_Successfully",node);
    }
    public void clickPastaCheckbox() throws IOException {
        if(!pastaCheckbox.isSelected()) {
            pastaCheckbox.click();
            node.pass("Pasta checkbox clicked successfully");
            Utility.getScreenShot(driver, "checkbox Submission Successfully",node);
        } else {
            node.info("Pasta checkbox was already selected");
        }
    }
    public void selectCountry() throws IOException {
        Select select = new Select(countryDropdown);
        select.selectByVisibleText("Bangladesh");
        node.pass("Country selected: ");
        Utility.getScreenShot(driver, "country Submission Successfully",node);
    }

    public void clickSubmit() throws IOException {
        submitBtn.click();
        node.pass("Submit button found and clicked successfully");
        Utility.getScreenShot(driver, "From Submission Successfully",node);
    }

    public boolean isFieldDisplayed() {
        return nameInput.isDisplayed() && emailInput.isDisplayed()
                && phoneInput.isDisplayed() && fileUpload.isDisplayed()
                && radioBoxSelect.isDisplayed() && pastaCheckbox.isDisplayed() && countryDropdown.isDisplayed();
    }
}
