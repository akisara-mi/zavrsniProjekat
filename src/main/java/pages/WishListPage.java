package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Utils;

public class WishListPage extends BasePage {
    By homeF = By.xpath("//a[contains(text(), 'Home')]");
    By productWish = By.xpath("//h5[contains(text(), 'Combination Pliers')]");
    By addToFavouritesButton = By.id("btn-add-to-favorites");
    By userNameButton = By.id("user-menu");
    By favouritesProductList = By.cssSelector("a[data-test='nav-my-favorites']");
    By nameOfFavouritesProduct = By.xpath("//h5[text()='Combination Pliers']");
    By emailField = By.cssSelector("input[data-test='email']");
    By passwordField = By.id("password");
    By singIn = By.cssSelector("a[data-test='nav-sign-in']");
    By loginButton = By.cssSelector("input[data-test='login-submit']");


    public WishListPage(WebDriver driver) {
        super(driver);
    }

    public void setAddToFavouritesProduct() {
        clickOnElement(homeF);
        clickOnElement(productWish);
        clickOnElement(addToFavouritesButton);
        clickOnElement(userNameButton);
        clickOnElement(favouritesProductList);
    }
     public String getTextOfFavouriteProduct(){
       return driver.findElement(nameOfFavouritesProduct).getText();
     }
}
