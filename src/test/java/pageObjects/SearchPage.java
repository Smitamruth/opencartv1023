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
	
	@FindBy(xpath="//button[@type='button']//i[@class='fa fa-heart']")
	WebElement wishlistBtn;

	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	WebElement wishlistConfirmationMsg;
	
	@FindBy(xpath="//i[@class='fa fa-heart']")
	WebElement wishlistBtnOnTop;
	
	public boolean ifProductExist(String item) {
		if (product.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void selectProduct(String item) throws IOException {
		if (itemName.getText().toLowerCase().contains(item.toLowerCase())) {
			itemName.click();
		}
	}
	
	public void addToWishlist() {
		wishlistBtn.click();
	}
	
	public boolean confirmWishlist() {
		if(wishlistConfirmationMsg.getText().contains("Success:")) {
			return true;
		}else {
			return false;
		}
	}
	
	public void getWishlist() {
		wishlistBtnOnTop.click();
	}
}
