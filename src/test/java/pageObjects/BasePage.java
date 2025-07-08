package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * BasePage serves as a parent class for all page object classes in the project.
 * It initializes the WebDriver instance and the web elements on the page using Selenium's PageFactory.
 */
public class BasePage {
    // WebDriver instance used to interact with the web browser
    WebDriver driver;

    /**
     * Constructor for the BasePage class.
     *
     * @param driver The WebDriver instance to be used by the page object.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        // Initialize web elements defined in the child classes using PageFactory
        PageFactory.initElements(driver, this);
    }
}