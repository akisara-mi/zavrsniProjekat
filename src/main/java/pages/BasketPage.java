package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BasketPage extends BasePage {

    By home = By.xpath("//a[contains(text(), 'Home')]");
    By productOne = By.xpath("//*[contains(text(), 'Combination Pliers')]");
    By productTwo = By.xpath("//*[contains(text(), 'Bolt Cutters')]");
    By productsAddToCart = By.id("btn-add-to-cart");
    By plus = By.cssSelector(".fa-plus");
    By shoppingCart = By.xpath("//*[@id='navbarSupportedContent']/ul/li[5]/a");   ////a[starts-with(@href, '#/checkout')]
    By basketNumOfProducts = By.id("lblCartCount");
    By numberOfProductInCart = By.cssSelector(".form-control.quantity");
    By productPricesCP = By.xpath("//td[contains(text(),'$14.15')]"); //span[@data-test="unit-price"]
    By productPricesP = By.xpath("//td[4][contains(text(),'$12.01')]");
    By productPrices = By.cssSelector(".col-md-2.align-middle");
    By buttonOfCheckout1 = By.cssSelector("button[data-test='proceed-1']");
    By buttonOfCheckout2 = By.cssSelector("button[data-test='proceed-2']");
    By buttonOfCheckout3 = By.cssSelector("button[data-test='proceed-3']");
    By payment = By.id("#payment-method");
    By accountName = By.id("#account-name");
    By accountNumber = By.id("account-number");
    By confirmButton = By.cssSelector("//button[contains(text(), 'Confirm')]");
    public By confirmPaymentText = By.cssSelector(".help-block");
    By email = By.id("email");
    By password = By.id("password");
    By loginButtonPayment = By.cssSelector("input[data-test='login-submit']");
    By registerLink = By.cssSelector("a[data-test='register-link']");
    By goToRegisterFormLink = By.cssSelector("a[data-test='register-link']");
    By loginButton = By.cssSelector("input[data-test='login-submit']");
    By totalAmount = By.xpath("");
    By giftCard = By.xpath("//option[text()='Gift Card']");

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public void addProductToBasket() {
        clickOnElement(home);
        hoverAndClick(productOne);
        clickOnElement(plus);
        clickOnElement(productsAddToCart);
        clickOnElement(home);
        hoverAndClick(productTwo);
        clickOnElement(productsAddToCart);
        clickOnElement(shoppingCart);
        clickOnElement(buttonOfCheckout1);
        clickOnElement(buttonOfCheckout2);
        clickOnElement(buttonOfCheckout3);
        clickOnElement(payment);
        Utils.waitForSeconds(3);
        hoverAndClick(giftCard);
        Utils.waitForSeconds(2);
        clickOnElement(accountName);
        typeIn(accountName,"Mara");
        clickOnElement(accountNumber);
        typeIn(accountNumber,"12345");
        clickOnElement(confirmButton);
    }

    public int getAmounts() {
        List<WebElement> listAmount = driver.findElements(numberOfProductInCart);
        int sum = 0;
        for (WebElement num : listAmount) {
            sum += Integer.parseInt(num.getAttribute("value"));
        }
        return sum;
    }

    public int getNumberOfShoppingCart() {
        String num = driver.findElement(basketNumOfProducts).getText();
        return Integer.parseInt(num);
    }

    public double getPrices() {
        double sum = 0;
        List<WebElement> listAmount = driver.findElements(numberOfProductInCart);
        List<WebElement> totalListOfPrices = driver.findElements(productPrices);
        List<WebElement> listOfPrices = new ArrayList<>();
        for (int i = 1; i < totalListOfPrices.size(); i += 3) {
            listOfPrices.add(totalListOfPrices.get(i));
        }
        for (int j = 0; j < listAmount.size(); j++) {
            sum += (Integer.parseInt(listAmount.get(j).getAttribute("value")) * Double.valueOf(listOfPrices.get(j).getText().substring(1)));
        }
        return sum;
    }

    public double totalAmount() {
        return Double.parseDouble(driver.findElement(totalAmount).getText().substring(1));
    }

    public void selectPaymentMethod(By locator) {
        Select objSelect = new Select(driver.findElement(By.id("#payment-method")));
        objSelect.selectByVisibleText("5: Gift Card");
    }

    public String getTextForBasketConfirmationText(){

        WebElement loggedInText = driver.findElement(confirmPaymentText);
        return loggedInText.getText();
    }
}





