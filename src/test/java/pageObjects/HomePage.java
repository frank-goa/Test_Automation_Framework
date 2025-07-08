package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * HomePage represents the page object model for the home page of the application.
 * It extends the BasePage class to inherit common functionality.
 */
public class HomePage extends BasePage {

    /**
     * Constructor for the HomePage class.
     *
     * @param driver The WebDriver instance to interact with the web browser.
     */
    public HomePage(WebDriver driver) {
        super(driver); // Invoking the constructor of the BasePage class
    }

    // Web element representing the "My Account" link on the home page
    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement lnkMyAccount;

    // Web element representing the "Register" link under the "My Account" section
    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement lnkRegister;

    /**
     * Clicks on the "My Account" link.
     */
    public void clickOnMyAccount() {
        lnkMyAccount.click();
    }

    /**
     * Clicks on the "Register" link under the "My Account" section.
     */
    public void clickOnRegister() {
        lnkRegister.click();
    }
}