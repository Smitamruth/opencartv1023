package pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

	public SearchPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//div[@class='product-thumb']")
	WebElement product;

	@FindBy(xpath = "//div[@class='caption']//h4//a")
	WebElement itemName;

	public boolean ifProductExist(String item) {
		if (product.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void selectProduct(String item) throws IOException {
		if (itemName.getText().toLowerCase().contains(getConfigProperty("searchProductName").toLowerCase())) {
			itemName.click();
		}
	}
}
