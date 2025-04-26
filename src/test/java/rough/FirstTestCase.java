package rough;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTestCase {

	public static void main(String[] args) throws Exception {
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
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Log In']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Sign up with email']")).click();
		driver.quit();
	}
}
