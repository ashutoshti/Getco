package com.qa.getco.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.getco.Browser.BrowserConfiguration;
import com.qa.getco.pages.ErrorMatricsPage;
import com.qa.getco.pages.HomePage;
import com.qa.getco.pages.LoginPage;

public class ErrormatricsTestcasesWind extends BrowserConfiguration {

	LoginPage loginPage;
	HomePage homePage;
	ErrorMatricsPage errorMatricsPage;

	public ErrormatricsTestcasesWind() {
		super();
	}

	@BeforeTest
	public void openbrowser() {
		browserTearUp();
		loginPage = new LoginPage();
		homePage = new HomePage();
		homePage = loginPage.login(properties.getProperty("emailid"), properties.getProperty("password"));
		errorMatricsPage=homePage.openErrorMatrics();
	}
	
	@Test(priority=1)
	public void verifyingPage() {
		String name=errorMatricsPage.verifyPage();
		Assert.assertEquals("Error Matrices", name);
	}
	
	@Test(priority=2)
	public void errorMatricsopenWind() {
		errorMatricsPage.ErrorMatricsWind();
	}
}

