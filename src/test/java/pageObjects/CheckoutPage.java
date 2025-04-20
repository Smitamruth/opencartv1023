package pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-payment-firstname']")
	WebElement fNameTxtBox;

	@FindBy(xpath = "//input[@id='input-payment-lastname']")
	WebElement lNameTxtBox;

	@FindBy(xpath = "//input[@id='input-payment-address-1']")
	WebElement add1TxtBox;

	@FindBy(xpath = "//input[@id='input-payment-city']")
	WebElement cityTxtBox;

	@FindBy(xpath = "//input[@id='input-payment-postcode']")
	WebElement postcodeTxtBox;

	@FindBy(xpath = "//select[@id='input-payment-country']")
	WebElement countryDrpDown;

	@FindBy(xpath = "//select[@id='input-payment-zone']")
	WebElement regionDrpDown;

	@FindBy(xpath = "//input[@id='button-payment-address']")
	WebElement billingContinueBtn;
	
	@FindBy(xpath="//input[@value='new']")
	WebElement newRadioBtn;

	@FindBy(xpath = "//div[@id='shipping-existing']//select[@name='address_id']")
	WebElement addressDrpDown;

	@FindBy(xpath = "//input[@id='button-shipping-address']")
	WebElement shippingContinueBtn;

	@FindBy(xpath = "//textarea[@name='comment']")
	WebElement commentBox;

	@FindBy(xpath = "//input[@id='button-shipping-method']")
	WebElement delMthdContinueBtn;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement TnCchkBox;
	
	@FindBy(xpath="//input[@id='button-payment-method']")
	WebElement paymentContinueBtn;
	
	@FindBy(xpath="//input[@id='button-confirm']")
	WebElement finalconfirmBtn;

	public void setFirstName(String fname) throws IOException {
		fNameTxtBox.sendKeys(fname);
	}

	public void setLastName(String lname) throws IOException {
		lNameTxtBox.sendKeys(lname);
	}

	public void setAddress1(String address1) throws IOException {
		add1TxtBox.sendKeys(address1);
	}

	public void setCity(String city) throws IOException {
		cityTxtBox.sendKeys(city);
	}

	public void setPostcode(String postcode) throws IOException {
		postcodeTxtBox.sendKeys(postcode);
	}

	public void setCountry(String country) throws IOException {
		Select sel = new Select(countryDrpDown);
		sel.selectByContainsVisibleText(country);
	}

	public void setState(String state) throws IOException {
		Select sel = new Select(regionDrpDown);
		sel.selectByContainsVisibleText(state);
	}

	public void clickBillingContinue() {
		billingContinueBtn.click();
	}

	public void selectAddress(String address) throws IOException {
		Select sel = new Select(addressDrpDown);
		sel.selectByContainsVisibleText(address);
	}

	public void clickShippingContinue() {
		shippingContinueBtn.click();
	}

	public void addComment(String comment) {
		commentBox.sendKeys(comment);
	}

	public void clickDelMthdConntinue() {
		delMthdContinueBtn.click();
	}

	public void tickTnCcheckBox() {
		TnCchkBox.click();
	}
	
	public void clickContinuePayment() {
		paymentContinueBtn.click();
	}
	public void clickConfirm() {
		finalconfirmBtn.click();
	}
	
	public void clickRadioBtn() {
		newRadioBtn.click();
	}
}
