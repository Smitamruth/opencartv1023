package testCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.ProductSearchPage;
import pageObjects.SearchPage;
import pageObjects.SuccessPage;
import testBase.BaseClass;
import utilities.ExtentReportUtility;

@Listeners({ ExtentReportUtility.class })
public class TC006_EndToEndTest extends BaseClass {

	@Test(groups= {"sanity", "master"})
	public void end_to_end_test() {
		try {
			logger.info("*****Test case - End to End Started*****");
			
		// Home Page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account");
			hp.clickLogin();
			logger.info("Clicked on Login");
			
		// Login Page
			LoginPage lp = new LoginPage(driver);

			logger.info("Providing customer login details....");
			
			lp.setEmail(getConfigProperty("email"));
			lp.setPassword(getConfigProperty("password"));
			lp.clickLogin();
		
		// MyAccountPage
			logger.info("Validationg expeceted message..");
			MyAccountPage map = new MyAccountPage(driver);
			boolean confirmationMessage = map.isMyAccountPageExists();

			Assert.assertEquals(confirmationMessage, true);
			
		// Search Page	
			String item = getConfigProperty("searchProductName"); 
			logger.info("Providing item to be searched....");
			hp.search(getConfigProperty("searchProductName"));
			hp.clickSearch();
			
			SearchPage sp = new SearchPage(driver);
			logger.info("Verifying if item(s) exist....");

			sp.ifProductExist(item);

			Assert.assertEquals(sp.ifProductExist(item), true);
			
			if (sp.ifProductExist(item)) {
				logger.info("Select the searched item");
				sp.selectProduct(item);
			} else {
				Assert.fail("No product exists");
			}
			
			ProductSearchPage psp = new ProductSearchPage(driver);
			if(psp.ifStockAvailable()) {
			psp.clearQuantity();
			psp.setQuantity(getConfigProperty("qtyOfProducts"));
			logger.info("Set the quantity of items");
			psp.clickAddToCartButton();
			logger.info("Clicked AddToCart button");
			}else {
				Assert.fail();
			}
			
			psp.clickCartButton();
			logger.info("Clicked on Cart Button on top");
			psp.clickViewCart();
			logger.info("Clicked View Cart option");
			
		// Cart Page
			CartPage cp = new CartPage(driver);
			/*
			 * if(cp.checkQuantity(getConfigProperty("qtyOfProducts"))) {
			 * cp.clickCheckout(); logger.info("Clicked on checkout"); }else {
			 * cp.updateQuantity(getConfigProperty("qtyOfProducts"));
			 * logger.info("Updated the quantity"); cp.clickCheckout();
			 * logger.info("Clicked on checkout"); }
			 */
			cp.clickCheckout();
			logger.info("Clicked on checkout");
			
		// Checkout Page
			CheckoutPage chop = new CheckoutPage(driver);
			logger.info("Clicked on Radio button for new address");
			chop.clickRadioBtn();
			logger.info("Providing shipping details..");
			
			chop.setFirstName(getConfigProperty("firstName"));
			Thread.sleep(1000);
			chop.setLastName(getConfigProperty("lastName"));
			Thread.sleep(1000);
			chop.setAddress1(getConfigProperty("address1"));
			Thread.sleep(1000);
			chop.setCity(getConfigProperty("city"));
			Thread.sleep(1000);
			chop.setPostcode(getConfigProperty("pincode"));
			Thread.sleep(1000);
			chop.setCountry(getConfigProperty("country"));
			Thread.sleep(1000);
			chop.setState(getConfigProperty("state"));
			Thread.sleep(1000);
			
			chop.clickBillingContinue();
			logger.info("Clicked continue in billing section");
			Thread.sleep(1000);
			
			/*
			 * chop.selectAddress(getConfigProperty("address1")); Thread.sleep(1000);
			 * logger.info("Selected correct address from the shipping address dropdown");
			 */
			
			chop.clickShippingContinue();
			Thread.sleep(1000);
			logger.info("Clicked continue in shipping section");
			
			chop.addComment(getConfigProperty("comment"));
			logger.info("Added comment");
			
			chop.clickDelMthdConntinue();
			Thread.sleep(1000);
			logger.info("Clicked continue in delivery method section");
			
			chop.tickTnCcheckBox();
			logger.info("Clicked T&C checkbox");
			
			Thread.sleep(1000);
			chop.clickContinuePayment();
			logger.info("Clicked continue in payment section");
			
			Thread.sleep(1000);
			chop.clickConfirm();
			logger.info("Clicked confirm on checkout page");
			
		// Success Page
			Thread.sleep(2000);
			SuccessPage sup = new SuccessPage(driver);
			logger.info("Validate the success message");
			Assert.assertEquals(sup.confirmSuccessMessage(),true);
			
			sup.clickContinue();
			logger.info("Clicked continue in success page");
			
		} catch (Exception e) {
			logger.error("Exception occurred..");
			// logger.debug("Debug logs");
			Assert.fail();
		}
		logger.info("*****Test case - End to End Started*****");
	}

}
