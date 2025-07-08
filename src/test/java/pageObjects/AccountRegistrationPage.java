package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * AccountRegistrationPage represents the page object model for the account registration page.
 * It provides methods to interact with the web elements on the page.
 */
public class AccountRegistrationPage extends BasePage {

    /**
     * Constructor for the AccountRegistrationPage class.
     *
     * @param driver The WebDriver instance to interact with the web browser.
     */
    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Locators for the web elements on the account registration page

    /** Web element for the "First Name" input field. */
    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txtFirstName;

    /** Web element for the "Last Name" input field. */
    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtLastName;

    /** Web element for the "Email" input field. */
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;

    /** Web element for the "Password" input field. */
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

    /** Web element for the "Privacy Policy" checkbox. */
    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkPolicy;

    /** Web element for the "Continue" button. */
    @FindBy(xpath = "//button[normalize-space()='Continue']")
    WebElement btnContinue;

    /** Web element for the confirmation message displayed after successful registration. */
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement confirmationMessage;

    // Methods to interact with the web elements

    /**
     * Enters the first name into the "First Name" input field.
     *
     * @param fName The first name to be entered.
     */
    public void setFirstName(String fName) {
        txtFirstName.sendKeys(fName);
    }

    /**
     * Enters the last name into the "Last Name" input field.
     *
     * @param lName The last name to be entered.
     */
    public void setLastName(String lName) {
        txtLastName.sendKeys(lName);
    }

    /**
     * Enters the email into the "Email" input field.
     *
     * @param email The email address to be entered.
     */
    public void setEmail(String email) {
        txtEmail.sendKeys(email);
    }

    /**
     * Enters the password into the "Password" input field.
     *
     * @param password The password to be entered.
     */
    public void setPassword(String password) {
        txtPassword.sendKeys(password);
    }

    /**
     * Clicks the "Privacy Policy" checkbox after adjusting the page zoom.
     */
    public void setPrivacyPolicy() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom = '80%';");
        chkPolicy.click();
    }

    /**
     * Clicks the "Continue" button to submit the registration form.
     */
    public void clickContinue() {
        btnContinue.click();
    }

    /**
     * Retrieves the confirmation message displayed after successful registration.
     *
     * @return The confirmation message text if found, or an error message if an exception occurs.
     */
    public String getConfirmationMessage() {
        try {
            return confirmationMessage.getText();
        } catch (Exception e) {
            return "Confirmation message not found due to an error: " + e.getMessage();
        }
    }
}