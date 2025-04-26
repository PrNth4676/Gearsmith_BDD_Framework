package testBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	
	static WebDriver driver;
	static Properties config;
	static FileInputStream inputStream;
	static String browser;
	
	// BROWSER MANAGEMENT //
		@BeforeClass
		public static void setUp() {
			if (driver == null) {
				try {
					inputStream = new FileInputStream(
							System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				}
				try {
					config = new Properties();
					config.load(inputStream);
				} catch (IOException e) {
					System.out.println(e.getMessage());
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
		public static void clickElement(String locator) {
			try {
				if (locator.endsWith("_CSS")) {
					driver.findElement(By.cssSelector(locator));
				} else if (locator.endsWith("_XPATH")) {
					driver.findElement(By.xpath(locator));
				} else if (locator.endsWith("_ID")) {
					driver.findElement(By.id(locator));
				}
			} catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
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
