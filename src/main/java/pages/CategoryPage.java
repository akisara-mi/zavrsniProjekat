package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoryPage extends BasePage {

    By categoryButton = By.cssSelector("a[ data-test='nav-categories']");
    By powerToolsCategory = By.cssSelector("a[data-test='nav-power-tools']");
    By checkBoxDrillCategory = By.cssSelector("input[data-test='category-11']");
    By productOfPowerToolsCategory = By.xpath("//a[@class='card']");
    By productOfDrillCategory = By.xpath("//h5[contains(text(), 'Cordless')]");

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public int selectTools() {

        clickOnElement(categoryButton);
        clickOnElement(powerToolsCategory);
        int allProducts = getNumberOfProductInPowerToolsCategory();
        clickOnElement(checkBoxDrillCategory);
        return allProducts;
    }

    public int getNumberOfProductInPowerToolsCategory() {
        List<WebElement> listOfAllProduct = driver.findElements(productOfPowerToolsCategory);
        return listOfAllProduct.size();
    }

    public int getNumberOfProductInDrillCategory() {
        List<WebElement> listOfAllProduct = driver.findElements(productOfDrillCategory);
        return listOfAllProduct.size();
    }
}