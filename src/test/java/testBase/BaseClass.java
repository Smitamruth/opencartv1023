package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver; // to avoid the conflict of creating 2 instances of driver in BaseClass(inside
									// switch stmts) and ExtentReportUtility(cz we are creating new object of base
									// class here before calling captureScreen()).
	public Logger logger; // from log4j
	public Properties pty;
	public static FileInputStream fi;

	@BeforeClass(groups = { "sanity", "regression", "dataDriven", "master" })
	@Parameters({ "os", "browser" })
	public void setUp(String os, String browser) throws IOException {

		// loading config file -can use FIleReader or FIleInputStream.
		FileInputStream file = new FileInputStream("./src//test//resources//config.properties");
		pty = new Properties(); // very important to create an object of Properties class to avoid null pointer
								// exception.
		pty.load(file);
		String url = pty.getProperty("appUrl");
		logger = LogManager.getLogger(this.getClass()); // automatically gives log4j class into the variable logger.

		
		/*
		 * //this is additional setup for Selenium Grid. if
		 * (pty.getProperty("execution_env").equalsIgnoreCase("remote")) {
		 * DesiredCapabilities cap = new DesiredCapabilities();
		 * 
		 * // os if (os.equalsIgnoreCase("windows")) { cap.setPlatform(Platform.WIN11);
		 * } else if (os.equalsIgnoreCase("mac")) { cap.setPlatform(Platform.MAC); }
		 * else { System.out.println("No matching OS"); return; }
		 * 
		 * // browser switch (browser.toLowerCase()) { case "chrome":
		 * cap.setBrowserName("chrome"); break; case "edge": cap.setBrowserName("edge");
		 * break; default: System.out.println("Invalid Browser!!"); return; } driver =
		 * new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap); }
		 */
		//if (pty.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid Browser!!");
				return;
			}
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			driver.get(url);
			driver.manage().window().maximize();
		
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		String randomString = RandomStringUtils.randomAlphabetic(5); // RandomStringUtils is a java util class.
		return randomString;
	}

	public String randomNumber() {
		String randomNumber = RandomStringUtils.randomNumeric(10); // RandomStringUtils is a java util class.
		return randomNumber;
	}

	public String randomAlphaNumeric() {
		String randomString = RandomStringUtils.randomAlphabetic(4); // RandomStringUtils is a java util class.
		String randomNumber = RandomStringUtils.randomNumeric(4); // RandomStringUtils is a java util class.

		return (randomString + "@" + randomNumber);
	}

	public String captureScreen(String testname) {
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + testname + "_" + timestamp;
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;
	}
	
	public String getConfigProperty(String pptyName) throws IOException {
		FileInputStream file = new FileInputStream("./src//test//resources//config.properties");
		pty = new Properties();
		pty.load(file);
		
		String ptyValue = pty.getProperty(pptyName);
		return ptyValue;
	}

}
