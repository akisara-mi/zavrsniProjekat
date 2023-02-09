package tests;

import core.DriverManager;
import core.Environment;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import utils.Utils;

public class BaseTest {

    WebDriver driver;
    protected SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void setup(){
        driver = DriverManager.getInstance().setDriver();
        new Environment(driver).openBrowser();
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }



}
