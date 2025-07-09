package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

/**
 * Base class for test cases.
 * Provides common setup and teardown methods for WebDriver configuration.
 * Includes utility methods for generating random strings.
 */
public class BaseClass {
    // WebDriver instance for browser interaction
    public WebDriver driver;
    public Logger logger;

    /**
     * Setup method to initialize the WebDriver and configure browser settings.
     * This method runs before any test method in the class.
     */
    @BeforeClass
    public void setup() {
        logger = LogManager.getLogger(this.getClass());
        driver = new ChromeDriver(); // Initialize ChromeDriver
        driver.manage().deleteAllCookies(); // Clear browser cookies
        driver.manage().window().maximize(); // Maximize browser window
        driver.get("http://localhost/opencart/upload/index.php"); // Navigate to the application URL
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set implicit wait
    }

    /**
     * Tear down method to close the browser after test execution.
     * This method runs after all test methods in the class.
     */
    @AfterClass
    public void tearDown() {
        driver.quit(); // Close the browser and end WebDriver session
    }

    /**
     * Utility method to generate a random alphabetic string.
     *
     * @return A random string of 5 alphabetic characters.
     */
    public String randomString() {
        String generatedString = RandomStringUtils.randomAlphabetic(5); // Generate random alphabetic string
        return generatedString;
    }

    /**
     * Utility method to generate a random alphanumeric string.
     *
     * @return A random alphanumeric string in the format "ABC@123".
     */
    public String randomAlphaNumeric() {
        String generatedString = RandomStringUtils.randomAlphabetic(3); // Generate random alphabetic string
        String generatedNumber = RandomStringUtils.randomNumeric(3); // Generate random numeric string
        return (generatedString + "@" + generatedNumber); // Combine alphabetic and numeric strings
    }
}