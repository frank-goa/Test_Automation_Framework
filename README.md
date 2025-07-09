# Test Automation Framework

┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈

- #### This is a test automation framework designed to facilitate the testing of web applications***OpenCart
  ***. It provides a structured approach to writing and executing tests, making it easier to maintain and scale your test suite.
- #### Based on the Page Object Model (POM) design pattern, this framework allows for better organization of test code and promotes reusability of page elements and actions.
- #### Uses: Java, Selenium, TestNG, Maven and Extent Reports.
- #### This project required OpenCart running on localhost using XAMPP or any other server.

<img src="img.png" alt="Folder Structure" width="800"/>

-------------------------

### Version 0.0.1

- A blank Java project with the necessary dependencies for Selenium and TestNG.

------------------------

### Version 0.0.2

- Update pom.xml to include the dependencies.

------------------------

### Version 0.0.3

- Creating a basic structure for the framework.
- Hybrid Automation framework with POM design pattern.
- Creating a Folder structure for the framework.
- Create BasePage under "pageObjects" package which will contain only the constructor which will be invoked by every
  page object class constructor.
- Create Page Object Classes for the following pages:
  - HomePage
  - AccountRegistrationPage
- Create AccountRegistrationTest class under "testsCases" package which will contain the test case for account registration.
- Create re-usable methods in BasePage to generate random email.

<img src="folderstructure.jpg" alt="Folder Structure" width="400"/>
------------------------

### Version 0.0.4

Objective of this version is to create a Test case for account registration.
Start by creating Page Objects under the pageObjects package.
We create page objects for the following pages:

- HomePage
- AccountRegistrationPage

**BasePage**

- Serves as a parent class for all page object classes in the project.
- It initializes the WebDriver instance and the web elements on the page using Selenium's PageFactory.
- We have achieved reusability of code by creating a BasePage class.
- Initialize web elements defined in the child classes using PageFactory

------------------------

### Version 0.0.5
- Modified TC_001_AccountRegistrationTest to use the BasePage class.
- Added a method to generate random string in BasePage.
- Added a method to generate random alphaNumeric string as email in BasePage.
- Ran the test case and verified the account registration functionality.

------------------------

### Version 0.0.6
- Adding logging functionality to the framework.

Logging - record all the events in the form of text.
Log Levels (6) - ALL < TRACE < DEBUG < INFO < WARN < ERROR < FATAL < OFF

Appenders - appenders are responsible for writing the log messages to a specific destination (Console / File).
Loggers - loggers are responsible for logging the messages.

Add log4j2.xml file to the src/test/resources folder - it is a configuration file.
Start by adding the log4j2 dependency in pom.xml file.
- log4j-core
- log4j-api

Update BaseClass file to include logging functionality.
The configuration should be done in the setup method of BaseClass.


```java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {
    public WebDriver driver;
    public Logger logger;
    
    @BeforeClass
    public void setup() {
        logger = LogManager.getLogger(this.getClass());
        driver = new ChromeDriver(); // Initialize ChromeDriver
        driver.get("http://localhost/opencart/upload/index.php"); // Navigate to the application URL
    }
}
```
