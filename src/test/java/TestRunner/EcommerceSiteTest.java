package TestRunner;

import Config.BasePage;
import Pages.*;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.DriverManager;

public class EcommerceSiteTest extends BasePage {
    @Test(priority = 1,description = "Login With Valid Credential")
    public void doLoginWithValidCred() throws InterruptedException, IOException {
        driver.get("https://practice.qabrains.com/");
//        test=extent.createTest("User Authentication");
//        ExtentTest node = test.createNode("Login successfully With Valid Credential");
//        test= test.createNode("Login successfully With Valid Credential");
        EcommercePages ecommercePages=new EcommercePages(driver);
        ecommercePages.ecommerceSiteLogin();
        Thread.sleep(1000);

//        String expectedText = "Login Successful";
//        String actualText = driver.findElement(By.cssSelector("div.toaster span.title")).getText();
//        Assert.assertEquals(actualText, expectedText);
//        Thread.sleep(1000);
    }
    @Test(priority = 2, description = "Test adding items to cart and verify cart updates")
    public void testAddItemsToCart() throws InterruptedException {
        EcommercePages ecommercePages=new EcommercePages(driver);
        ecommercePages.ecommerceSiteLogin();
        Thread.sleep(1000);

        ProductListingPage productListingPage=new ProductListingPage(driver);
        CartPage cartPage=new CartPage(driver);


        // Verify products are displayed
        Assert.assertTrue(productListingPage.isProductListingPageDisplayed(),
                "Product listing page should be displayed");

        int initialCartCount = Integer.parseInt(productListingPage.getCartItemCount().replaceAll("\\D", ""));

        // Add first product to cart
        productListingPage.addFirstProductToCart();

        // Verify cart update notification
        Assert.assertTrue(productListingPage.isSuccessNotificationDisplayed(),
                "Success notification should appear after adding to cart");

        // Navigate to cart and verify item was added
        productListingPage.clickCartLink();
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page should be displayed");
        Assert.assertTrue(cartPage.getCartItemCount() > initialCartCount,
                "Cart should contain the added item");

        System.out.println("Add items to cart test completed successfully");
    }

    @Test(priority = 3, description = "Test adding items to favorites and verify presence")
    public void testAddItemsToFavorites() throws InterruptedException {
        EcommercePages ecommercePages=new EcommercePages(driver);
        ecommercePages.ecommerceSiteLogin();
        Thread.sleep(1000);

        ProductListingPage productListingPage=new ProductListingPage(driver);
        FavoritesPage favoritesPage=new FavoritesPage(driver);

        // Get first product title before adding to favorites
        String firstProductTitle = productListingPage.getProductTitle(0);

        // Add first product to favorites
        productListingPage.addFirstProductToFavorites();

        // Verify success notification
        Assert.assertTrue(productListingPage.isSuccessNotificationDisplayed(),
                "Success notification should appear after adding to favorites");

        // Navigate to favorites page
        productListingPage.clickFavoritesLink();
        Assert.assertTrue(favoritesPage.isFavoritesPageDisplayed(),
                "Favorites page should be displayed");

        // Verify the product is in favorites
        Assert.assertTrue(favoritesPage.isProductInFavorites(firstProductTitle),
                "Product should be present in favorites list");

        System.out.println("Add items to favorites test completed successfully");
    }

    @Test(priority = 4, description = "Test sorting functionality for product listings")
    public void testProductSorting() throws InterruptedException {
        EcommercePages ecommercePages=new EcommercePages(driver);
        ecommercePages.ecommerceSiteLogin();
        Thread.sleep(1000);

        ProductListingPage productListingPage=new ProductListingPage(driver);
        FavoritesPage favoritesPage=new FavoritesPage(driver);

        // Test price sorting - low to high
        productListingPage.selectSortOption("Price: Low to High");
        Assert.assertTrue(productListingPage.isPriceSortedAscending(),
                "Products should be sorted by price in ascending order");

        // Test price sorting - high to low
        productListingPage.selectSortOption("Price: High to Low");
        Assert.assertTrue(productListingPage.isPriceSortedDescending(),
                "Products should be sorted by price in descending order");

        // Test alphabetical sorting
        productListingPage.selectSortOption("Name: A to Z");
        Assert.assertTrue(productListingPage.isTitleSortedAlphabetically(),
                "Products should be sorted alphabetically");

        System.out.println("Product sorting test completed successfully");
    }

