package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductListingPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductListingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Product Listing Elements
    @FindBy(xpath = "//div[contains(@class,'product') or contains(@class,'item')]")
    private List<WebElement> productItems;

    @FindBy(xpath = "//select[contains(@name,'sort') or contains(@id,'sort')]")
    private WebElement sortDropdown;

    @FindBy(xpath = "//button[contains(text(),'Add to Cart') or contains(@class,'add-cart')]")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//button[contains(text(),'Add to Favorites') or contains(@class,'favorite') or contains(@class,'wishlist')]")
    private List<WebElement> addToFavoritesButtons;

    @FindBy(xpath = "//span[contains(@class,'price')]")
    private List<WebElement> productPrices;

    @FindBy(xpath = "//h3[contains(@class,'product-title') or contains(@class,'item-name')]")
    private List<WebElement> productTitles;

    @FindBy(xpath = "//div[contains(@class,'cart-count') or contains(@class,'cart-items')]")
    private WebElement cartItemCount;

    @FindBy(xpath = "//a[contains(@href,'cart') or contains(@class,'cart')]")
    private WebElement cartLink;

    @FindBy(xpath = "//a[contains(@href,'favorites') or contains(@href,'wishlist') or contains(@class,'favorites')]")
    private WebElement favoritesLink;

    @FindBy(xpath = "//div[contains(@class,'success') or contains(@class,'notification')]")
    private WebElement successNotification;

    @FindBy(xpath = "//input[contains(@placeholder,'Search') or contains(@name,'search')]")
    private WebElement searchBox;

    @FindBy(xpath = "//button[contains(@type,'submit') or contains(@class,'search-btn')]")
    private WebElement searchButton;

    // Methods
    public boolean isProductListingPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(productItems));
            return productItems.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public int getProductCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productItems));
        return productItems.size();
    }

    public void addProductToCart(int productIndex) {
        if (productIndex < addToCartButtons.size()) {
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButtons.get(productIndex)));
            addToCartButtons.get(productIndex).click();
        }
    }

    public void addFirstProductToCart() {
        addProductToCart(0);
    }

    public void addProductToFavorites(int productIndex) {
        if (productIndex < addToFavoritesButtons.size()) {
            wait.until(ExpectedConditions.elementToBeClickable(addToFavoritesButtons.get(productIndex)));
            addToFavoritesButtons.get(productIndex).click();
        }
    }

    public void addFirstProductToFavorites() {
        addProductToFavorites(0);
    }

    public void selectSortOption(String sortOption) {
        wait.until(ExpectedConditions.visibilityOf(sortDropdown));
        Select sort = new Select(sortDropdown);
        sort.selectByVisibleText(sortOption);

        // Wait for sorting to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public List<String> getProductTitles() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productTitles));
        List<String> titles = new ArrayList<>();
        for (WebElement title : productTitles) {
            titles.add(title.getText());
        }
        return titles;
    }

    public List<Double> getProductPrices() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productPrices));
        List<Double> prices = new ArrayList<>();
        for (WebElement price : productPrices) {
            String priceText = price.getText().replaceAll("[^0-9.]", "");
            if (!priceText.isEmpty()) {
                prices.add(Double.parseDouble(priceText));
            }
        }
        return prices;
    }

    public boolean isPriceSortedAscending() {
        List<Double> prices = getProductPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPriceSortedDescending() {
        List<Double> prices = getProductPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) < prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isTitleSortedAlphabetically() {
        List<String> titles = getProductTitles();
        for (int i = 0; i < titles.size() - 1; i++) {
            if (titles.get(i).compareToIgnoreCase(titles.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    public String getCartItemCount() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartItemCount));
            return cartItemCount.getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public void clickCartLink() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink));
        cartLink.click();
    }

    public void clickFavoritesLink() {
        wait.until(ExpectedConditions.elementToBeClickable(favoritesLink));
        favoritesLink.click();
    }

    public boolean isSuccessNotificationDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successNotification));
            return successNotification.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getSuccessNotificationText() {
        if (isSuccessNotificationDisplayed()) {
            return successNotification.getText();
        }
        return "";
    }

    public void searchProduct(String productName) {
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.clear();
        searchBox.sendKeys(productName);
        searchButton.click();
    }

    public String getProductTitle(int index) {
        if (index < productTitles.size()) {
            return productTitles.get(index).getText();
        }
        return "";
    }

    public void clickProduct(int index) {
        if (index < productItems.size()) {
            wait.until(ExpectedConditions.elementToBeClickable(productItems.get(index)));
            productItems.get(index).click();
        }
    }
}
