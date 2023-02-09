package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegisterPage;
import pages.WishListPage;
import utils.Utils;

public class WishListTest extends BaseTest {
    WishListPage wishList;
    RegisterPage registerPage;
    LoginPage loginPage;

    @BeforeMethod
    public void localSetup() {
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        registerPage.goToRegisterPage()
                .registerUser();
        loginPage.loginUser(registerPage.getUsername(), registerPage.getPassword());
    }

    @Test
    public void checkFavouritesProduct() {
        wishList = new WishListPage(driver);
        wishList.setAddToFavouritesProduct();
        String expectedText = "Combination Pliers";
        Utils.waitForSeconds(3);
        String actualText = wishList.getTextOfFavouriteProduct();
        Assert.assertEquals(actualText, expectedText);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

