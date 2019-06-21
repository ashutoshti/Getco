package com.qa.getco.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.getco.Browser.BrowserConfiguration;

public class WeatherForecast extends BrowserConfiguration {

	public WeatherForecast() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@id='page-content']/div/div[1]/div/button")
	WebElement selectFilterButton;
	
	@FindBy(xpath="//*[@id='weatherSourceList']/div[1]/div/div/div/div/input")
	WebElement inputTextBox;
	
	@FindBy(xpath="//*[@id='weatherSourceList']/div[1]/div/div/button")
	WebElement selectUtility;
	
	@FindBy(xpath="//*[@id='weatherSourceList']/div[1]/div/div/div/ul/li[3]/a/span[1]")
	WebElement selectUtilityName;
	
	@FindBy(xpath="//span[contains(text(),'Select Location Type')]")
	WebElement selectlocationType;
	
	@FindBy(xpath="//*[@id='weatherSourceList']/div[2]/div/div/div/div/input")
	WebElement selectlocationTypeInput;
	
	@FindBy(xpath="//span[contains(text(),'District')]")
	WebElement selectType;
	
	@FindBy(xpath="//*[@id='weatherSourceList']/div[3]/button")
	WebElement goButton;
	
	@FindBy(xpath="//span[contains(text(),'Search By Name')]")
	WebElement selectname;
	
	@FindBy(xpath="//*[@id='weatherSourceList']/div[4]/div/div/div/div/input")
	WebElement selectnameinput;
	
	@FindBy(xpath="//*[@id='weatherSourceList']/div[4]/div/div/div/ul/li[1]/a/span[1]")
	WebElement inputname;
	
		
	
	public void selectUtility(String utilityName) {
		selectFilterButton.click();
		selectUtility.click();
		inputTextBox.sendKeys(utilityName);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		selectUtilityName.click();
		
		//Select Location Type
		
		selectlocationType.click();
		selectType.click();
		
		//Go Button
		goButton.click();
		
		//Select Name
		selectname.click();
		selectnameinput.sendKeys("Ahmedabad");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		inputname.click();
		
	}
	
}
