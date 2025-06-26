package testCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.SearchPage;
import testBase.BaseClass;
import utilities.ExtentReportUtility;

@Listeners({ExtentReportUtility.class})
public class TC007_AddToWishlistTest extends BaseClass {

	@Test(groups= {"sanity", "master"})
	public void add_To_Wishlist() {

	try {
		logger.info("*****Test case - Add To WishList Started*****");
		HomePage hp = new HomePage(driver); 
		hp.clickMyAccount();
		logger.info("Clicked on My Account");
		hp.clickLogin();
		logger.info("Clicked on Login");

		LoginPage lp = new LoginPage(driver);

		logger.info("Providing customer login details....");
		
		lp.setEmail(getConfigProperty("email"));
		lp.setPassword(getConfigProperty("password"));
		lp.clickLogin();
		
		
		MyAccountPage map = new MyAccountPage(driver);
		
		String item = getConfigProperty("searchProductName");
		logger.info("Providing item to be searched....");
		
		map.search(item);
		map.clickSearch();
		logger.info("Clicked Search button");
		
		SearchPage sp = new SearchPage(driver);
		sp.addToWishlist();
		logger.info("Product added to Wishlist");
		
		logger.info("Validationg expeceted message..");
		Assert.assertEquals(sp.confirmWishlist(), true);
		
		sp.getWishlist();
		logger.info("Clicked Wishlist button on top");
	}catch(Exception e) {
		logger.error("Exception occurred..");
		// logger.debug("Debug logs");
		Assert.fail();
	}
	
	logger.info("*****Test case - Add To WishList Finished*****");

	}
}
