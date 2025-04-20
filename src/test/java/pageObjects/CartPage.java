package pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

	public CartPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@name='quantity")
	WebElement qtyBox;
	
	@FindBy(xpath="//i[@class='fa fa-refresh']")
	WebElement updateBtn;

	@FindBy(xpath = "//a[@class='btn btn-primary']")
	WebElement checkoutBtn;

	public void clickCheckout() {
		checkoutBtn.click();
	}

	public boolean checkQuantity(String qty) throws IOException {
		if(qtyBox.getText().toString().equals(qty)){
			return true;
		}else {
			return false;
		}
	}
	
	public void updateQuantity(String qty) throws IOException {
		qtyBox.clear();
		qtyBox.sendKeys(qty);
		updateBtn.click();
	}
	
}
