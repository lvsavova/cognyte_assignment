package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    public static final String BASE_URL = "https://www.saucedemo.com/";

    protected WebDriver driver;
    protected WebDriverWait mediumWait;
    protected WebDriverWait smallWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        mediumWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        smallWait = new WebDriverWait(driver, Duration.ofSeconds(1));
    }

    protected abstract void waitPageToLoad();

    protected void clickElement(WebElement element) {
        mediumWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected void enterText(WebElement element, String text) {
        mediumWait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }
}
