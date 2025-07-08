package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

import java.time.Duration;

public class TC001_AccountRegistrationTest {

    public WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("http://localhost/opencart/upload/index.php");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void verifyAccountRegistration() {
        HomePage hp = new HomePage(driver);
        hp.clickOnMyAccount();
        hp.clickOnRegister();

        AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
        regpage.setFirstName(randomString().toUpperCase());
        regpage.setLastName(randomString().toUpperCase());
        regpage.setEmail(randomString().toLowerCase() + "@gmail.com");
        regpage.setPassword(randonAlphanumericString());
        regpage.setPrivacyPolicy();
        regpage.clickContinue();

        String confMsg = regpage.getConfirmationMessage();
        Assert.assertEquals(confMsg, "Your Account Has Been Created!", "Account registration failed or confirmation message is incorrect.");
    }

    public String randomString() {
        String generatedString = RandomStringUtils.randomAlphabetic(6);
        return generatedString;
    }

    public String randonAlphanumericString() {
        String generatedString = RandomStringUtils.randomAlphabetic(3);
        String generatedNumber = RandomStringUtils.randomNumeric(3);
        return (generatedString + "@" + generatedNumber);
    }
}
