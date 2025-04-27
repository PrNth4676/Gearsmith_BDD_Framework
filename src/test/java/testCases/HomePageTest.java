package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class HomePageTest extends BaseClass {

	HomePage homePage;
	LoginPage loginPage;

	@Test(groups = { "Sanity", "Regression" })
	public void verifyHomePage() {
		logger.info("Verifying the Title of the Home Page");
		String actualTitle = getTitleOfPage();
		String[] titleParts = actualTitle.split(" | ");
		try {
			Assert.assertEquals("Gearsmith", titleParts[0]);
			logger.info("Title matches as expected");
		} catch (AssertionError e) {
			logger.error("Title Mismatch" + e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	@Test(groups = "Regression")
	public void verifyLogInLink() throws Exception {
		homePage = new HomePage(driver);
		logger.info("Verifying the Log In Link");
		Thread.sleep(5000);
		clickElement(homePage.LINK_logIn);
	}

	@Test
	public void verifyAccountPage() throws Exception {
		loginPage = new LoginPage(driver);
		logger.info("Verifying the Account Page after Login");
		clickElement(loginPage.BUTTON_logInWithEmail_XPATH);
		Thread.sleep(5000);
		typeValues(loginPage.TEXTFIELD_emailId_XPATH, config.getProperty("emailId"));
		typeValues(loginPage.TEXTFIELD_password_XPATH, config.getProperty("password"));
		clickElement(loginPage.BUTTON_logInWithEmail_XPATH);

	}
}
