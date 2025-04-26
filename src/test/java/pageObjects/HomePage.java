package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	static @FindBy(xpath = "//span[normalize-space()='Log In']") WebElement LINK_logIn_XPATH;

}
