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