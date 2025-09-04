package Pages;

import Utility.Utility;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;

public class FromSubmission {
    WebDriver driver;
    ExtentTest test;

    public FromSubmission(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        PageFactory.initElements(driver, this);
    }
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

    @FindBy(id = "success-message")
    WebElement successMsg;

    @FindBy(id = "error-message")
    WebElement errorMsg;
    public void enterName(String name) throws IOException {
        nameInput.sendKeys(name);
        test.info("Name column found as expected");
        Utility.getScreenShot(driver, "Enter Name Successfully");

    }

    public void enterEmail(String email) throws IOException {
        emailInput.sendKeys(email);
        test.info("Email column found as expected");
        Utility.getScreenShot(driver, "Enter Email Successfully");
    }

    public void enterPhone(String phone) throws IOException {
        phoneInput.sendKeys(phone);
        test.info("Phone column found as expected");
        Utility.getScreenShot(driver, "Enter Phone Number Successfully");
    }

    public void uploadFile() throws IOException {
//        String filePath = Paths.get(System.getProperty("user.dir"), "testdata", fileName).toString();
//        fileUpload.sendKeys(filePath);
        File file = new File("src/chaldal.pdf"); // Replace with your file path
        String absoluteFilePath = file.getAbsolutePath();
        // Upload the file by sending the file path to the input element
        fileUpload.sendKeys(absoluteFilePath);
        test.pass("File uploaded successfully: ");
        Utility.getScreenShot(driver, "File Upload Successfully");
    }
    public void radioBoxColor() throws IOException {
        radioBoxSelect.click();
        test.pass("checkbox clicked successfully");
        Utility.getScreenShot(driver, "checkbox Submission Successfully");
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

    public String getSuccessMessage() {
        return successMsg.getText();
    }

    public String getErrorMessage() {
        return errorMsg.getText();
    }
    public boolean isFieldDisplayed() {
        return nameInput.isDisplayed() && emailInput.isDisplayed()
                && phoneInput.isDisplayed() && fileUpload.isDisplayed();
    }
}
