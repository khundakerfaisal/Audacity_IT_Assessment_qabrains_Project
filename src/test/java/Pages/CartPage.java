package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Cart Page Elements

//
//    @FindBy(xpath = "//button[contains(text(),'Remove') or contains(@class,'remove')]")
//    private List<WebElement> removeButtons;
//
//    @FindBy(xpath = "//input[contains(@class,'quantity') or contains(@name,'quantity')]")
//    private List<WebElement> quantityInputs;
//
//    @FindBy(xpath = "//button[contains(text(),'Update') or contains(@class,'update')]")
//    private List<WebElement> updateButtons;
//
//    @FindBy(xpath = "//span[contains(@class,'total') or contains(@class,'subtotal')]")
//    private WebElement totalAmount;
//
//    @FindBy(xpath = "//button[contains(text(),'Checkout') or contains(text(),'Proceed')]")
//    private WebElement checkoutButton;
//
//    @FindBy(xpath = "//button[contains(text(),'Continue Shopping') or contains(text(),'Back')]")
//    private WebElement continueShoppingButton;
//
//    @FindBy(xpath = "//div[contains(@class,'empty-cart') or contains(text(),'empty')]")
//    private WebElement emptyCartMessage;
//
//    @FindBy(xpath = "//h2[contains(text(),'Cart') or contains(text(),'Shopping Cart')]")
//    private WebElement cartTitle;
//
//    @FindBy(xpath = "//div[contains(@class,'cart-summary')]")
//    private WebElement cartSummary;
//
//    @FindBy(xpath = "//span[contains(@class,'item-count')]")
//    private WebElement itemCountDisplay;
//
//    // Product details in cart
//    @FindBy(xpath = "//h4[contains(@class,'product-name') or contains(@class,'item-title')]")
//    private List<WebElement> cartProductTitles;
//
//    @FindBy(xpath = "//span[contains(@class,'price') or contains(@class,'amount')]")
//    private List<WebElement> cartProductPrices;
//
//    @FindBy(xpath = "//div[contains(@class,'error') or contains(@class,'alert-danger')]")
//    private WebElement errorMessage;
//
//    @FindBy(xpath = "//div[contains(@class,'success') or contains(@class,'alert-success')]")
//    private WebElement successMessage;

    // Methods

//    public boolean isCartPageDisplayed() {
//        try {
//            wait.until(ExpectedConditions.visibilityOf(AddToCartButton));
//            return AddToCartButton.isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
//    }

//    public boolean isCartEmpty() {
//        try {
//            wait.until(ExpectedConditions.visibilityOf(emptyCartMessage));
//            return emptyCartMessage.isDisplayed();
//        } catch (Exception e) {
//            return cartItems.isEmpty();
//        }
//    }
//
//    public int getCartItemCount() {
//        try {
//            wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
//            return cartItems.size();
//        } catch (Exception e) {
//            return 0;
//        }
//    }
//
//    public void removeItemFromCart(int itemIndex) {
//        if (itemIndex < removeButtons.size()) {
//            wait.until(ExpectedConditions.elementToBeClickable(removeButtons.get(itemIndex)));
//            removeButtons.get(itemIndex).click();
//        }
//    }
//
//    public void removeFirstItem() {
//        removeItemFromCart(0);
//    }
//
//    public void updateQuantity(int itemIndex, String quantity) {
//        if (itemIndex < quantityInputs.size()) {
//            wait.until(ExpectedConditions.visibilityOf(quantityInputs.get(itemIndex)));
//            quantityInputs.get(itemIndex).clear();
//            quantityInputs.get(itemIndex).sendKeys(quantity);
//
//            if (itemIndex < updateButtons.size()) {
//                updateButtons.get(itemIndex).click();
//            }
//        }
//    }
//
//    public String getTotalAmount() {
//        try {
//            wait.until(ExpectedConditions.visibilityOf(totalAmount));
//            return totalAmount.getText();
//        } catch (Exception e) {
//            return "0.00";
//        }
//    }
//
//    public void proceedToCheckout() {
//        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
//        checkoutButton.click();
//    }
//
//    public void continueShopping() {
//        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
//        continueShoppingButton.click();
//    }
//
//    public boolean isCheckoutButtonEnabled() {
//        try {
//            wait.until(ExpectedConditions.visibilityOf(checkoutButton));
//            return checkoutButton.isEnabled();
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public List<String> getCartProductTitles() {
//        wait.until(ExpectedConditions.visibilityOfAllElements(cartProductTitles));
//        return cartProductTitles.stream()
//                .map(WebElement::getText)
//                .collect(java.util.stream.Collectors.toList());
//    }
//
//    public List<String> getCartProductPrices() {
//        wait.until(ExpectedConditions.visibilityOfAllElements(cartProductPrices));
//        return cartProductPrices.stream()
//                .map(WebElement::getText)
//                .collect(java.util.stream.Collectors.toList());
//    }
//
//    public String getItemCountDisplay() {
//        try {
//            wait.until(ExpectedConditions.visibilityOf(itemCountDisplay));
//            return itemCountDisplay.getText();
//        } catch (Exception e) {
//            return "0";
//        }
//    }
//
//    public boolean isErrorMessageDisplayed() {
//        try {
//            wait.until(ExpectedConditions.visibilityOf(errorMessage));
//            return errorMessage.isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public String getErrorMessage() {
//        if (isErrorMessageDisplayed()) {
//            return errorMessage.getText();
//        }
//        return "";
//    }
//
//    public boolean isSuccessMessageDisplayed() {
//        try {
//            wait.until(ExpectedConditions.visibilityOf(successMessage));
//            return successMessage.isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public String getSuccessMessage() {
//        if (isSuccessMessageDisplayed()) {
//            return successMessage.getText();
//        }
//        return "";
//    }
//
//    public void clearCart() {
//        while (getCartItemCount() > 0) {
//            removeFirstItem();
//            try {
//                Thread.sleep(1000); // Wait for removal to process
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }
//    }
//
//    public boolean verifyCartUpdate() {
//        try {
//            Thread.sleep(2000); // Wait for cart to update
//            return getCartItemCount() >= 0; // Basic verification
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            return false;
//        }
//    }
}
