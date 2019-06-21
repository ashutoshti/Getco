package com.qa.getco.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.getco.Browser.BrowserConfiguration;
import com.qa.getco.pages.HomePage;
import com.qa.getco.pages.LoginPage;
import com.qa.getco.pages.WeatherForecast;

public class WeatherForecastTestcases extends BrowserConfiguration {

	LoginPage loginPage;
	HomePage homePage;
	WeatherForecast weatherforecast;

	public WeatherForecastTestcases() {
		super();
	}

	@BeforeTest
	public void openbrowser() {
		browserTearUp();
		loginPage = new LoginPage();
		homePage = new HomePage();
		weatherforecast=new WeatherForecast();
		homePage = loginPage.login(properties.getProperty("emailid"), properties.getProperty("password"));
		weatherforecast=homePage.openWeatherForecast();
	}

	@Test
	public void openWeatherForecast() {
		weatherforecast.selectUtility(properties.getProperty("utilityName"));
	}
}
