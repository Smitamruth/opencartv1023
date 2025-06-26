package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
	WebElement lnkLogout;
	
	@FindBy(xpath = "//h2[normalize-space()='My Account']")		//My Account Page Heading.
	WebElement confirmationMsg;
	
	@FindBy(xpath="//input[@placeholder='Search']")
	WebElement searchBox;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	WebElement searchBtn;
	
	public boolean isMyAccountPageExists() {
		try {
			return (confirmationMsg.isDisplayed());
		}catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}
	
	public void search(String item) {
		searchBox.sendKeys(item);
	}
	
	public void clickSearch() {
		searchBtn.click();
	}
	
}
