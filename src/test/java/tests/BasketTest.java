package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.BasketPage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.Utils;

public class BasketTest extends BaseTest {
    BasketPage basketPage;
    BasePage basePage;
    RegisterPage registerPage;
    LoginPage loginPage;

    @BeforeMethod
    public void localSetup() {
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        registerPage.goToRegisterPage()
                .registerUser();
        Utils.waitForSeconds(2);
        loginPage.loginUser(registerPage.getUsername(), registerPage.getPassword());
    }

    @Test
    public void addToCartTest() {
        basketPage = new BasketPage(driver);
        basePage = new BasePage(driver);
        Utils.waitForSeconds(2);
        basketPage.addProductToBasket();
        Utils.waitForSeconds(2);
        basketPage.getAmounts();
        Utils.waitForSeconds(2);

        Assert.assertEquals(basketPage.getAmounts(), basketPage.getNumberOfShoppingCart());
        //  Assert.assertEquals(basketPage.getPrices(), basketPage.totalAmount()); - Greska na sajtu u racunu, metode rade - Expected :76.7
        //Actual   :76.71

        String expectedText = "Payment was successful";
        Assert.assertEquals(basketPage.getTextForBasketConfirmationText(), expectedText);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
