package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	static @FindBy(xpath = "(//button[normalize-space()='Log In'])[2]") WebElement LINK_logIn_XPATH;

}
