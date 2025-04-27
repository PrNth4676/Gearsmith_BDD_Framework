package testBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	public static WebDriver driver;
	static Properties config;
	static FileInputStream inputStream;
	static String browser;
	public static Logger logger;

	// BROWSER MANAGEMENT //
	@BeforeClass
	public void setUp() {
		logger = LogManager.getLogger(this.getClass()); // Initialized the Logger
		if (driver == null) {
			try {
				String configFilePath = "\\src\\test\\resources\\properties\\Config.properties";
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

		if (System.getenv("browser") != null && (!System.getenv("browser").isEmpty())) {
			browser = System.getenv("browser");
		} else {
			browser = config.getProperty("browser");
		}

		config.setProperty("browser", browser);

		if (config.getProperty("browser").equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (config.getProperty("browser").equals("chrome")) {
			driver = new ChromeDriver();
		} else if (config.getProperty("browser").equals("ie")) {
			driver = new InternetExplorerDriver();
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(config.getProperty("testURL"));
		driver.manage().window().maximize();

	}

	@AfterClass
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
		} catch (NoSuchElementException e) {
			logger.error("Failed to click on the Element" + e.getMessage());
		}
	}

	/**
	 * @author Pratik Nath
	 * @METHOD : Enter values to Element using Locator
	 **/
	public static void typeValues(String locator, String valueToEnter) {
		try {
			if (locator.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(locator)).sendKeys(valueToEnter);
			} else if (locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(locator)).sendKeys(valueToEnter);
			} else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(locator)).sendKeys(valueToEnter);
			}
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @author Pratik Nath
	 * @METHOD : Fetches the Title of the Page
	 **/
	public static String getTitleOfPage() {
		return driver.getTitle();
	}

}
