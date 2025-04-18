package testCases;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.ExtentReportUtility;

@Listeners({ ExtentReportUtility.class })
public class TC002_LoginTest extends BaseClass {

	@Test(groups={"sanity","master"})
	public void verify_Login() {
		try {
			logger.info("*****Test case - Login Started*****");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account");
			hp.clickLogin();
			logger.info("Clicked on Login");

			LoginPage lp = new LoginPage(driver);

			logger.info("Providing customer login details....");
			
			FileInputStream file = new FileInputStream("./src//test//resources//config.properties");
			pty = new Properties();   //very important to create an object of Properties class to avoid null pointer exception. 		
			pty.load(file);
			String email = pty.getProperty("email");
			String password = pty.getProperty("password");
			
			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin();

			logger.info("Validationg expeceted message..");
			String confirmationMessage = lp.getConfirmationMsg();

			Assert.assertEquals(confirmationMessage, "My Account");
		} catch (Exception e) {
			logger.error("Exception occurred..");
			// logger.debug("Debug logs");
			Assert.fail();
		}

		logger.info("*****Test case - Login Finished*****");

	}

}
