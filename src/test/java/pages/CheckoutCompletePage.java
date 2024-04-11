package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends CartListContainer {
    public static String URL = BASE_URL + "checkout-complete.html";
    @FindBy(css = ".complete-header")
    private WebElement completeHeader;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void waitPageToLoad() {
        mediumWait.until(ExpectedConditions.urlToBe(URL));
        mediumWait.until(ExpectedConditions.visibilityOf(completeHeader));
    }

    public String getHeaderText() {
        mediumWait.until(ExpectedConditions.visibilityOf(completeHeader));
        return completeHeader.getText();
    }
}
