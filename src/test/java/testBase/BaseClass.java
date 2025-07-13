package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

/**
 * Base class for test cases.
 * Provides common setup and teardown methods for WebDriver configuration.
 * Includes utility methods for generating random strings.
 */
public class BaseClass {
    // WebDriver instance for browser interaction
    public WebDriver driver;

    // Logger instance for logging test execution details
    public Logger logger;

    public Properties p;

    /**
     * Setup method to initialize the WebDriver and configure browser settings.
     * This method runs before any test method in the class.
     *
     * @param os The operating system on which the tests are running.
     * @param br The browser to be used for testing (e.g., chrome, firefox, edge).
     */
    @BeforeClass(groups = {"sanity", "regression", "master"})
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {

        FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        // Initialize logger for the current class
        logger = LogManager.getLogger(this.getClass());

        // Select WebDriver based on the specified browser
        switch (br) {
            case "chrome":
                driver = new ChromeDriver(); // Initialize ChromeDriver
                break;
            case "firefox":
                driver = new FirefoxDriver(); // Initialize FirefoxDriver
                break;
            case "edge":
                driver = new EdgeDriver(); // Initialize EdgeDriver
                break;
            case "safari":
                driver = new SafariDriver(); // Initialize EdgeDriver
                break;
            default:
                System.out.println("Browser not supported: " + br); // Handle unsupported browser
                return; // Exit from the test case if browser is not supported
        }

        // Configure browser settings
        driver.manage().deleteAllCookies(); // Clear browser cookies
        driver.manage().window().maximize(); // Maximize browser window
        driver.get(p.getProperty("appURL")); // Navigate to the application URL
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set implicit wait
    }

    /**
     * Tear down method to close the browser after test execution.
     * This method runs after all test methods in the class.
     */
    @AfterClass(groups = {"sanity", "regression", "master"})
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

    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        /*
        ====> TakesScreenshot takesScreenshot = (TakesScreenshot) driver; <======
        is used to enable the functionality of capturing screenshots in Selenium WebDriver. In Selenium,
        the TakesScreenshot interface provides a method to capture a screenshot of the current browser window.
        Here, the driver object, which is an instance of WebDriver, is cast to the TakesScreenshot interface.
        This casting is necessary because not all WebDriver implementations inherently support the
        TakesScreenshot interface. For example, browsers like Chrome and Firefox (via their respective drivers)
        implement this interface, allowing screenshots to be taken.
        By casting the driver to TakesScreenshot, you gain access to the getScreenshotAs method,
        which can be used to capture the screenshot and save it in a specified format (e.g., as a file ).
        This is particularly useful for debugging or reporting purposes in automated tests.
         */

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"/screenshots/" + tname + "_" + timeStamp + ".png";
        File targetFile=new File(targetFilePath);

        //move the screenshot from its temporary location (sourceFile) to the specified target location (targetFile)
        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }
}