package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;
import utilities.ExtentReportUtility;

@Listeners({ ExtentReportUtility.class })
public class TC004_SearchAnItemTest extends BaseClass {

	@Test(groups= {"sanity", "master"})
	public void search_An_Item() throws IOException {
		try {
			logger.info("*****Test case - Seacrh an item Started*****");
			HomePage hp = new HomePage(driver);

			String item = getConfigProperty("searchProductName");
			logger.info("Providing item to be searched....");
			hp.search(item);
			hp.clickSearch();

			SearchPage sp = new SearchPage(driver);
			logger.info("Verifying if item(s) exist....");

			sp.ifProductExist(item);

			Assert.assertEquals(sp.ifProductExist(item), true);
		} catch (Exception e) {
			logger.error("Exception occurred..");
			// logger.debug("Debug logs");
			Assert.fail();
		}

		logger.info("*****Test case - Search an item Finished*****");
	}

}
