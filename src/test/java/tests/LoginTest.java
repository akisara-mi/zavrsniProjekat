package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Utils;

public class LoginTest extends BaseTest {

    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    By loginButton = By.cssSelector("input[data-test='login-submit']");
    public By signInLink = By.cssSelector("a[data-test='nav-sign-in']");

    @Test(description = "Verifying login with incorrect data")  // from Json
    public void loginNegativeTest() {

        LoginPage loginPage = new LoginPage(driver);
        String expectedText = "Invalid email or password";
        Assert.assertEquals(loginPage.logInWithWrongData(), expectedText);
    }


    @Test(description = "Verifying login with incorrect email data")

    public void loginWithWrongEmailData() {
        LoginPage loginPage = new LoginPage(driver);
        String expectedText = "E-mail format is invalid.";
        Utils.waitForSeconds(3);
        Assert.assertEquals(loginPage.logInWithWrongEmail(), expectedText);
    }

    @Test(description = "Verifying login with short password data")

    public void loginWithShortPasswordData() {
        LoginPage loginPage = new LoginPage(driver);
        String expectedText = "Password length is invalid";
        Utils.waitForSeconds(3);
        Assert.assertEquals(loginPage.passwordLoginError(), expectedText);
    }

    @Test(description = "Verifying login without password data")

    public void loginWithoutPasswordData() {
        LoginPage loginPage = new LoginPage(driver);
        String expectedText = "Password is required.";
        Utils.waitForSeconds(3);
        Assert.assertEquals(loginPage.withOutPasswordError(), expectedText);
    }
}
