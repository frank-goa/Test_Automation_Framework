package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    /**
     * Constructor for the BasePage class.
     *
     * @param driver The WebDriver instance to be used by the page object.
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Add WebElement locators and methods for the LoginPage here
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    WebElement btnLogin;

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

}
