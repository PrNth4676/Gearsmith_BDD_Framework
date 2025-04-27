package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;

public class HomePageTest extends BaseClass {

	HomePage homePage;

	@Test
	public void verifyHomePage() {
		logger.info("Verifying the Title of the Home Page");
		String actualTitle = getTitleOfPage();
		String[] titleParts = actualTitle.split(" | ");
		try {
			Assert.assertEquals("Gearsmithyyy", titleParts[0]);
			logger.info("Title matches as expected");
		} catch (AssertionError e) {
			logger.error("Title Mismatch" + e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void verifyLogInLink() throws Exception {
		logger.info("Verifying the Log In Link");
		homePage = new HomePage(driver);
		Thread.sleep(5000);
		clickElement(homePage.LINK_logIn);
	}
}
