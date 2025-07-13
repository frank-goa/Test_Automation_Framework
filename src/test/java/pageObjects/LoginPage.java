// src/test/java/pageObjects/LoginPage.java
package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    /**
     * Constructor for the LoginPage class.
     *
     * @param driver The WebDriver instance to be used by the page object.
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    WebElement btnLogin;

    // Locator for the warning message (update selector if needed)
    @FindBy(css = ".alert-danger")
    WebElement warningMessage;

    public void setTxtEmail(String email) {
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    public void setTxtPassword(String password) {
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }

    public void clickLogin() {
        btnLogin.click();
    }

    /**
     * Checks if the warning message is displayed on the login page.
     *
     * @return true if the warning message is displayed, false otherwise.
     */
    public boolean isWarningMessageDisplayed() {
        try {
            return warningMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}