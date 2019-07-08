package com.qa.getco.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.getco.Browser.BrowserConfiguration;
import com.qa.getco.pages.ForecastedSchedulePage;
import com.qa.getco.pages.HomePage;
import com.qa.getco.pages.LoginPage;

public class ForecastedScheduleTestcase extends BrowserConfiguration {

	LoginPage loginPage;
	HomePage homePage;
	ForecastedSchedulePage forecastedSchedulePage;

	

	public ForecastedScheduleTestcase() {
		super();
	}

	@BeforeTest
	public void openbrowser() {
		browserTearUp();
		loginPage = new LoginPage();
		homePage = new HomePage();
		homePage = loginPage.login(properties.getProperty("emailid"), properties.getProperty("password"));
		forecastedSchedulePage=homePage.openForecastedSchedule();
		forecastedSchedulePage.selectFilterButton();
		
		
		 
	}
	
	@Test
	public void TestForecastedSchedule() throws Exception {
		forecastedSchedulePage.selectUtility();
		forecastedSchedulePage.selectScheduleTodayDate();
		forecastedSchedulePage.clickGoButton();
		forecastedSchedulePage.downloadExcelFile();
		forecastedSchedulePage.verifyUIandAPIData();
		forecastedSchedulePage.deleteFile();
		
	}
}
