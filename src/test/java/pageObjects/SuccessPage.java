package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SuccessPage extends BasePage {

	public SuccessPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//h1[normalize-space()='Your order has been placed!']")
	WebElement successMsg;
	
	@FindBy(xpath="//a[normalize-space()='Continue']")
	WebElement continueBtn;
	
	public boolean confirmSuccessMessage() {
		if(successMsg.getText().equals("Your order has been placed!")) {
			return true;
		}else {
			return false;
		}
	}
	
	public void clickContinue() {
		continueBtn.click();
	}
}
