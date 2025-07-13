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
    @Test (groups = {"regression", "master"}) // TestNG annotation to define the test groups
    public void verifyAccountRegistration() {

        logger.info("**** Starting TC001_AccountRegistrationTest... ****"); // Log the start of the test case

        try {
            // Create HomePage object to interact with the home page
            HomePage hp = new HomePage(driver);
            hp.clickOnMyAccount(); // Click on "My Account" to open account options
            logger.info("**** Clicked on My Account Link ****"); // Log the action of clicking "My Account"

            hp.clickOnRegister(); // Click on "Register" to navigate to the registration page
            logger.info("**** Clicked on Register Link ****"); // Log the action of clicking "Register"

            // Create AccountRegistrationPage object to interact with the registration page
            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

            logger.info("**** Providing Account Details ****"); // Log the action of providing account details
            regpage.setFirstName(randomString().toUpperCase()); // Set a random first name in uppercase
            regpage.setLastName(randomString().toUpperCase()); // Set a random last name in uppercase
            regpage.setEmail(randomString().toLowerCase() + "@gmail.com"); // Set a random email address
            regpage.setPassword(randomAlphaNumeric()); // Set a random alphanumeric password
            regpage.setPrivacyPolicy(); // Agree to the privacy policy
            regpage.clickContinue(); // Submit the registration form

            // Retrieve the confirmation message displayed after registration
            String confMsg = regpage.getConfirmationMessage();

            logger.info("**** Registration Confirmation Message: " + confMsg + " ****"); // Log the confirmation message
            Assert.assertEquals(confMsg, "Your Account Has Been Created!",
                    "Account registration failed or confirmation message is incorrect."); // Validate the confirmation message
        } catch (Exception e) {
            logger.error("**** Exception occurred during account registration: " + e.getMessage() + " ****", e); // Log any exceptions
            Assert.fail("Test case failed due to an exception: " + e.getMessage()); // Fail the test case if an exception occurs
        }
        logger.info("**** Finished TC001_AccountRegistrationTest ****"); // Log the end of the test case
    }

}