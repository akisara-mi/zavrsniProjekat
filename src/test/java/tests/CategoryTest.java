package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.Utils;

public class CategoryTest extends BaseTest {

    CategoryPage categoryPage;
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
    public void checkProductNumber() {
        categoryPage = new CategoryPage(driver);
        Assert.assertTrue(categoryPage.selectTools() > categoryPage.getNumberOfProductInDrillCategory());
        Utils.waitForSeconds(3);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
