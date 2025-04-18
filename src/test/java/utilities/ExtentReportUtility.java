package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportUtility implements ITestListener {

	public ExtentSparkReporter sparkReporter; // UI of the report.
	public ExtentReports extent; // populate common info of the report.
	public ExtentTest test; // creating test case entries in the report and update status of the test
							// methods.

	String repName;

	public void onStart(ITestContext context) {

		// do not hardcode data; let the report be created with timestamp in its name

		/*
		 * SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); Date dt =
		 * new Date(); String currentDate = df.format(dt);
		 */

		String timestamp = new SimpleDateFormat("yyyy.MM.dd..mm.ss").format(new Date());
		repName = "Test-Report-" + timestamp + ".html";

		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // specific location.

		sparkReporter.config().setDocumentTitle("Automation Report"); // Title of the report.
		sparkReporter.config().setReportName("Functional testing"); // Name of the report.
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter); // combines UI along with the populated info.

		// no need to hard code. Get this info at runtime.
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("SubModule", "Customers");
		extent.setSystemInfo("UserName", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");

		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result) {
		// show test case names and test case ID ==> so that if you have 100s of test
		// cases, then its easy to understand.
		test = extent.createTest(result.getTestClass().getName()); // create a new entry in the report.
		test.assignCategory(result.getMethod().getGroups()); // To display groups in report.
		test.log(Status.PASS, "Test passed is: " + result.getName()); // update status p/f/s.
	}

	public void onTestFailure(ITestResult result) {

		// add screenshot in report.
		test = extent.createTest(result.getTestClass().getName()); // create a new entry in the report.
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL, "Test failed is: " + result.getName()); // update status p/f/s.
		test.log(Status.FAIL, "Test failed cause is: " + result.getThrowable()); // update cause of failure.

		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName()); // create a new entry in the report.
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test skipped is: " + result.getName()); // update status p/f/s.
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {
		// also write code to send email report.
		extent.flush(); // Writes test information from the started reporters to their output view.

		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		File extentReport = new File(pathOfExtentReport);

		// to automatically open the report on browser.
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// to automatically send the report via email
		/*
		 * try { URL url = new URL("file:///" + System.getProperty("user.dir") +
		 * "\\reports\\" + repName);
		 * 
		 * // Create the email message. ImageHtmlEmail email = new ImageHtmlEmail(); //
		 * Add apache commons email dependency to pom.xml.
		 * email.setDataSourceResolver(new DataSourceUrlResolver(url));
		 * email.setHostName("smtp.googleemail.com"); email.setSmtpPort(465);
		 * email.setAuthenticator(new DefaultAuthenticator("smita.sa1023@gmail.com",
		 * "Smiamr_1023")); email.setSSLOnConnect(true);
		 * email.setFrom("smita.sa1023@gmail.com"); // sender.
		 * email.setSubject("Test Results");
		 * email.setMsg("Please find the attached report.");
		 * email.addTo("rahul.gupta15990@gmail.com"); // receiver. email.attach(url,
		 * "extent report", "please check report.."); email.send(); // send the email.
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
	}

}
