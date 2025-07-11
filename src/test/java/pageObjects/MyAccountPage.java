package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * Page Object class for the "My Account" page.
 * Provides methods to interact with and verify elements on the "My Account" page.
 * Extends the BasePage class to inherit common page functionalities.
 */
public class MyAccountPage extends BasePage {

    /**
     * Constructor for the MyAccountPage class.
     * Initializes the WebDriver instance for interacting with the page elements.
     *
     * @param driver The WebDriver instance to be used by the page object.
     */
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    // WebElement representing the "My Account" label on the page
    @FindBy(xpath = "//h1[normalize-space()='My Account']")
    WebElement lblMyAccount;

    /**
     * Method to verify if the "My Account" page is displayed.
     * Checks the visibility of the "My Account" label on the page.
     *
     * @return true if the "My Account" page is displayed, false otherwise.
     */
    public boolean isMyAccountPageDisplayed() {
        try {
            return lblMyAccount.isDisplayed(); // Check if the "My Account" label is visible
        } catch (Exception e) {
            // Log and fail the test if the "My Account" page is not displayed
            Assert.fail("My Account page is not displayed: " + e.getMessage());
            return false; // This line will never be reached, but it's required for compilation
        }
    }
}