package testCases;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.ProductSearchPage;
import pageObjects.SearchPage;
import testBase.BaseClass;
import utilities.ExtentReportUtility;

@Listeners({ExtentReportUtility.class})
public class TC005_AddToCartTest extends BaseClass {

	@Test(groups= {"sanity", "master"})
	public void add_to_cart() {
		try {
			logger.info("*****Test case - Add To Cart Started*****");
			HomePage hp = new HomePage(driver);

			String item = getConfigProperty("searchProductName");
			logger.info("Providing item to be searched....");
			hp.search(item);
			hp.clickSearch();

			SearchPage sp = new SearchPage(driver);
			if (sp.ifProductExist(item)) {
				logger.info("Select the searched item");
				sp.selectProduct(item);
			} else {
				Assert.fail("No product exists");
			}

			ProductSearchPage psp = new ProductSearchPage(driver);
			psp.clearQuantity();
			psp.setQuantity(getConfigProperty("qtyOfProducts"));
			logger.info("Set the quantity of items");
			psp.clickAddToCartButton();
			logger.info("Clicked AddToCart button");

			Assert.assertEquals(psp.confirmMessage(), true);
			
		} catch (Exception e) {
			logger.error("Exception occurred..");
			// logger.debug("Debug logs");
			Assert.fail();
		}

		logger.info("*****Test case - Add To Cart Finished*****");
	}
}
