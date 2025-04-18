package testCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.ExtentReportUtility;

@Listeners({ ExtentReportUtility.class })
public class TC001_AccountRegistrationTest extends BaseClass{
		
	@Test(groups={"regression", "master"})
	public void verify_account_registration() {
		try {
		logger.info("*****Test case - Account Registration Started*****");
		HomePage hp = new HomePage(driver); 
		hp.clickMyAccount();
		logger.info("Clicked on My Account");
		hp.clickRegister();
		logger.info("Clicked on Register");
		
		AccountRegistrationPage reg = new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details....");
		reg.setFirstName(randomString().toUpperCase());
		reg.setLastName(randomString().toUpperCase());
		reg.setEmail(randomString()+ "@gmail.com"); 		//randomly generate email id
		reg.setTelephone(randomNumber());
		
		String pwd = randomAlphaNumeric();
		reg.setPassword(pwd);
		reg.setConfirmPassword(pwd);
		reg.setPrivatePolicy();
		
		reg.clickContinue();
		
		logger.info("Validationg expeceted message..");
		String confirmMsg = reg.getConfirmationMsg();
		
		Assert.assertEquals(confirmMsg, "Your Account Has Been Created!");
		}catch(Exception e) {
			logger.error("Exception occurred..");
			// logger.debug("Debug logs");
			Assert.fail();
		}
		
		logger.info("*****Test case - Account Registration Finished*****");
	}
	
}
