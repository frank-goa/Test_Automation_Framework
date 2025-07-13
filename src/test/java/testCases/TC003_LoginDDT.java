package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = {"datadriven"})
    public void verify_LoginDDT(String email, String pwd, String exp) {

        logger.info("**** Starting TC003_LoginDDT ****");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickOnMyAccount();
            hp.clickOnLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setTxtEmail(email);
            lp.setTxtPassword(pwd);
            lp.clickLogin();

            if (exp.equalsIgnoreCase("Valid")) {
                MyAccountPage myAccountPage = new MyAccountPage(driver);
                boolean isMyAccountDisplayed = myAccountPage.isMyAccountPageDisplayed();
                if (isMyAccountDisplayed) {
                    myAccountPage.clickLogout();
                    Assert.assertTrue(true, "Login succeeded as expected.");
                } else {
                    Assert.fail("Login should succeed but did not.");
                }
            } else if (exp.equalsIgnoreCase("Invalid")) {
                boolean isWarningDisplayed = lp.isWarningMessageDisplayed();
                Assert.assertTrue(isWarningDisplayed, "Login should fail but succeeded.");
            }

        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());
        }

        logger.info("**** Finished TC003_LoginDDT ****");
    }
}