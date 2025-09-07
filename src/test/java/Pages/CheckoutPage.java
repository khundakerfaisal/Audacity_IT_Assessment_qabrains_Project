package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // Checkout Page Elements - Billing Information
    @FindBy(xpath = "//input[@name='firstName' or @id='firstName' or @placeholder='First Name']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@name='lastName' or @id='lastName' or @placeholder='Last Name']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@name='email' or @id='email' or @placeholder='Email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='phone' or @id='phone' or @placeholder='Phone']")
    private WebElement phoneField;

    @FindBy(xpath = "//input[@name='address' or @id='address' or @placeholder='Address']")
    private WebElement addressField;

    @FindBy(xpath = "//input[@name='city' or @id='city' or @placeholder='City']")
    private WebElement cityField;

    @FindBy(xpath = "//input[@name='zipCode' or @id='zipCode' or @placeholder='Zip Code']")
    private WebElement zipCodeField;

    @FindBy(xpath = "//select[@name='state' or @id='state']")
    private WebElement stateDropdown;

    @FindBy(xpath = "//select[@name='country' or @id='country']")
    private WebElement countryDropdown;

    // Payment Information
    @FindBy(xpath = "//input[@name='cardNumber' or @id='cardNumber' or @placeholder='Card Number']")
    private WebElement cardNumberField;

    @FindBy(xpath = "//input[@name='expiryDate' or @id='expiryDate' or @placeholder='MM/YY']")
    private WebElement expiryDateField;

    @FindBy(xpath = "//input[@name='cvv' or @id='cvv' or @placeholder='CVV']")
    private WebElement cvvField;

    @FindBy(xpath = "//input[@name='cardHolderName' or @id='cardHolderName' or @placeholder='Cardholder Name']")
    private WebElement cardHolderNameField;

    // Payment Method Selection
    @FindBy(xpath = "//input[@name='paymentMethod' and @value='creditCard']")
    private WebElement creditCardOption;

    @FindBy(xpath = "//input[@name='paymentMethod' and @value='paypal']")
    private WebElement paypalOption;

    @FindBy(xpath = "//input[@name='paymentMethod' and @value='bankTransfer']")
    private WebElement bankTransferOption;

    // Shipping Options
    @FindBy(xpath = "//input[@name='shipping' and @value='standard']")
    private WebElement standardShippingOption;

    @FindBy(xpath = "//input[@name='shipping' and @value='express']")
    private WebElement expressShippingOption;

    @FindBy(xpath = "//input[@name='shipping' and @value='overnight']")
    private WebElement overnightShippingOption;

    // Order Summary
    @FindBy(xpath = "//div[contains(@class,'order-summary')]")
    private WebElement orderSummary;

    @FindBy(xpath = "//span[contains(@class,'subtotal')]")
    private WebElement subtotalAmount;

    @FindBy(xpath = "//span[contains(@class,'shipping-cost')]")
    private WebElement shippingCost;

    @FindBy(xpath = "//span[contains(@class,'tax')]")
    private WebElement taxAmount;

    @FindBy(xpath = "//span[contains(@class,'total') or contains(@class,'grand-total')]")
    private WebElement totalAmount;

    // Buttons and Actions
    @FindBy(xpath = "//button[contains(text(),'Place Order') or contains(text(),'Complete Order')]")
    private WebElement placeOrderButton;

    @FindBy(xpath = "//button[contains(text(),'Back to Cart') or contains(text(),'Edit Cart')]")
    private WebElement backToCartButton;

    @FindBy(xpath = "//input[@type='checkbox' and (@name='terms' or @id='terms')]")
    private WebElement termsCheckbox;

    @FindBy(xpath = "//input[@type='checkbox' and (@name='newsletter' or @id='newsletter')]")
    private WebElement newsletterCheckbox;

    // Messages and Notifications
    @FindBy(xpath = "//div[contains(@class,'success') or contains(@class,'alert-success')]")
    private WebElement successMessage;

    @FindBy(xpath = "//div[contains(@class,'error') or contains(@class,'alert-danger')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[contains(@class,'validation-error')]")
    private List<WebElement> validationErrors;

    // Order Confirmation
    @FindBy(xpath = "//div[contains(@class,'order-confirmation')]")
    private WebElement orderConfirmation;

    @FindBy(xpath = "//span[contains(@class,'order-number')]")
    private WebElement orderNumber;

    @FindBy(xpath = "//h2[contains(text(),'Thank You') or contains(text(),'Order Confirmed')]")
    private WebElement confirmationTitle;

    // Methods - Navigation
    public boolean isCheckoutPageDisplayed() {
        try {
            return placeOrderButton.isDisplayed() || firstNameField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Billing Information Methods
    public void fillBillingInformation(String firstName, String lastName, String email,
                                       String phone, String address, String city,
                                       String zipCode, String state, String country) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterPhone(phone);
        enterAddress(address);
        enterCity(city);
        enterZipCode(zipCode);
        selectState(state);
        selectCountry(country);
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameField));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPhone(String phone) {
        wait.until(ExpectedConditions.visibilityOf(phoneField));
        phoneField.clear();
        phoneField.sendKeys(phone);
    }

    public void enterAddress(String address) {
        wait.until(ExpectedConditions.visibilityOf(addressField));
        addressField.clear();
        addressField.sendKeys(address);
    }

    public void enterCity(String city) {
        wait.until(ExpectedConditions.visibilityOf(cityField));
        cityField.clear();
        cityField.sendKeys(city);
    }

    public void enterZipCode(String zipCode) {
        wait.until(ExpectedConditions.visibilityOf(zipCodeField));
        zipCodeField.clear();
        zipCodeField.sendKeys(zipCode);
    }

    public void selectState(String state) {
        try {
            wait.until(ExpectedConditions.visibilityOf(stateDropdown));
            Select stateSelect = new Select(stateDropdown);
            stateSelect.selectByVisibleText(state);
        } catch (Exception e) {
            // State might not be a dropdown, could be input field
        }
    }

    public void selectCountry(String country) {
        try {
            wait.until(ExpectedConditions.visibilityOf(countryDropdown));
            Select countrySelect = new Select(countryDropdown);
            countrySelect.selectByVisibleText(country);
        } catch (Exception e) {
            // Country might not be a dropdown
        }
    }

    // Payment Information Methods
    public void fillPaymentInformation(String cardNumber, String expiryDate, String cvv, String cardHolderName) {
        enterCardNumber(cardNumber);
        enterExpiryDate(expiryDate);
        enterCVV(cvv);
        enterCardHolderName(cardHolderName);
    }

    public void enterCardNumber(String cardNumber) {
        wait.until(ExpectedConditions.visibilityOf(cardNumberField));
        cardNumberField.clear();
        cardNumberField.sendKeys(cardNumber);
    }

    public void enterExpiryDate(String expiryDate) {
        wait.until(ExpectedConditions.visibilityOf(expiryDateField));
        expiryDateField.clear();
        expiryDateField.sendKeys(expiryDate);
    }

    public void enterCVV(String cvv) {
        wait.until(ExpectedConditions.visibilityOf(cvvField));
        cvvField.clear();
        cvvField.sendKeys(cvv);
    }

    public void enterCardHolderName(String cardHolderName) {
        wait.until(ExpectedConditions.visibilityOf(cardHolderNameField));
        cardHolderNameField.clear();
        cardHolderNameField.sendKeys(cardHolderName);
    }

    // Payment Method Selection
    public void selectCreditCard() {
        wait.until(ExpectedConditions.elementToBeClickable(creditCardOption));
        if (!creditCardOption.isSelected()) {
            creditCardOption.click();
        }
    }

    public void selectPayPal() {
        wait.until(ExpectedConditions.elementToBeClickable(paypalOption));
        if (!paypalOption.isSelected()) {
            paypalOption.click();
        }
    }

    public void selectBankTransfer() {
        wait.until(ExpectedConditions.elementToBeClickable(bankTransferOption));
        if (!bankTransferOption.isSelected()) {
            bankTransferOption.click();
        }
    }

    // Shipping Methods
    public void selectStandardShipping() {
        wait.until(ExpectedConditions.elementToBeClickable(standardShippingOption));
        if (!standardShippingOption.isSelected()) {
            standardShippingOption.click();
        }
    }

    public void selectExpressShipping() {
        wait.until(ExpectedConditions.elementToBeClickable(expressShippingOption));
        if (!expressShippingOption.isSelected()) {
            expressShippingOption.click();
        }
    }

    public void selectOvernightShipping() {
        wait.until(ExpectedConditions.elementToBeClickable(overnightShippingOption));
        if (!overnightShippingOption.isSelected()) {
            overnightShippingOption.click();
        }
    }

    // Order Summary Methods
    public String getSubtotal() {
        wait.until(ExpectedConditions.visibilityOf(subtotalAmount));
        return subtotalAmount.getText();
    }

    public String getShippingCost() {
        wait.until(ExpectedConditions.visibilityOf(shippingCost));
        return shippingCost.getText();
    }

    public String getTaxAmount() {
        wait.until(ExpectedConditions.visibilityOf(taxAmount));
        return taxAmount.getText();
    }

    public String getTotalAmount() {
        wait.until(ExpectedConditions.visibilityOf(totalAmount));
        return totalAmount.getText();
    }

    // Actions
    public void acceptTermsAndConditions() {
        wait.until(ExpectedConditions.elementToBeClickable(termsCheckbox));
        if (!termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }
    }

    public void subscribeToNewsletter() {
        wait.until(ExpectedConditions.elementToBeClickable(newsletterCheckbox));
        if (!newsletterCheckbox.isSelected()) {
            newsletterCheckbox.click();
        }
    }

    public void placeOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        placeOrderButton.click();
    }

    public void backToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(backToCartButton));
        backToCartButton.click();
    }

    // Complete Checkout Process
    public void completeCheckoutProcess(String firstName, String lastName, String email,
                                        String phone, String address, String city, String zipCode,
                                        String cardNumber, String expiryDate, String cvv, String cardHolderName) {
        fillBillingInformation(firstName, lastName, email, phone, address, city, zipCode, "CA", "USA");
        selectCreditCard();
        fillPaymentInformation(cardNumber, expiryDate, cvv, cardHolderName);
        selectStandardShipping();
        acceptTermsAndConditions();
        placeOrder();
    }

    // Validation and Messages
    public boolean isSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successMessage));
            return successMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getSuccessMessage() {
        if (isSuccessMessageDisplayed()) {
            return successMessage.getText();
        }
        return "";
    }

    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        if (isErrorMessageDisplayed()) {
            return errorMessage.getText();
        }
        return "";
    }

    public List<String> getValidationErrors() {
        return validationErrors.stream()
                .map(WebElement::getText)
                .collect(java.util.stream.Collectors.toList());
    }

    public boolean hasValidationErrors() {
        return !validationErrors.isEmpty();
    }

    // Order Confirmation
    public boolean isOrderConfirmationDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(orderConfirmation));
            return orderConfirmation.isDisplayed();
        } catch (Exception e) {
            try {
                return confirmationTitle.isDisplayed();
            } catch (Exception ex) {
                return false;
            }
        }
    }

    public String getOrderNumber() {
        try {
            wait.until(ExpectedConditions.visibilityOf(orderNumber));
            return orderNumber.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isPlaceOrderButtonEnabled() {
        try {
            wait.until(ExpectedConditions.visibilityOf(placeOrderButton));
            return placeOrderButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
}
