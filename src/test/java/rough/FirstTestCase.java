package rough;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTestCase {

	public static void sleepUsingThread(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		// Launch Chrome Browser
		WebDriver driver = new ChromeDriver();
		// Open the Webpage
		driver.get("https://www.gearsmith.in/");
		driver.manage().window().maximize();
		String actualTitle = driver.getTitle() + " : Title";
		String[] titleParts = actualTitle.split(" | ");
		if (titleParts[0].equals("Gearsmith")) {
			System.out.println("Passed");
		}
		FirstTestCase.sleepUsingThread(5);
		driver.findElement(By.xpath("//span[text()='Log In']")).click();
		FirstTestCase.sleepUsingThread(3);
		driver.findElement(By.xpath("//span[text()='Sign up with email']")).click();
		FirstTestCase.sleepUsingThread(5);
		driver.quit();
	}
}