    @Test(priority = 5, description = "Test complete checkout flow")
    public void testCheckoutFlow() throws InterruptedException {
        EcommercePages ecommercePages=new EcommercePages(driver);
        ecommercePages.ecommerceSiteLogin();
        Thread.sleep(1000);

        ProductListingPage productListingPage=new ProductListingPage(driver);
        CartPage cartPage=new CartPage(driver);
        CheckoutPage checkoutPage=new CheckoutPage(driver);
        productListingPage.addFirstProductToCart();

        // Navigate to cart
        productListingPage.clickCartLink();
        Assert.assertFalse(cartPage.isCartEmpty(), "Cart should not be empty");

        // Proceed to checkout
        cartPage.proceedToCheckout();
        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed(),
                "Checkout page should be displayed");

        // Fill checkout details and complete purchase
        checkoutPage.completeCheckoutProcess(
                "John", "Doe", "john.doe@example.com", "1234567890",
                "123 Main St", "New York", "10001",
                "4111111111111111", "12/25", "123", "John Doe"
        );

        // Verify order confirmation
        Assert.assertTrue(checkoutPage.isOrderConfirmationDisplayed() ||
                        checkoutPage.isSuccessMessageDisplayed(),
                "Order confirmation should be displayed");

        System.out.println("Checkout flow test completed successfully");
    }

    @Test(priority = 6, description = "Test checkout success and error messages")
    public void testCheckoutMessages() throws InterruptedException {
        EcommercePages ecommercePages=new EcommercePages(driver);
        ecommercePages.ecommerceSiteLogin();
        Thread.sleep(1000);

        ProductListingPage productListingPage=new ProductListingPage(driver);
        CartPage cartPage=new CartPage(driver);
        CheckoutPage checkoutPage=new CheckoutPage(driver);

        // Add items to cart and proceed to checkout
        productListingPage.addFirstProductToCart();
        productListingPage.clickCartLink();
        cartPage.proceedToCheckout();

        // Test with invalid payment details
        checkoutPage.fillBillingInformation("John", "Doe", "john@example.com",
                "1234567890", "123 Main St", "New York",
                "10001", "CA", "USA");
        checkoutPage.selectCreditCard();
        checkoutPage.fillPaymentInformation("1234567890123456", "13/20", "999", "John Doe");
        checkoutPage.acceptTermsAndConditions();
        checkoutPage.placeOrder();

        // Verify error message for invalid payment
        Assert.assertTrue(checkoutPage.isErrorMessageDisplayed() ||
                        checkoutPage.hasValidationErrors(),
                "Error message should be displayed for invalid payment details");

        System.out.println("Checkout messages test completed successfully");
    }

    @Test(priority = 7, description = "Test empty cart checkout edge case")
    public void testEmptyCartCheckout() throws InterruptedException {
        EcommercePages ecommercePages=new EcommercePages(driver);
        ecommercePages.ecommerceSiteLogin();
        Thread.sleep(1000);

        ProductListingPage productListingPage=new ProductListingPage(driver);
        CartPage cartPage=new CartPage(driver);

        // Navigate directly to cart
        productListingPage.clickCartLink();

        // Verify cart is empty or clear it
        if (!cartPage.isCartEmpty()) {
            cartPage.clearCart();
        }

        Assert.assertTrue(cartPage.isCartEmpty(), "Cart should be empty");

        // Try to proceed to checkout with empty cart
        Assert.assertFalse(cartPage.isCheckoutButtonEnabled(),
                "Checkout button should be disabled for empty cart");

        System.out.println("Empty cart checkout test completed successfully");
    }

    @Test(priority = 8, description = "Test invalid payment details edge case")
    public void testInvalidPaymentDetails() throws InterruptedException {
        EcommercePages ecommercePages=new EcommercePages(driver);
        ecommercePages.ecommerceSiteLogin();
        Thread.sleep(1000);

        ProductListingPage productListingPage=new ProductListingPage(driver);
        CartPage cartPage=new CartPage(driver);

        // Add item and proceed to checkout
        productListingPage.addFirstProductToCart();
        productListingPage.clickCartLink();
        cartPage.proceedToCheckout();

        // Test various invalid payment scenarios
        testInvalidCardNumber();
        testInvalidExpiryDate();
        testInvalidCVV();

        System.out.println("Invalid payment details test completed successfully");
    }

    private void testInvalidCardNumber() {
        CheckoutPage checkoutPage=new CheckoutPage(driver);
        checkoutPage.fillBillingInformation("John", "Doe", "john@example.com",
                "1234567890", "123 Main St", "New York",
                "10001", "CA", "USA");
        checkoutPage.selectCreditCard();
        checkoutPage.enterCardNumber("1234"); // Invalid card number
        checkoutPage.enterExpiryDate("12/25");
        checkoutPage.enterCVV("123");
        checkoutPage.enterCardHolderName("John Doe");
        checkoutPage.acceptTermsAndConditions();
        checkoutPage.placeOrder();

        // Should show validation error
        Assert.assertTrue(checkoutPage.hasValidationErrors() ||
                        checkoutPage.isErrorMessageDisplayed(),
                "Should show error for invalid card number");
    }

    private void testInvalidExpiryDate() {
        CheckoutPage checkoutPage=new CheckoutPage(driver);
        checkoutPage.enterExpiryDate("13/20"); // Invalid month
        checkoutPage.placeOrder();

        Assert.assertTrue(checkoutPage.hasValidationErrors() ||
                        checkoutPage.isErrorMessageDisplayed(),
                "Should show error for invalid expiry date");
    }

    private void testInvalidCVV() {
        CheckoutPage checkoutPage=new CheckoutPage(driver);
        checkoutPage.enterCVV("12"); // Invalid CVV
        checkoutPage.placeOrder();

        Assert.assertTrue(checkoutPage.hasValidationErrors() ||
                        checkoutPage.isErrorMessageDisplayed(),
                "Should show error for invalid CVV");
    }

