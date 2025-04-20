package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductSearchPage extends BasePage{

	public ProductSearchPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//input[@id='input-quantity']")
	WebElement qtyBox;
	
	@FindBy(xpath="//button[@id='button-cart']")
	WebElement addToCartBtn;
	
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement confirmationMsg;
	
	@FindBy(xpath="//li[normalize-space()='Availability:In Stock']")
	WebElement stockStatus;
	
	@FindBy(xpath="//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']")
	WebElement cartBtn;
	
	@FindBy(xpath="//strong[normalize-space()='View Cart']")
	WebElement viewCartBtn;
	
	public void clearQuantity() {
		qtyBox.clear();
	}
	
	public void setQuantity(String numOfItems) {
		qtyBox.sendKeys(numOfItems);
	}
	
	public void clickAddToCartButton() {
		addToCartBtn.click();
	}
	
	public boolean confirmMessage() {
		if (confirmationMsg.getText().contains("Success")) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean ifStockAvailable() {
		if(stockStatus.getText().contains("In Stock")) {
			return true;
		}else {
			return false;
		}
	}
	
	public void clickCartButton() {
		cartBtn.click();
	}
	
	
	public void clickViewCart() {
		viewCartBtn.click();
	}
}
