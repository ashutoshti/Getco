package com.qa.getco.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.getco.Browser.BrowserConfiguration;

public class HomePage extends BrowserConfiguration{

	public HomePage() {
	PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//a[@id='Dashboard']//strong[contains(text(),'Dashboard')]")
	WebElement Dashboard;
	
	@FindBy(xpath="//ul[@class='collapse in']//a[contains(text(),'Weather Forecast')]")
	WebElement WeatherForecast;
	
	@FindBy(xpath="//ul[@class='collapse in']//a[contains(text(),'Weather Observed')]")
	WebElement WeatherObserved;
	
	@FindBy(xpath="//*[@id='content-container']/ol/li[2]")
	WebElement pagetitle;
	
	@FindBy(xpath="//strong[contains(text(),'Schedule')]")
	WebElement Schedule;
	
	@FindBy(xpath="//a[contains(text(),'Error Matrices')]")
	WebElement ErrorMatrices;
	
	
	public String getTitle() {
		return pagetitle.getText();
	}
	
	public WeatherObserved openWeatherObserved() {
		Dashboard.click();
		WeatherObserved.click();
		return new WeatherObserved();
	}
	
	public WeatherForecast openWeatherForecast() {
		Dashboard.click();
		WeatherForecast.click();
		return new WeatherForecast();
	}
	
	public ErrorMatricsPage openErrorMatrics() {
		JavascriptExecutor jse=(JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", Schedule);
		Schedule.click();
		ErrorMatrices.click();
		return new ErrorMatricsPage();
	}
}
