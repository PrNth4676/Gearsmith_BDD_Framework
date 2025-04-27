package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public @FindBy(xpath = "(//button[normalize-space()='Log In'])[2]") WebElement LINK_logIn_XPATH;
	public @FindBy(xpath = "//span[normalize-space()='Log in with Email']") WebElement BUTTON_logInWithEmail_XPATH;
	public @FindBy(xpath = "//input[contains(@id, 'input_emailInput')]") WebElement TEXTFIELD_emailId_XPATH;
	public @FindBy(xpath = "//input[contains(@id, 'input_passwordInput')]") WebElement TEXTFIELD_password_XPATH;

}
