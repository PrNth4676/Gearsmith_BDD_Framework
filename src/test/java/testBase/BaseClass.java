package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public static Properties config;
	static FileInputStream inputStream;
	static String browser;
	public static Logger logger;
	public static DesiredCapabilities capabilities;
	public static ChromeOptions chromeOptions;
	public static RemoteWebDriver webDriver;

	// BROWSER MANAGEMENT //
	@BeforeClass(groups = { "Sanity", "Regression" })
	@Parameters({ "OS", "Browser" })
	public void setUp(String OS, String Browser) throws Exception {
		logger = LogManager.getLogger(this.getClass()); // Initialized the Logger
		if (driver == null) {
			try {
				String configFilePath = "\\src\\test\\resources\\properties\\config.properties";
				inputStream = new FileInputStream(System.getProperty("user.dir") + configFilePath);
			} catch (FileNotFoundException e) {
				logger.error("Failed to open Config file" + e.getMessage());
			}
			try {
				config = new Properties();
				config.load(inputStream);
			} catch (IOException e) {
				logger.error("Failed to Load Config file" + e.getMessage());
			}
		}

		switch (Browser.toLowerCase()) {
		case "chrome":
//			capabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOptions(capabilities));
//			URL url = new URI("http://localhost:4444/wd/hub").toURL();
//			webDriver = new RemoteWebDriver(url, capabilities);
//			driver = new ChromeDriver(getChromeOptions(capabilities));
			driver = new ChromeDriver();
			logger.info("Opened Chrome Browser");
			break;
		case "edge":
			driver = new EdgeDriver();
			logger.info("Opened Edge Browser");
			break;
		case "firefox":
			driver = new FirefoxDriver();
			logger.info("Opened Firefox Browser");
			break;
		default:
			logger.fatal("Invalid Browser!");
			return; // This will cause the execution to break.
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(config.getProperty("testURL"));
		driver.manage().window().maximize();
	}

	public static ChromeOptions getChromeOptions(DesiredCapabilities capabilities) {
		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--headless"); // Run when on GitHub Actions
		options.addArguments("--disable-gpu");
		options.addArguments("--no-sandbox");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		return options.merge(capabilities);
	}

	@AfterClass(groups = { "Sanity", "Regression" })
	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	// ELEMENT INTERACTION //
	/**
	 * @author Pratik Nath
	 * @METHOD : Click On the Element
	 **/
	public static void clickElement(WebElement locator) {
		try {
			locator.click();
			logger.info("Clicked on the Element : " + locator.getText());
		} catch (NoSuchElementException e) {
			logger.error("Failed to click on the Element" + e.getMessage());
		}
	}

	/**
	 * @author Pratik Nath
	 * @METHOD : Enter values to Element using Locator
	 **/
	public static void typeValues(WebElement locator, String valueToEnter) {
		try {
			locator.sendKeys(valueToEnter);
			logger.info("Entered value : " + valueToEnter + " on the field :" + locator.getText());
		} catch (NoSuchElementException e) {
			logger.error("Failed to enter values on the Element" + e.getMessage());
		}
	}

	/**
	 * @author Pratik Nath
	 * @METHOD : Fetches the Title of the Page
	 **/
	public static String getTitleOfPage() {
		return driver.getTitle();
	}

	/**
	 * @author Pratik Nath
	 * @METHOD : Captures Screenshot
	 **/
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;
	}

}
