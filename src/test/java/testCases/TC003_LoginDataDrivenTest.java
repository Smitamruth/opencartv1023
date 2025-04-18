package testCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.ExtentReportUtility;

@Listeners({ ExtentReportUtility.class })
public class TC003_LoginDataDrivenTest extends BaseClass{

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="dataDriven")	//getting data provider from different class.
	public void verify_Login(String email, String pwd, String expRes) {
		try {
			logger.info("*****Test case - Data Driven Test for Login Started*****");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account");
			hp.clickLogin();
			logger.info("Clicked on Login");

			LoginPage lp = new LoginPage(driver);

			logger.info("Providing customer login details....");
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();

			logger.info("Validationg expeceted message..");
			MyAccountPage myAcc = new MyAccountPage(driver);
			boolean targetPage = myAcc.isMyAccountPageExists(); // if true, login is successful.

			
			/*Data is valid -- Login Success - Test pass - Login Success - Logout
			Data is valid -- Login Failed - Test Fail
			Data is invalid -- Login Success - Test Fail - Logout
			Data is invalid -- Login Failed - Test Pass*/

			if(expRes.equalsIgnoreCase("valid")) {
				if(targetPage==true) {
					Assert.assertTrue(true);
					myAcc.clickLogout();
				}else {
					Assert.assertTrue(false);
				}
			}else {
				if(targetPage==true) {
					Assert.assertTrue(false);
					myAcc.clickLogout();
				}else {
					Assert.assertTrue(true); 
				}
			}
			
			Assert.assertEquals(targetPage, true);
		} catch (Exception e) {
			logger.error("Exception occurred..");
			// logger.debug("Debug logs");
			Assert.fail();
		}

		logger.info("*****Test case - Data Driven Test for Login Finished*****");
	}
	
}
