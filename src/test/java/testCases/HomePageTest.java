package testCases;

import org.testng.annotations.Test;

import testBase.BaseClass;

public class HomePageTest extends BaseClass {


	@Test
	public void verifyHomePage() {
		String actualTitle = getTitleOfPage();
		String[] titleParts = actualTitle.split(" | ");
		if (titleParts[0].equals("Gearsmith")) {
			System.out.println("Passed");
		}
	}
}
