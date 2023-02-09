package pages;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static utils.Utils.dotEnv;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(BasePage.class.getName());
    private long waitTime = Long.parseLong(dotEnv().get("EXPLICIT_WAIT_TIME"));

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
    }

    protected WebElement getElement(By locator) {
        WebElement element = null;
        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (StaleElementReferenceException st) {
            logger.warn("Stale Element Exception occured.");
            st.printStackTrace();
            driver.findElement(locator);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return element;
    }

    protected List<WebElement> getElementList(By locator) {
        List<WebElement> elementList;
        try {
            elementList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            if (!elementList.isEmpty() && !(elementList == null)) {
                return elementList;
            }
        } catch (Exception e) {
            logger.warn("Element could not be found By locator [" + locator.toString() + "] in specified time.");
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    protected void typeIn(By locator, String text) {
        WebElement element = getElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void clickOnElement(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (ElementClickInterceptedException ex) {
            logger.warn("ElementClickInterceptedException occurred!");
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            hoverAndClick(locator);
        } catch (StaleElementReferenceException staleEl) {
            getElement(locator).click();
            logger.warn("Stale Element Exception occurred.");
        } catch (TimeoutException te) {
            te.printStackTrace();
            WebElement element = getElement(locator);
            js.executeScript("arguments[0].click();", element);
            logger.warn("TimeoutException occurred!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("FAILED - Unable to click on element " + locator.toString());
        }
    }

    protected void hoverAndClick(By locator) {
        new Actions(driver)
                .moveToElement(getElement(locator))
                .click()
                .perform();
    }

    protected void hover(By locator) {
        new Actions(driver)
                .moveToElement(getElement(locator))
                .perform();
    }

    protected boolean matchesExpectedText(By locator, String expectedText) {
        WebElement element = getElement(locator);

        if (element.getText().trim().equals(expectedText)) {
            logger.info("PASSED - Text found in element: " + element.getText() + " MATCHES expected text: " + expectedText);
            return true;
        } else {
            logger.error("FAILED - Text found in element: " + element.getText() + " DOES NOT MATCH expected text: " + expectedText);
        }
        return false;
    }

    protected void scrollToMyElement(By locator) {
        WebElement element = getElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()", element);
    }

    public String getTextFromElement(By locator) {
        return getElement(locator).getText();
    }

    public void clickOnRandomElementFromList(By locator) {
        Random random = new Random();
        List<WebElement> list = driver.findElements(locator);
        int randomElement = random.nextInt(list.size());
        list.get(randomElement).click();

    }

    public void addToCartProducts(By products, By product, By cart) {
        clickOnRandomElementFromList(products);
        if (isOutOfStock(product)) {
            driver.navigate().back();
        } else {
            clickOnElement(cart);
        }
    }

    public boolean isOutOfStock(By locator) {
        String tmp = "Out of stock";
        if (locator != null && tmp.equalsIgnoreCase(getTextFromElement(locator))) {
            return true;
        }
        return false;
    }

    List<WebElement> getElements(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return elements;
    }

}
