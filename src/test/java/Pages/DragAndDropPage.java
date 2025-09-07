package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class DragAndDropPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    // Constructor
    public DragAndDropPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // Page Elements - Update these locators based on actual HTML
    @FindBy(xpath = "//div[text()='Drag Me']")
    private WebElement dragElement;

    @FindBy(xpath = "//div[text()='Drop Here']")
    private WebElement dropTarget;

    // Alternative locators in case the above don't work
    @FindBy(css = "[draggable='true']")
    private WebElement draggableElement;

    @FindBy(css = ".drop-zone")
    private WebElement dropZone;

    // Methods
    public void navigateToPage() {
        driver.get("https://practice.qabrains.com/drag-drop");
    }

    public boolean isDragElementVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(dragElement));
            return dragElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDropTargetVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(dropTarget));
            return dropTarget.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Main drag and drop method using Actions class
    public void performDragAndDrop() {
        wait.until(ExpectedConditions.visibilityOf(dragElement));
        wait.until(ExpectedConditions.visibilityOf(dropTarget));

        actions.dragAndDrop(dragElement, dropTarget).perform();
    }

    // Alternative drag and drop using clickAndHold approach
    public void performDragAndDropAlternative() {
        wait.until(ExpectedConditions.visibilityOf(dragElement));
        wait.until(ExpectedConditions.visibilityOf(dropTarget));

        actions.clickAndHold(dragElement)
                .moveToElement(dropTarget)
                .release()
                .perform();
    }

    // Method to get drag element text
    public String getDragElementText() {
        wait.until(ExpectedConditions.visibilityOf(dragElement));
        return dragElement.getText();
    }

    // Method to get drop target text
    public String getDropTargetText() {
        wait.until(ExpectedConditions.visibilityOf(dropTarget));
        return dropTarget.getText();
    }

    // Method to check if drag and drop was successful
    public boolean isDragDropSuccessful() {
        // This method would need to be customized based on what happens after successful drop
        // For example, checking if element moved, text changed, or success message appeared
        try {
            Thread.sleep(1000); // Wait for any animations to complete
            // Add your success verification logic here
            // Example: check if drag element is now inside drop target
            return true; // Placeholder - implement based on actual behavior
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}