package com.qa.getco.pages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.qa.getco.Browser.BrowserConfiguration;
import com.qa.getco.testcases.POSTAPITest;

public class ForecastedSchedulePage extends BrowserConfiguration {

	static Connection mysqlConn = null;
	static PreparedStatement mysqlPrepare = null;
	String getQueryStatement;

	public ForecastedSchedulePage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@class='btn btn-primary btn-icon icon-sm fa fa-filter add-tooltip pull-left filterBtn']")
	WebElement filterButton;

	@FindBy(xpath = "//span[contains(text(),'Select Utility')]")
	WebElement selectUtility;

	@FindBy(xpath = "//div[@class='btn-group bootstrap-select utility form-control open']//input[@class='form-control']")
	WebElement inputUtilityTextBox;

	@FindBy(linkText = "GUJARAT_STATE")
	WebElement selectUtilityName;

	@FindBy(xpath = "//span[contains(text(),'Select Schedule Date')]")
	WebElement selectScheduleDate;

	@FindBy(xpath = "//span[contains(text(),'Today')]")
	WebElement selectToday;

	@FindBy(xpath = "//span[contains(text(),'Yesterday')]")
	WebElement selectYesterday;

	@FindBy(xpath = "//span[contains(text(),'Tomorrow')]")
	WebElement selectTomorrow;

	@FindBy(xpath = "//button[@class='btn btn-primary goBtn']")
	WebElement goButton;

	@FindBy(xpath = "//*[@id='page-content']/div/div[2]/div[2]/div[2]/div/div[1]/div/i[2]")
	WebElement downloadWindForecastExcelFile;

	@FindBy(xpath = "//*[@id='page-content']/div/div[2]/div[2]/div[3]/div/div[1]/div/i[2]")
	WebElement downloadSolarForecastExcelFile;

	public void selectFilterButton() {
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}
		filterButton.click();
	}

	public void selectUtility() {
		selectUtility.click();
		selectUtilityName.click();
	}

	public void selectScheduleTodayDate() {
		selectScheduleDate.click();
		selectToday.click();
	}

	public void selectScheduleYesterdayDate() {
		selectScheduleDate.click();
		selectYesterday.click();
	}

	public void selectScheduleTomorrowDate() {
		selectScheduleDate.click();
		selectTomorrow.click();
	}

	public void clickGoButton() {
		goButton.click();
	}

	public void downloadExcelFile() {
		downloadWindForecastExcelFile.click();
		downloadSolarForecastExcelFile.click();
	}

	public void verifyUIandAPIData() {
		POSTAPITest pat=new POSTAPITest();
		pat.setUp();
		try {
			pat.postApiTest();
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteFile() {
		String home = System.getProperty("user.home");
		File fileSolar = new File(home+"\\Downloads\\Solar_Forecast.csv"); 
		File fileWind = new File(home+"\\Downloads\\Wind_Forecast.csv");
		if (fileSolar.exists()&&fileWind.exists()) {
			fileSolar.deleteOnExit();
			fileWind.deleteOnExit();
			System.out.println("=====> File Deleted<=====");
		} else {
			System.out.println("=====> File NOT Found<=====");
		}
	}
}
