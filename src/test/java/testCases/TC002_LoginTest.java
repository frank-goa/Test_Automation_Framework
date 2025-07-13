package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

/**
 * Test case class for verifying the login functionality.
 * Extends the BaseClass to utilize common setup and teardown methods.
 */
public class TC002_LoginTest extends BaseClass {

    /**
     * Test method to verify the login functionality.
     * This method performs the following steps:
     * 1. Navigates to the "My Account" section.
     * 2. Opens the login page.
     * 3. Enters login credentials and submits the form.
     * 4. Validates whether the "My Account" page is displayed after login.
     */
    @Test (groups = {"sanity", "master"}) // TestNG annotation to define the test group
    public void verifyLogin() {

        // Log the start of the test case
        logger.info("**** Starting TC002_LoginTest... ****");

        // Navigate to the "My Account" section
        HomePage hp = new HomePage(driver);
        hp.clickOnMyAccount(); // Click on "My Account" to open account options
        logger.info("**** Clicked on My Account Link ****"); // Log the action of clicking "My Account"

        // Open the login page
        hp.clickOnLogin(); // Click on "Login" to navigate to the login page
        logger.info("**** Clicked on Login Link ****"); // Log the action of clicking "Login"

        // Enter login credentials and submit the form
        LoginPage lp = new LoginPage(driver); // Create LoginPage object to interact with the login page
        lp.setTxtEmail(p.getProperty("email")); // Set the email from properties file
        lp.setTxtPassword(p.getProperty("password")); // Set the password from properties file
        lp.clickLogin(); // Click the login button to submit the login form

        // Validate whether the "My Account" page is displayed after login
        MyAccountPage map = new MyAccountPage(driver); // Create MyAccountPage object to interact with the account page
        boolean targetpage = map.isMyAccountPageDisplayed();

        if (targetpage) {
            // Log successful login
            logger.info("**** Login successful, My Account page is displayed ****");
            // Assert that the login was successful
            Assert.assertTrue(targetpage, "Login test passed, My Account page is displayed.");
        } else {
            // Log failed login
            logger.error("**** Login failed, My Account page is not displayed ****");
            // Assert that the login was not successful
            Assert.assertTrue(targetpage, "Login test failed, My Account page is not displayed.");
        }

        // Log the end of the test case
        logger.info("**** Finished TC002_LoginTest ****");
    }
}