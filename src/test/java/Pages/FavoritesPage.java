package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FavoritesPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public FavoritesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Favorites Page Elements
    @FindBy(xpath = "//div[contains(@class,'favorite-item') or contains(@class,'wishlist-item')]")
    private List<WebElement> favoriteItems;

    @FindBy(xpath = "//h2[contains(text(),'Favorites') or contains(text(),'Wishlist')]")
    private WebElement favoritesTitle;

    @FindBy(xpath = "//div[contains(@class,'empty-favorites') or contains(text(),'no favorites')]")
    private WebElement emptyFavoritesMessage;

    @FindBy(xpath = "//button[contains(text(),'Remove') or contains(@class,'remove')]")
    private List<WebElement> removeFromFavoritesButtons;

    @FindBy(xpath = "//button[contains(text(),'Add to Cart') or contains(@class,'add-cart')]")
    private List<WebElement> addToCartFromFavoritesButtons;

    @FindBy(xpath = "//h4[contains(@class,'product-name') or contains(@class,'item-title')]")
    private List<WebElement> favoriteProductTitles;

    @FindBy(xpath = "//span[contains(@class,'price')]")
    private List<WebElement> favoriteProductPrices;

    @FindBy(xpath = "//img[contains(@class,'product-image')]")
    private List<WebElement> favoriteProductImages;

    @FindBy(xpath = "//button[contains(text(),'Continue Shopping') or contains(text(),'Back')]")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//div[contains(@class,'success') or contains(@class,'alert-success')]")
    private WebElement successMessage;

    @FindBy(xpath = "//div[contains(@class,'error') or contains(@class,'alert-danger')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//span[contains(@class,'favorites-count')]")
    private WebElement favoritesCountDisplay;

    // Methods
    public boolean isFavoritesPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(favoritesTitle));
            return favoritesTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFavoritesEmpty() {
        try {
            wait.until(ExpectedConditions.visibilityOf(emptyFavoritesMessage));
            return emptyFavoritesMessage.isDisplayed();
        } catch (Exception e) {
            return favoriteItems.isEmpty();
        }
    }

    public int getFavoritesCount() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(favoriteItems));
            return favoriteItems.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public List<String> getFavoriteProductTitles() {
        wait.until(ExpectedConditions.visibilityOfAllElements(favoriteProductTitles));
        return favoriteProductTitles.stream()
                .map(WebElement::getText)
                .collect(java.util.stream.Collectors.toList());
    }

    public List<String> getFavoriteProductPrices() {
        wait.until(ExpectedConditions.visibilityOfAllElements(favoriteProductPrices));
        return favoriteProductPrices.stream()
                .map(WebElement::getText)
                .collect(java.util.stream.Collectors.toList());
    }

    public void removeFromFavorites(int itemIndex) {
        if (itemIndex < removeFromFavoritesButtons.size()) {
            wait.until(ExpectedConditions.elementToBeClickable(removeFromFavoritesButtons.get(itemIndex)));
            removeFromFavoritesButtons.get(itemIndex).click();
        }
    }

    public void removeFirstFavorite() {
        removeFromFavorites(0);
    }

    public void addToCartFromFavorites(int itemIndex) {
        if (itemIndex < addToCartFromFavoritesButtons.size()) {
            wait.until(ExpectedConditions.elementToBeClickable(addToCartFromFavoritesButtons.get(itemIndex)));
            addToCartFromFavoritesButtons.get(itemIndex).click();
        }
    }

    public void addFirstFavoriteToCart() {
        addToCartFromFavorites(0);
    }

    public boolean isProductInFavorites(String productTitle) {
        List<String> favoritesTitles = getFavoriteProductTitles();
        return favoritesTitles.stream()
                .anyMatch(title -> title.toLowerCase().contains(productTitle.toLowerCase()));
    }

    public void continueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        continueShoppingButton.click();
    }

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

    public String getFavoritesCountDisplay() {
        try {
            wait.until(ExpectedConditions.visibilityOf(favoritesCountDisplay));
            return favoritesCountDisplay.getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public void clearAllFavorites() {
        while (getFavoritesCount() > 0) {
            removeFirstFavorite();
            try {
                Thread.sleep(1000); // Wait for removal to process
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public boolean verifyFavoriteAdded(String expectedProductTitle) {
        try {
            Thread.sleep(2000); // Wait for favorites to update
            return isProductInFavorites(expectedProductTitle);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public void clickFavoriteProduct(int index) {
        if (index < favoriteItems.size()) {
            wait.until(ExpectedConditions.elementToBeClickable(favoriteItems.get(index)));
            favoriteItems.get(index).click();
        }
    }
}
