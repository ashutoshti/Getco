package com.qa.getco.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.getco.Browser.BrowserConfiguration;

public class LoginPage extends BrowserConfiguration{
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "adminEmail")
	WebElement uiemailid;
	
	@FindBy(id = "adminPass")
	WebElement uipassword;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginButton;
	
	public HomePage login(String username, String password) {
		uiemailid.sendKeys(username);
		uipassword.sendKeys(password);
		loginButton.click();
		return new HomePage();
	}
	
	public String verifyTitle() {
		return driver.getTitle();
		
	}

}
