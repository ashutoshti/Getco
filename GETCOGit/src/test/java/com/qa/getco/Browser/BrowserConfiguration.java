package com.qa.getco.Browser;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterSuite;

import com.qa.getco.utility.TestUtil;
import com.qa.getco.utility.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserConfiguration {

	public static WebDriver driver;
	public static Properties properties;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	public BrowserConfiguration() {
		properties = new Properties();
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
					+ "/src/test/java/com/qa/getco/configuration/config.properties");
			properties.load(fis);
		} catch (Exception e) {
			System.out.println("File Not Found");
		}
	}

	public void browserTearUp() {
		/*
		 * ChromeOptions options=new ChromeOptions(); options.addArguments("headless");
		 * options.addArguments("window-size=1200x600");
		 * driver=new ChromeDriver(options);
		 */
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		//Trace Driver Presence
		//e_driver = new EventFiringWebDriver(driver); 
		//eventListener = new WebEventListener(); 
		//e_driver.register(eventListener); 
		//driver = e_driver;
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(properties.getProperty("url"));
	}

	@AfterSuite
	public void browserTearDown() {
		driver.quit();
	}
}
