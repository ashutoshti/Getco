package com.qa.getco.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.getco.Browser.BrowserConfiguration;
import com.qa.getco.pages.LoginPage;

public class LoginPageTestcase extends BrowserConfiguration {

	LoginPage loginpage;
	
	public LoginPageTestcase() {
		super();
	}
	
	@BeforeTest
	public void openbrowser() {
		browserTearUp();
		loginpage=new LoginPage();
	}
	
	@Test(priority = 1)
	public void verifyTitle() {
		String title=loginpage.verifyTitle();
		Assert.assertEquals(title, "50 Hertz Limited");
	}
	
	@Test(priority = 2)
	public void applicationStarts() {
		loginpage.login(properties.getProperty("emailid"), properties.getProperty("password"));
	}
}
