package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegisterPage;
import utils.Utils;

public class RegisterTest extends BaseTest {

    RegisterPage registerPage;
    LoginPage loginPage;

    public RegisterTest(WebDriver driver) {
    }

    @BeforeMethod
    public void localSetup() {
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        registerPage.goToRegisterPage()
                .registerUser();
        loginPage.loginUser(registerPage.getUsername(), registerPage.getPassword());
    }

    @Test
    public void registerUserTest() {
        softAssert.assertTrue(registerPage.isUserRegistered());
        softAssert.assertAll();
        Utils.waitForSeconds(3);
    }
}
