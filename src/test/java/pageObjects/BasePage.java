package pageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	// contains only constructor initializing the web driver.

	WebDriver driver;
	public Properties pty;
	public static FileInputStream fi;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getConfigProperty(String pptyName) throws IOException {
		FileInputStream file = new FileInputStream("./src//test//resources//config.properties");
		pty = new Properties();
		pty.load(file);

		String ptyValue = pty.getProperty(pptyName);
		return ptyValue;
	}
}
