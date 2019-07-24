package com.qa.getco.testcases;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.getco.Browser.BrowserConfiguration;
import com.qa.getco.pages.ErrorMatricsPage;
import com.qa.getco.pages.HomePage;
import com.qa.getco.pages.LoginPage;

public class ErrormatricsTestcasesDemand extends BrowserConfiguration {

	LoginPage loginPage;
	HomePage homePage;
	ErrorMatricsPage errorMatricsPage;

	public ErrormatricsTestcasesDemand() {
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test(priority=2)
	public void errorMatricsopen() {
		errorMatricsPage.ErrorMatricsDemand();
		errorMatricsPage.deleteFile();
	}
}
