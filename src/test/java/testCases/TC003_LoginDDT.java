package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/**
 * Test case for verifying login functionality using data-driven testing.
 * This test validates both positive and negative login scenarios based on the provided data.
 */
public class TC003_LoginDDT extends BaseClass {

    /**
     * Verifies login functionality using data-driven testing.
     *
     * @param email The email address to be used for login.
     * @param pwd The password to be used for login.
     * @param exp The expected result ("Valid" or "Invalid").
     */
    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = {"datadriven"})
    public void verify_LoginDDT(String email, String pwd, String exp) {

        logger.info("**** Starting TC003_LoginDDT ****");

        try {
            // Navigate to the login page
            HomePage hp = new HomePage(driver);
            hp.clickOnMyAccount();
            hp.clickOnLogin();

            // Perform login action
            LoginPage lp = new LoginPage(driver);
            lp.setTxtEmail(email);
            lp.setTxtPassword(pwd);
            lp.clickLogin();

            // Validate login based on expected result
            if (exp.equalsIgnoreCase("Valid")) {
                // Positive test case: Check if My Account page is displayed
                MyAccountPage myAccountPage = new MyAccountPage(driver);
                boolean isMyAccountDisplayed = myAccountPage.isMyAccountPageDisplayed();
                if (isMyAccountDisplayed) {
                    // Logout if login is successful
                    myAccountPage.clickLogout();
                    Assert.assertTrue(true, "Login succeeded as expected.");
                } else {
                    // Fail the test if My Account page is not displayed
                    Assert.fail("Login should succeed but did not.");
                }
            } else if (exp.equalsIgnoreCase("Invalid")) {
                // Negative test case: Check if warning message is displayed
                boolean isWarningDisplayed = lp.isWarningMessageDisplayed();
                Assert.assertTrue(isWarningDisplayed, "Login should fail but succeeded.");
            }

        } catch (Exception e) {
            // Handle unexpected exceptions and fail the test
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());
        }

        logger.info("**** Finished TC003_LoginDDT ****");
    }
}