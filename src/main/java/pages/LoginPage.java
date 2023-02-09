package pages;

import model.LoginUserModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.List;

public class LoginPage extends BasePage {

    By emailField = By.cssSelector("input[data-test='email']");
    By passwordField = By.id("password");
    By loginButton = By.cssSelector("input[data-test='login-submit']");
    public By signInLink = By.cssSelector("a[data-test='nav-sign-in']");
    By userNameInMenu = By.id("user-menu");
    By loginError = By.cssSelector("div[data-test='login-error']");
    By passwordError = By.cssSelector("div[data-test='password-error']");
    By emailErrorMessage = By.cssSelector("div[data-test='email-error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage loginUser(String username, String password) {
        typeIn(emailField, username);
        typeIn(passwordField, password);
        clickOnElement(loginButton);
        return this;
    }

    public String logInWithWrongData() {
        clickOnElement(signInLink);
        Utils.waitForSeconds(2);
        List<LoginUserModel> list = Utils.getDataFromJson();
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys(list.get(4).getEmail());
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(list.get(4).getPassword());
        WebElement loginButton = driver.findElement(By.cssSelector("input[data-test='login-submit']"));
        loginButton.click();
        Utils.waitForSeconds(2);
        WebElement loggedInText = driver.findElement(loginError);
        return loggedInText.getText();
    }

    public String logInWithWrongEmail() {

        clickOnElement(signInLink);
        clickOnElement(emailField);
        typeIn(emailField, "a");
        typeIn(passwordField, "135552");
        clickOnElement(loginButton);
        WebElement loggedInText = driver.findElement(emailErrorMessage);
        return loggedInText.getText();

    }
    public String passwordLoginError() {
        clickOnElement(signInLink);
        clickOnElement(emailField);
        typeIn(emailField, "marija@gmail.com");
        typeIn(passwordField, "12");
        clickOnElement(loginButton);
        WebElement loggedInText = driver.findElement(passwordError);
        return loggedInText.getText();
    }

    public String withOutPasswordError() {
        clickOnElement(signInLink);
        clickOnElement(emailField);
        typeIn(emailField, "marija@gmail.com");
        typeIn(passwordField, "");
        clickOnElement(loginButton);
        WebElement loggedInText = driver.findElement(passwordError);
        return loggedInText.getText();
    }

}
