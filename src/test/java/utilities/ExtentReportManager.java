package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter extentSparkReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;

    String repName;

    public void onStart(ITestContext testContext) {
		
		/*
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp=df.format(dt);
		*/


        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
        repName = "Test-Report-" + timeStamp + ".html";

        // create the report folder if it doesn't exist
        extentSparkReporter = new ExtentSparkReporter("./reports/" + repName);// specify location of the report
        extentSparkReporter.config().setDocumentTitle("opencart Automation Report"); // Title of report
        extentSparkReporter.config().setReportName("opencart Functional Testing"); // name of the report
        extentSparkReporter.config().setTheme(Theme.DARK);

        // add system information
        extentReports = new ExtentReports();

        // attach the spark reporter to the extentReports report
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Application", "opencart");
        extentReports.setSystemInfo("Module", "Admin");
        extentReports.setSystemInfo("Sub Module", "Customers");
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Environemnt", "QA");

        // Get OS and Browser from testng.xml
        String os = testContext.getCurrentXmlTest().getParameter("os");
        extentReports.setSystemInfo("Operating System", os);

        // Get Browser from testng.xml
        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extentReports.setSystemInfo("Browser", browser);

        // Get the included groups from testng.xml
        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extentReports.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result) {

        extentTest = extentReports.createTest(result.getTestClass().getName());        // create a new test in the report
        // assign the test method name to the report
        extentTest.assignCategory(result.getMethod().getGroups()); // to display groups in report
        // add the test method name to the report
        extentTest.log(Status.PASS, result.getName() + " got successfully executed");

    }

    public void onTestFailure(ITestResult result) {
        // create a new test in the report
        extentTest = extentReports.createTest(result.getTestClass().getName());
        extentTest.assignCategory(result.getMethod().getGroups());

        // add the test method name to the report
        extentTest.log(Status.FAIL, result.getName() + " got failed");
        extentTest.log(Status.INFO, result.getThrowable().getMessage());

        // add the exception message to the report
        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            extentTest.addScreenCaptureFromPath(imgPath);

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        extentTest = extentReports.createTest(result.getTestClass().getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.log(Status.SKIP, result.getName() + " got skipped");
        extentTest.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext testContext) {

        extentReports.flush();

        String pathOfExtentReport = System.getProperty("user.dir") + "//reports//" + repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*
        try {
            URL url = new URL("file:///" + System.getProperty("user.dir") + "//reports//" + repName);

            // Create the email message
            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(url));
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("franson.naz@gmail.com", "EF68@eae$"));
            email.setSSLOnConnect(true);
            email.setFrom("franson.naz@gmail.com"); //Sender
            email.setSubject("Test Results");
            email.setMsg("Please find Attached Report....");
            email.addTo("nazarethfranson@gmail.com"); //Receiver
            email.attach(url, "extentReports report", "please check report...");
            email.send(); // send the email
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }
}
