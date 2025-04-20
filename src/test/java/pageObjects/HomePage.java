package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	WebDriver driver;

//constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}

//locators

	@FindBy(xpath="//a[@title='My Account']")
	WebElement lnkMyAccount;

	@FindBy(xpath="(//a[normalize-space()='Register'])[1]")
	WebElement lnkRegister;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	@FindBy(xpath="//input[@placeholder='Search']")
	WebElement searchBox;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	WebElement searchBtn;
	
//action methods
	public void clickMyAccount() {
		lnkMyAccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}
	
	public void search(String item) {
		searchBox.sendKeys(item);
	}
	
	public void clickSearch() {
		searchBtn.click();
	}
}
