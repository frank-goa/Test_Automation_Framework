package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

/**
 * Test case for verifying account registration functionality.
 * This class uses Selenium WebDriver and TestNG for automation testing.
 */
public class TC001_AccountRegistrationTest extends BaseClass {

    /**
     * Test method to verify the account registration process.
     * It performs actions on the HomePage and AccountRegistrationPage to register a new account.
     * Asserts the confirmation message to validate successful registration.
     */
    @Test
    public void verifyAccountRegistration() {

        // Create HomePage object to interact with the home page
        HomePage hp = new HomePage(driver);
        hp.clickOnMyAccount(); // Click on "My Account" to open account options
        hp.clickOnRegister(); // Click on "Register" to navigate to the registration page

        // Create AccountRegistrationPage object to interact with the registration page
        AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
        regpage.setFirstName(randomString().toUpperCase()); // Set a random first name in uppercase
        regpage.setLastName(randomString().toUpperCase()); // Set a random last name in uppercase
        regpage.setEmail(randomString().toLowerCase() + "@gmail.com"); // Set a random email address
        regpage.setPassword(randomAlphaNumeric()); // Set a random alphanumeric password
        regpage.setPrivacyPolicy(); // Agree to the privacy policy
        regpage.clickContinue(); // Submit the registration form

        // Retrieve the confirmation message displayed after registration
        String confMsg = regpage.getConfirmationMessage();
        Assert.assertEquals(confMsg, "Your Account Has Been Created!",
                "Account registration failed or confirmation message is incorrect."); // Validate the confirmation message
    }

}