//    @Test(priority = 9, description = "Test accessibility of interactive elements")
//    public void testAccessibility() throws InterruptedException {
//        EcommercePages ecommercePages=new EcommercePages(driver);
//        ecommercePages.ecommerceSiteLogin();
//        Thread.sleep(1000);
//
//        ProductListingPage productListingPage=new ProductListingPage(driver);
//        FavoritesPage favoritesPage=new FavoritesPage(driver);
//        CartPage cartPage=new CartPage(driver);
//
//        // Test cart accessibility
//        Assert.assertTrue(isElementAccessible(productListingPage.getCartItemCount()),
//                "Cart should be accessible");
//
//        // Test favorites accessibility
//        productListingPage.clickFavoritesLink();
//        Assert.assertTrue(favoritesPage.isFavoritesPageDisplayed(),
//                "Favorites should be accessible");
//
//        // Test sorting accessibility
//        DriverManager.getDriver().navigate().back();
//        Assert.assertTrue(productListingPage.isProductListingPageDisplayed(),
//                "Product listing should be accessible via navigation");
//
//        // Test checkout accessibility
//        productListingPage.addFirstProductToCart();
//        productListingPage.clickCartLink();
//        Assert.assertTrue(cartPage.isCheckoutButtonEnabled() ||
//                        cartPage.getCartItemCount() > 0,
//                "Checkout should be accessible when cart has items");
//
//        System.out.println("Accessibility test completed successfully");
//    }

    private boolean isElementAccessible(String elementText) {
        // Basic accessibility check - element should have text or be clickable
        return elementText != null && !elementText.trim().isEmpty();
    }

    @Test(priority = 10, description = "Test cart quantity update")
    public void testCartQuantityUpdate() throws InterruptedException {
        EcommercePages ecommercePages=new EcommercePages(driver);
        ecommercePages.ecommerceSiteLogin();
        Thread.sleep(1000);

        ProductListingPage productListingPage=new ProductListingPage(driver);
        CartPage cartPage=new CartPage(driver);

        // Add item to cart
        productListingPage.addFirstProductToCart();
        productListingPage.clickCartLink();

        // Update quantity
        cartPage.updateQuantity(0, "3");

        // Verify cart update
        Assert.assertTrue(cartPage.verifyCartUpdate(),
                "Cart should update after quantity change");

        System.out.println("Cart quantity update test completed successfully");
    }

    @Test(priority = 11, description = "Test remove item from cart")
    public void testRemoveItemFromCart() throws InterruptedException {
        EcommercePages ecommercePages=new EcommercePages(driver);
        ecommercePages.ecommerceSiteLogin();
        Thread.sleep(1000);

        ProductListingPage productListingPage=new ProductListingPage(driver);
        CartPage cartPage=new CartPage(driver);

        // Add item to cart
        productListingPage.addFirstProductToCart();
        productListingPage.clickCartLink();

        int initialCount = cartPage.getCartItemCount();

        // Remove item
        cartPage.removeFirstItem();

        // Verify item was removed
        Assert.assertTrue(cartPage.getCartItemCount() < initialCount,
                "Item should be removed from cart");

        System.out.println("Remove item from cart test completed successfully");
    }
}
