package com.qa.getco.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.getco.Browser.BrowserConfiguration;
import com.qa.getco.pages.HomePage;
import com.qa.getco.pages.LoginPage;
import com.qa.getco.pages.WeatherForecast;
import com.qa.getco.pages.WeatherObserved;

public class HomePageTestcases extends BrowserConfiguration {

	LoginPage loginPage;
	HomePage homePage;

	public HomePageTestcases() {
		super();
	}

	@BeforeTest
	public void openbrowser() {
		browserTearUp();
		loginPage = new LoginPage();
		homePage = new HomePage();
		homePage = loginPage.login(properties.getProperty("emailid"), properties.getProperty("password"));

	}

	@Test(priority=2,enabled=false)
	public WeatherObserved TestWeatherObserved() {
		homePage.openWeatherObserved();
		return new WeatherObserved();
	}

	@Test(priority=1,enabled=false)
	public WeatherForecast TestWeatherForecast() {
		homePage.openWeatherForecast();
		return new WeatherForecast();
	}

	@Test(enabled=false)
	public void TestErrorMatrices() {
		homePage.openErrorMatrics();
	}
	
	@Test
	public void TestForecastedSchedule() {
		homePage.openForecastedSchedule();
	}
}
