package com.qa.getco.pages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.getco.Browser.BrowserConfiguration;

public class ErrorMatricsPage extends BrowserConfiguration {

	public ErrorMatricsPage() {
		PageFactory.initElements(driver, this);
	}

	WebDriverWait wait = new WebDriverWait(driver, 30);

	@FindBy(xpath = "//button[contains(@class,'btn btn-primary btn-icon icon-sm fa fa-filter add-tooltip pull-left filterBtn')]")
	WebElement filterButton;

	@FindBy(xpath = "//div[contains(@class,'btn-group bootstrap-select form-control utility')]//span[contains(@class,'filter-option pull-left')]")
	WebElement selectUtilityDropDown;

	@FindBy(xpath = "//div[contains(@class,'btn-group bootstrap-select form-control utility open')]//input[contains(@class,'form-control')]")
	WebElement inputUtilityTextBox;

	@FindBy(linkText = "GUJARAT_STATE")
	WebElement selectUtilityName;

	@FindBy(xpath = "//span[contains(text(),'Error Matrices')]")
	WebElement pageName;

	@FindBy(xpath = "//input[@id='dateFrom']")
	WebElement selectDate;

	@FindBy(xpath = "//li[contains(text(),'Today')]")
	WebElement selectTodayDate;

	@FindBy(xpath = "//li[contains(text(),'Yesterday')]")
	WebElement selectYesterdayDate;

	@FindBy(xpath = "//*[@id='forecastReportFilterForm']/div/div[3]/div/div/button/span[1]")
	WebElement selectForecastType;

	@FindBy(xpath = "//span[contains(@class,'text')][contains(text(),'Demand Forecast')]")
	WebElement selectType;

	@FindBy(xpath = "//span[@class='text'][contains(text(),'Wind Forecast')]")
	WebElement selectTypeWind;

	@FindBy(xpath = "//span[@class='text'][contains(text(),'Solar Forecast')]")
	WebElement selectTypeSolar;

	@FindBy(xpath = "//span[contains(text(),'Select Meter Type')]")
	WebElement clickSelectMeterType;

	@FindBy(xpath = "//div[@class='btn-group bootstrap-select form-control meterType open']//input[@class='form-control']")
	WebElement selectInputMeterText;

	@FindBy(xpath = "//*[@id='forecastReportFilterForm']/div/div[4]/div/div/div/ul/li[1]/a/span[1]")
	WebElement selectFirstMeterDropDownValue;

	@FindBy(xpath = "//span[contains(text(),'Select Graph Type')]")
	WebElement clickGraphType;

	@FindBy(xpath = "//span[contains(text(),'NMAE & NMAPE')]")
	WebElement selectFirstGraphDropDownValue;

	@FindBy(xpath = "//span[contains(text(),'NRMSE & NPRMSE')]")
	WebElement selectSecondGraphDropDownValue;

	@FindBy(xpath = "//*[@id='forecastReportFilterForm']/div/div[5]/div/div/button/span[1]")
	WebElement finalSelectGraphType;

	@FindBy(xpath = "//button[@id='forecastSubmit']")
	WebElement goButton;

	@FindBy(xpath = "//*[@id='page-content']/div[2]/div[1]/div/div[1]/div/i[2]")
	WebElement csvFileDownload;

	@FindBy(xpath = "//*[@id='page-content']/div[2]/div[2]/div/div[1]/div/i[2]")
	WebElement csvFileDownloadNAME;

	@FindBy(xpath = "//*[@id='page-content']/div[2]/div[3]/div/div[1]/div/i[2]")
	WebElement csvFileDownloadNRMSE;

	public String verifyPage() {
		return pageName.getText();
	}

	public void ErrorMatricsDemand() {

		/*** Select The Utility ***/
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		filterButton.click();
		selectUtilityDropDown.click();
		// inputUtilityTextBox.sendKeys(properties.getProperty("utilityName"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectUtilityName.click();

		/*** Select The Date ***/
		selectDate.click();
		// selectTodayDate.click();
		selectYesterdayDate.click();

		/*** Select The Forecast Type ***/
		selectForecastType.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		selectType.click();

		/*** Select The Meter Type ***/
		clickSelectMeterType.click();
		selectInputMeterText.click();
		selectInputMeterText.sendKeys(properties.getProperty("meterType"));
		selectFirstMeterDropDownValue.click();

		/*** Select The Graph Type ***/
		clickGraphType.click();
		selectFirstGraphDropDownValue.click();
		selectSecondGraphDropDownValue.click();
		finalSelectGraphType.click();

		/*** Go Button ***/

		goButton.click();

		try {
			Thread.sleep(20000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		csvFileDownload.click();
		csvFileDownloadNAME.click();
		csvFileDownloadNRMSE.click();
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		NMAEandNMAPECalculationDemandForecastDayAheadSchedule();
		NMAEandNMAPECalculationDemandForecastFinalSchedule();
	}

	public void NMAEandNMAPECalculationDemandForecastDayAheadSchedule() {
		double sumofNMAE = 0;
		double noofrows = 0;
		DecimalFormat f = new DecimalFormat("0.###");
		String csvFile = "C:\\Users\\Manikaran\\Downloads\\Power_Forecast_Vs_Actual_Consumption.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		double valueofDayAheadSchedule = 0;
		double valueofLoad = 0;
		double sumofLoad = 0;
		double sumofNRMSE = 0;
		double val = 2;

		int serialno = 1;

		try {
			br = new BufferedReader(new FileReader(csvFile));
			br.readLine();
			br.readLine();
			while ((line = br.readLine()) != null) {

				String[] data = line.split(cvsSplitBy);
				String data1content = data[1].replaceAll("^\"|\"$", "");
				String data2content = data[2].replaceAll("^\"|\"$", "");
				System.out.println("Getting Data " + data1content + " " + data2content);
				
				if (data1content.equalsIgnoreCase("null") || data2content.equalsIgnoreCase("null")) {
					br.readLine();
				} 
				else {
					valueofLoad = Double.parseDouble(data1content);
					valueofDayAheadSchedule = Double.parseDouble(data2content);
					sumofLoad += valueofLoad;
					noofrows++;
					System.out.println("Value of NMAE  Per Block: " + serialno + " "+ f.format(Math.abs(valueofLoad - valueofDayAheadSchedule) / (sumofLoad / noofrows)));
					sumofNMAE += Double.parseDouble(f.format(Math.abs(valueofLoad - valueofDayAheadSchedule) / (sumofLoad / noofrows)));
					System.out.println("Value of NMAE  Per Block: " + serialno + " "+ f.format(Math.sqrt((1 / 96) * Math.pow((valueofLoad - valueofDayAheadSchedule), val))/ (sumofLoad / noofrows)));
					sumofNRMSE += Double.parseDouble(f.format(Math.sqrt((1 / 96) * Math.pow((valueofLoad - valueofDayAheadSchedule), val))/ (sumofLoad / noofrows)));
					serialno++;
					System.out.println("<=====****=====>");
					
					/***Load NMAE & NMAPE CSV File And Verify***/
					
					String csvNMAEFile ="C:\\Users\\Manikaran\\Downloads\\NMAE_&_NMAPE.csv";
					BufferedReader nmaebr = null; 
					String nmaeline = ""; 
					String nmaecvsSplitBy = ",";
					double valueofnmae = 0;
					
					String csvNRMSEFile ="C:\\Users\\Manikaran\\Downloads\\NMAE_&_NMAPE.csv";
					BufferedReader NRMSEbr = null; 
					String NRMSEline = ""; 
					String NRMSEcvsSplitBy = ",";
					double valueofNRMSE = 0;
					
					try {
						nmaebr = new BufferedReader(new FileReader(csvNMAEFile));
						nmaebr.readLine();
						nmaebr.readLine();
						
						NRMSEbr = new BufferedReader(new FileReader(csvNRMSEFile));
						NRMSEbr.readLine();
						NRMSEbr.readLine();						
									
						while ((nmaeline=nmaebr.readLine())!=null && ((NRMSEline=NRMSEbr.readLine())!=null)) {
							String[] nmaedata=nmaeline.split(nmaecvsSplitBy);
							int len=nmaedata[1].length();
							String stringvalueofnmae=nmaedata[1].substring(1, len-1);
							valueofnmae=Double.parseDouble(stringvalueofnmae);
							
							String[] nrmsedata=NRMSEline.split(NRMSEcvsSplitBy);
							int lenNRMSE=nrmsedata[1].length();
							String stringvalueofNRMSE=nmaedata[1].substring(1, lenNRMSE-1);
							valueofNRMSE=Double.parseDouble(stringvalueofNRMSE);
							
							
									
							if (valueofnmae==(Double.parseDouble(f.format(sumofNMAE/noofrows)))&&valueofNRMSE==(Double.parseDouble(f.format(sumofNRMSE / noofrows)))) {
								System.out.println("Value Verified and Value of NMAE and NRMSE "+f.format((sumofNMAE/noofrows))+" "+f.format((sumofNRMSE / noofrows)));
							} else {
								System.out.println("Value NOT Verified and Value of NMAE and NRMSE "+f.format((sumofNMAE/noofrows))+" "+f.format((sumofNRMSE / noofrows)));
							}
						}
						
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
			}
			System.out.println("Average of Load: " + f.format(sumofLoad / noofrows));
			System.out.println("Average of NMAE:" + f.format(sumofNMAE / noofrows));
			System.out.println("Average of NRMSE:" +f.format(sumofNRMSE / noofrows));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void NMAEandNMAPECalculationDemandForecastFinalSchedule() {
		double sumofNMAE = 0;
		double noofrows = 0;
		DecimalFormat f = new DecimalFormat("0.###");
		String csvFile = "C:\\Users\\Manikaran\\Downloads\\Power_Forecast_Vs_Actual_Consumption.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		double valueofFinalSchedule = 0;
		double valueofLoad = 0;
		double sumofLoad = 0;
		double sumofNRMSE = 0;
		double val = 2;

		int serialno = 1;

		try {
			br = new BufferedReader(new FileReader(csvFile));
			br.readLine();
			br.readLine();
			while ((line = br.readLine()) != null) {

				String[] data = line.split(cvsSplitBy);
				String data1content = data[1].replaceAll("^\"|\"$", "");
				String data3content = data[2].replaceAll("^\"|\"$", "");
				System.out.println("Getting Data " + data1content + " " + data3content);
				
				if (data1content.equalsIgnoreCase("null") || data3content.equalsIgnoreCase("null")) {
					br.readLine();
				} 
				else {
					valueofLoad = Double.parseDouble(data1content);
					valueofFinalSchedule = Double.parseDouble(data3content);
					sumofLoad += valueofLoad;
					noofrows++;
					System.out.println("Value of NMAE  Per Block: " + serialno + " "+ f.format(Math.abs(valueofLoad - valueofFinalSchedule) / (sumofLoad / noofrows)));
					sumofNMAE += Double.parseDouble(f.format(Math.abs(valueofLoad - valueofFinalSchedule) / (sumofLoad / noofrows)));
					System.out.println("Value of NMAE  Per Block: " + serialno + " "+ f.format(Math.sqrt((1 / 96) * Math.pow((valueofLoad - valueofFinalSchedule), val))/ (sumofLoad / noofrows)));
					sumofNRMSE += Double.parseDouble(f.format(Math.sqrt((1 / 96) * Math.pow((valueofLoad - valueofFinalSchedule), val))/ (sumofLoad / noofrows)));
					serialno++;
					System.out.println("<=====****=====>");
					
					/***Load NMAE & NMAPE CSV File And Verify***/
					
					String csvNMAEFile ="C:\\Users\\Manikaran\\Downloads\\NMAE_&_NMAPE.csv";
					BufferedReader nmaebr = null; 
					String nmaeline = ""; 
					String nmaecvsSplitBy = ",";
					double valueofnmae = 0;
					
					String csvNRMSEFile ="C:\\Users\\Manikaran\\Downloads\\NMAE_&_NMAPE.csv";
					BufferedReader NRMSEbr = null; 
					String NRMSEline = ""; 
					String NRMSEcvsSplitBy = ",";
					double valueofNRMSE = 0;
					
					try {
						nmaebr = new BufferedReader(new FileReader(csvNMAEFile));
						nmaebr.readLine();
						nmaebr.readLine();
						
						NRMSEbr = new BufferedReader(new FileReader(csvNRMSEFile));
						NRMSEbr.readLine();
						NRMSEbr.readLine();						
									
						while ((nmaeline=nmaebr.readLine())!=null && ((NRMSEline=NRMSEbr.readLine())!=null)) {
							String[] nmaedata=nmaeline.split(nmaecvsSplitBy);
							int len=nmaedata[1].length();
							String stringvalueofnmae=nmaedata[1].substring(1, len-1);
							valueofnmae=Double.parseDouble(stringvalueofnmae);
							
							String[] nrmsedata=NRMSEline.split(NRMSEcvsSplitBy);
							int lenNRMSE=nrmsedata[1].length();
							String stringvalueofNRMSE=nmaedata[1].substring(1, lenNRMSE-1);
							valueofNRMSE=Double.parseDouble(stringvalueofNRMSE);
							
							
									
							if (valueofnmae==(Double.parseDouble(f.format(sumofNMAE/noofrows)))&&valueofNRMSE==(Double.parseDouble(f.format(sumofNRMSE / noofrows)))) {
								System.out.println("Value Verified and Value of NMAE and NRMSE "+f.format((sumofNMAE/noofrows))+" "+f.format((sumofNRMSE / noofrows)));
							} else {
								System.out.println("Value NOT Verified and Value of NMAE and NRMSE "+f.format((sumofNMAE/noofrows))+" "+f.format((sumofNRMSE / noofrows)));
							}
						}
						
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
			}
			System.out.println("Average of Load: " + f.format(sumofLoad / noofrows));
			System.out.println("Average of NMAE:" + f.format(sumofNMAE / noofrows));
			System.out.println("Average of NRMSE:" +f.format(sumofNRMSE / noofrows));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void ErrorMatricsWind() {

		/*** Select The Utility For Wind ***/
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		filterButton.click();
		selectUtilityDropDown.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectUtilityName.click();

		/*** Select The Date ***/
		selectDate.click();
		// selectTodayDate.click();
		selectYesterdayDate.click();

		/*** Select The Forecast Type ***/
		selectForecastType.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		selectTypeWind.click();

		/*** Select The Meter Type ***/
		clickSelectMeterType.click();
		selectInputMeterText.click();
		selectInputMeterText.sendKeys(properties.getProperty("meterType"));
		selectFirstMeterDropDownValue.click();

		/*** Select The Graph Type ***/
		clickGraphType.click();
		selectFirstGraphDropDownValue.click();
		selectSecondGraphDropDownValue.click();
		finalSelectGraphType.click();

		/*** Go Button ***/

		goButton.click();

		try {
			Thread.sleep(20000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		csvFileDownload.click();
		csvFileDownloadNAME.click();
		csvFileDownloadNRMSE.click();
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		NMAEandNMAPECalculationWindDayAhead();
		NMAEandNMAPECalculationWindFinal();
	}

	public void NMAEandNMAPECalculationWindDayAhead() {

		double installedcapacity = 5603.15;
		double sumofNMAE = 0;
		double noofrows = 0;
		DecimalFormat f = new DecimalFormat("0.##");
		String csvFileWind = "C:\\Users\\Manikaran\\Downloads\\Power_Forecast_Vs_Actual_Consumption.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		double valueofDayAheadSchedule = 0;
		double valueofLoad = 0;
		double sumofLoad = 0;
		double sumofNRMSE = 0;
		Connection mysqlConn = null;
		PreparedStatement mysqlPrepare = null;
		String getQueryStatement;
		double valueofnmae = 0;
		double valueofNRMSE = 0;

		int serialno = 1;

		try {
			br = new BufferedReader(new FileReader(csvFileWind));
			br.readLine();
			br.readLine();
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://101.53.139.234/epm";
			String dbUsername = "cluster_qa";
			String dbPwd = "admin@1212";
			mysqlConn = DriverManager.getConnection(dbURL, dbUsername, dbPwd);
			getQueryStatement = "select GENCO_PARAMETER_VALUE from generation_parameters where GENERATION_UID=697 and GENCO_PARAMETER_TYPE_UID=13 order by TO_DATE desc limit 1";
			mysqlPrepare = mysqlConn.prepareStatement(getQueryStatement);
			ResultSet rs = mysqlPrepare.executeQuery();
			while (rs.next()) {
				double dbValue = rs.getDouble(1);
				System.out.println("DB value is " + dbValue);

			}

			while ((line = br.readLine()) != null) {

				String[] data = line.split(cvsSplitBy);
				String data1content = data[1].replaceAll("^\"|\"$", "");
				String data2content = data[2].replaceAll("^\"|\"$", "");
				System.out.println("Getting Data " + data1content + " " + data2content);

				if (data1content.equalsIgnoreCase("null") || data2content.equalsIgnoreCase("null")) {
					br.readLine();
				} else {
					valueofLoad = Double.parseDouble(data1content);
					valueofDayAheadSchedule = Double.parseDouble(data2content);
					sumofLoad += valueofLoad;
					noofrows++;
					System.out.println("Value of ABS  Per Block: " + serialno + " "
							+ f.format(Math.abs(valueofLoad - valueofDayAheadSchedule)));
					sumofNMAE += Double.parseDouble(f.format(Math.abs(valueofLoad - valueofDayAheadSchedule)));
					serialno++;

					/*** Load NMAE & NMAPE CSV File And Verify ***/

					String csvNMAEFile = "C:\\Users\\Manikaran\\Downloads\\NMAE_&_NMAPE.csv";
					BufferedReader nmaebr = null;
					String nmaeline = "";
					String nmaecvsSplitBy = ",";

					String csvNRMSEFile = "C:\\Users\\Manikaran\\Downloads\\NRMSE_&_NPRMSE.csv";
					BufferedReader NRMSEbr = null;
					String NRMSEline = "";
					String NRMSEcvsSplitBy = ",";

					try {
						nmaebr = new BufferedReader(new FileReader(csvNMAEFile));
						nmaebr.readLine();
						nmaebr.readLine();

						NRMSEbr = new BufferedReader(new FileReader(csvNRMSEFile));
						NRMSEbr.readLine();
						NRMSEbr.readLine();

						while ((nmaeline = nmaebr.readLine()) != null && ((NRMSEline = NRMSEbr.readLine()) != null)) {
							String[] nmaedata = nmaeline.split(nmaecvsSplitBy);
							int len = nmaedata[1].length();
							String stringvalueofnmae = nmaedata[1].substring(1, len - 1);
							valueofnmae = Double.parseDouble(stringvalueofnmae);
							System.out.println("Value of wind nmae " + valueofnmae);

							String[] nrmsedata = NRMSEline.split(NRMSEcvsSplitBy);
							int lenNRMSE = nrmsedata[1].length();
							String stringvalueofNRMSE = nrmsedata[1].substring(1, lenNRMSE - 1);
							valueofNRMSE = Double.parseDouble(stringvalueofNRMSE);
							System.out.println("Value of wind nrmse " + valueofNRMSE);
						}
					} catch (Exception e) {
						e.printStackTrace();

					}

				}
			}
			if (valueofnmae == (Double.parseDouble(f.format((sumofNMAE / noofrows) / installedcapacity)))) {
				System.out.println("Value Verified and Value of NMAE and NRMSE "
						+ (f.format((sumofNMAE / noofrows) / installedcapacity)) + " "
						+ f.format((sumofNRMSE / noofrows)));
			} else {
				System.out.println("Value NOT Verified and Value of NMAE and NRMSE "
						+ (f.format((sumofNMAE / noofrows) / installedcapacity)) + " "
						+ f.format((sumofNRMSE / noofrows)));
			}
			System.out.println("<=====****=====>");
			System.out.println("Average of Load: " + f.format(sumofLoad / noofrows));
			System.out.println("Average of NMAE:" + (f.format((sumofNMAE / noofrows) / installedcapacity)));
			System.out.println("Average of NRMSE:" + f.format(sumofNRMSE / noofrows));

		} catch (Exception e) {
			e.printStackTrace();
		}

		File file1 = new File("C:\\Users\\Manikaran\\Downloads\\Power_Forecast_Vs_Actual_Consumption.csv");
		File file2 = new File("C:\\Users\\Manikaran\\Downloads\\NMAE_&_NMAPE.csv");
		File file3 = new File("C:\\Users\\Manikaran\\Downloads\\NRMSE_&_NPRMSE.csv");
		try {
			if (!file1.exists() || !file2.exists() || !file3.exists()) {
				System.out.println("File Not Found");
			} else {
				file1.delete();
				file2.delete();
				file3.delete();
				System.out.println("File Deleted");
			}
		} catch (Exception e) {
			System.out.println("Why im Here");
		}
	}

	public void NMAEandNMAPECalculationWindFinal() {

		double installedcapacity = 5603.15;
		double sumofNMAE = 0;
		double noofrows = 0;
		DecimalFormat f = new DecimalFormat("0.##");
		String csvFileWind = "C:\\Users\\Manikaran\\Downloads\\Power_Forecast_Vs_Actual_Consumption.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		double valueofDayAheadSchedule = 0;
		double valueofLoad = 0;
		double sumofLoad = 0;
		double sumofNRMSE = 0;
		Connection mysqlConn = null;
		PreparedStatement mysqlPrepare = null;
		String getQueryStatement;
		double valueofnmae = 0;
		double valueofNRMSE = 0;

		int serialno = 1;

		try {
			br = new BufferedReader(new FileReader(csvFileWind));
			br.readLine();
			br.readLine();
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://101.53.139.234/epm";
			String dbUsername = "cluster_qa";
			String dbPwd = "admin@1212";
			mysqlConn = DriverManager.getConnection(dbURL, dbUsername, dbPwd);
			getQueryStatement = "select GENCO_PARAMETER_VALUE from generation_parameters where GENERATION_UID=697 and GENCO_PARAMETER_TYPE_UID=13 order by TO_DATE desc limit 1";
			mysqlPrepare = mysqlConn.prepareStatement(getQueryStatement);
			ResultSet rs = mysqlPrepare.executeQuery();
			while (rs.next()) {
				double dbValue = rs.getDouble(1);
				System.out.println("DB value is " + dbValue);

			}

			while ((line = br.readLine()) != null) {

				String[] data = line.split(cvsSplitBy);
				String data1content = data[1].replaceAll("^\"|\"$", "");
				String data3content = data[3].replaceAll("^\"|\"$", "");
				System.out.println("Getting Data " + data1content + " " + data3content);

				if (data1content.equalsIgnoreCase("null") || data3content.equalsIgnoreCase("null")) {
					br.readLine();
				} else {
					valueofLoad = Double.parseDouble(data1content);
					valueofDayAheadSchedule = Double.parseDouble(data3content);
					sumofLoad += valueofLoad;
					noofrows++;
					System.out.println("Value of ABS  Per Block: " + serialno + " "
							+ f.format(Math.abs(valueofLoad - valueofDayAheadSchedule)));
					sumofNMAE += Double.parseDouble(f.format(Math.abs(valueofLoad - valueofDayAheadSchedule)));
					serialno++;

					/*** Load NMAE & NMAPE CSV File And Verify ***/

					String csvNMAEFile = "C:\\Users\\Manikaran\\Downloads\\NMAE_&_NMAPE.csv";
					BufferedReader nmaebr = null;
					String nmaeline = "";
					String nmaecvsSplitBy = ",";

					String csvNRMSEFile = "C:\\Users\\Manikaran\\Downloads\\NRMSE_&_NPRMSE.csv";
					BufferedReader NRMSEbr = null;
					String NRMSEline = "";
					String NRMSEcvsSplitBy = ",";

					try {
						nmaebr = new BufferedReader(new FileReader(csvNMAEFile));
						nmaebr.readLine();
						nmaebr.readLine();

						NRMSEbr = new BufferedReader(new FileReader(csvNRMSEFile));
						NRMSEbr.readLine();
						NRMSEbr.readLine();

						while ((nmaeline = nmaebr.readLine()) != null && ((NRMSEline = NRMSEbr.readLine()) != null)) {
							String[] nmaedata = nmaeline.split(nmaecvsSplitBy);
							int len = nmaedata[1].length();
							String stringvalueofnmae = nmaedata[1].substring(1, len - 1);
							valueofnmae = Double.parseDouble(stringvalueofnmae);
							System.out.println("Value of wind nmae " + valueofnmae);

							String[] nrmsedata = NRMSEline.split(NRMSEcvsSplitBy);
							int lenNRMSE = nrmsedata[1].length();
							String stringvalueofNRMSE = nrmsedata[1].substring(1, lenNRMSE - 1);
							valueofNRMSE = Double.parseDouble(stringvalueofNRMSE);
							System.out.println("Value of wind nrmse " + valueofNRMSE);
						}
					} catch (Exception e) {
						e.printStackTrace();

					}

				}
			}
			if (valueofnmae == (Double.parseDouble(f.format((sumofNMAE / noofrows) / installedcapacity)))) {
				System.out.println("Value Verified and Value of NMAE and NRMSE "
						+ (f.format((sumofNMAE / noofrows) / installedcapacity)) + " "
						+ f.format((sumofNRMSE / noofrows)));
			} else {
				System.out.println("Value NOT Verified and Value of NMAE and NRMSE "
						+ (f.format((sumofNMAE / noofrows) / installedcapacity)) + " "
						+ f.format((sumofNRMSE / noofrows)));
			}
			System.out.println("<=====****=====>");
			System.out.println("Average of Load: " + f.format(sumofLoad / noofrows));
			System.out.println("Average of NMAE:" + (f.format((sumofNMAE / noofrows) / installedcapacity)));
			System.out.println("Average of NRMSE:" + f.format(sumofNRMSE / noofrows));

		} catch (Exception e) {
			e.printStackTrace();
		}

		File file1 = new File("C:\\Users\\Manikaran\\Downloads\\Power_Forecast_Vs_Actual_Consumption.csv");
		File file2 = new File("C:\\Users\\Manikaran\\Downloads\\NMAE_&_NMAPE.csv");
		File file3 = new File("C:\\Users\\Manikaran\\Downloads\\NRMSE_&_NPRMSE.csv");
		try {
			if (!file1.exists() || !file2.exists() || !file3.exists()) {
				System.out.println("File Not Found");
			} else {
				file1.delete();
				file2.delete();
				file3.delete();
				System.out.println("File Deleted");
			}
		} catch (Exception e) {
			System.out.println("Why im Here");
		}
	}

	public void ErrorMatricsSolar() {

		/*** Select The Utility For Wind ***/
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		filterButton.click();
		selectUtilityDropDown.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectUtilityName.click();

		/*** Select The Date ***/
		selectDate.click();
		// selectTodayDate.click();
		selectYesterdayDate.click();

		/*** Select The Forecast Type ***/
		selectForecastType.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		selectTypeSolar.click();

		/*** Select The Meter Type ***/
		clickSelectMeterType.click();
		selectInputMeterText.click();
		selectInputMeterText.sendKeys(properties.getProperty("meterType"));
		selectFirstMeterDropDownValue.click();

		/*** Select The Graph Type ***/
		clickGraphType.click();
		selectFirstGraphDropDownValue.click();
		selectSecondGraphDropDownValue.click();
		finalSelectGraphType.click();

		/*** Go Button ***/

		goButton.click();

		try {
			Thread.sleep(20000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		csvFileDownload.click();
		csvFileDownloadNAME.click();
		csvFileDownloadNRMSE.click();
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		NMAEandNMAPECalculationSolarDayAhead();
		NMAEandNMAPECalculationSolarFinal();
	}

	public void NMAEandNMAPECalculationSolarDayAhead() {

		double installedcapacity = 5603.15;
		double sumofNMAE = 0;
		double noofrows = 0;
		DecimalFormat f = new DecimalFormat("0.##");
		String csvFileWind = "C:\\Users\\Manikaran\\Downloads\\Power_Forecast_Vs_Actual_Consumption.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		double valueofDayAheadSchedule = 0;
		double valueofLoad = 0;
		double sumofLoad = 0;
		double sumofNRMSE = 0;
		Connection mysqlConn = null;
		PreparedStatement mysqlPrepare = null;
		String getQueryStatement;
		double valueofnmae = 0;
		double valueofNRMSE = 0;

		int serialno = 1;

		try {
			br = new BufferedReader(new FileReader(csvFileWind));
			br.readLine();
			br.readLine();
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://101.53.139.234/epm";
			String dbUsername = "cluster_qa";
			String dbPwd = "admin@1212";
			mysqlConn = DriverManager.getConnection(dbURL, dbUsername, dbPwd);
			getQueryStatement = "select GENCO_PARAMETER_VALUE from generation_parameters where GENERATION_UID=697 and GENCO_PARAMETER_TYPE_UID=13 order by TO_DATE desc limit 1";
			mysqlPrepare = mysqlConn.prepareStatement(getQueryStatement);
			ResultSet rs = mysqlPrepare.executeQuery();
			while (rs.next()) {
				double dbValue = rs.getDouble(1);
				System.out.println("DB value is " + dbValue);

			}

			while ((line = br.readLine()) != null) {

				String[] data = line.split(cvsSplitBy);
				String data1content = data[1].replaceAll("^\"|\"$", "");
				String data2content = data[2].replaceAll("^\"|\"$", "");
				System.out.println("Getting Data " + data1content + " " + data2content);

				if (data1content.equalsIgnoreCase("null") || data2content.equalsIgnoreCase("null")) {
					br.readLine();
				} else {
					valueofLoad = Double.parseDouble(data1content);
					valueofDayAheadSchedule = Double.parseDouble(data2content);
					sumofLoad += valueofLoad;
					noofrows++;
					System.out.println("Value of ABS  Per Block: " + serialno + " "
							+ f.format(Math.abs(valueofLoad - valueofDayAheadSchedule)));
					sumofNMAE += Double.parseDouble(f.format(Math.abs(valueofLoad - valueofDayAheadSchedule)));
					serialno++;

					/*** Load NMAE & NMAPE CSV File And Verify ***/

					String csvNMAEFile = "C:\\Users\\Manikaran\\Downloads\\NMAE_&_NMAPE.csv";
					BufferedReader nmaebr = null;
					String nmaeline = "";
					String nmaecvsSplitBy = ",";

					String csvNRMSEFile = "C:\\Users\\Manikaran\\Downloads\\NRMSE_&_NPRMSE.csv";
					BufferedReader NRMSEbr = null;
					String NRMSEline = "";
					String NRMSEcvsSplitBy = ",";

					try {
						nmaebr = new BufferedReader(new FileReader(csvNMAEFile));
						nmaebr.readLine();
						nmaebr.readLine();

						NRMSEbr = new BufferedReader(new FileReader(csvNRMSEFile));
						NRMSEbr.readLine();
						NRMSEbr.readLine();

						while ((nmaeline = nmaebr.readLine()) != null && ((NRMSEline = NRMSEbr.readLine()) != null)) {
							String[] nmaedata = nmaeline.split(nmaecvsSplitBy);
							int len = nmaedata[1].length();
							String stringvalueofnmae = nmaedata[1].substring(1, len - 1);
							valueofnmae = Double.parseDouble(stringvalueofnmae);
							System.out.println("Value of wind nmae " + valueofnmae);

							String[] nrmsedata = NRMSEline.split(NRMSEcvsSplitBy);
							int lenNRMSE = nrmsedata[1].length();
							String stringvalueofNRMSE = nrmsedata[1].substring(1, lenNRMSE - 1);
							valueofNRMSE = Double.parseDouble(stringvalueofNRMSE);
							System.out.println("Value of wind nrmse " + valueofNRMSE);
						}
					} catch (Exception e) {
						e.printStackTrace();

					}

				}
			}
			if (valueofnmae == (Double.parseDouble(f.format((sumofNMAE / noofrows) / installedcapacity)))) {
				System.out.println("Value Verified and Value of NMAE and NRMSE "
						+ (f.format((sumofNMAE / noofrows) / installedcapacity)) + " "
						+ f.format((sumofNRMSE / noofrows)));
			} else {
				System.out.println("Value NOT Verified and Value of NMAE and NRMSE "
						+ (f.format((sumofNMAE / noofrows) / installedcapacity)) + " "
						+ f.format((sumofNRMSE / noofrows)));
			}
			System.out.println("<=====****=====>");
			System.out.println("Average of Load: " + f.format(sumofLoad / noofrows));
			System.out.println("Average of NMAE:" + (f.format((sumofNMAE / noofrows) / installedcapacity)));
			System.out.println("Average of NRMSE:" + f.format(sumofNRMSE / noofrows));

		} catch (Exception e) {
			e.printStackTrace();
		}

		File file1 = new File("C:\\Users\\Manikaran\\Downloads\\Power_Forecast_Vs_Actual_Consumption.csv");
		File file2 = new File("C:\\Users\\Manikaran\\Downloads\\NMAE_&_NMAPE.csv");
		File file3 = new File("C:\\Users\\Manikaran\\Downloads\\NRMSE_&_NPRMSE.csv");
		try {
			if (!file1.exists() || !file2.exists() || !file3.exists()) {
				System.out.println("File Not Found");
			} else {
				file1.delete();
				file2.delete();
				file3.delete();
				System.out.println("File Deleted");
			}
		} catch (Exception e) {
			System.out.println("Why im Here");
		}
	}

	public void NMAEandNMAPECalculationSolarFinal() {

		double installedcapacity = 5603.15;
		double sumofNMAE = 0;
		double noofrows = 0;
		DecimalFormat f = new DecimalFormat("0.##");
		String csvFileWind = "C:\\Users\\Manikaran\\Downloads\\Power_Forecast_Vs_Actual_Consumption.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		double valueofDayAheadSchedule = 0;
		double valueofLoad = 0;
		double sumofLoad = 0;
		double sumofNRMSE = 0;
		Connection mysqlConn = null;
		PreparedStatement mysqlPrepare = null;
		String getQueryStatement;
		double valueofnmae = 0;
		double valueofNRMSE = 0;

		int serialno = 1;

		try {
			br = new BufferedReader(new FileReader(csvFileWind));
			br.readLine();
			br.readLine();
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://101.53.139.234/epm";
			String dbUsername = "cluster_qa";
			String dbPwd = "admin@1212";
			mysqlConn = DriverManager.getConnection(dbURL, dbUsername, dbPwd);
			getQueryStatement = "select GENCO_PARAMETER_VALUE from generation_parameters where GENERATION_UID=698 and GENCO_PARAMETER_TYPE_UID=13 order by TO_DATE desc limit 1";
			mysqlPrepare = mysqlConn.prepareStatement(getQueryStatement);
			ResultSet rs = mysqlPrepare.executeQuery();
			while (rs.next()) {
				double dbValue = rs.getDouble(1);
				System.out.println("DB value is " + dbValue);

			}

			while ((line = br.readLine()) != null) {

				String[] data = line.split(cvsSplitBy);
				String data1content = data[1].replaceAll("^\"|\"$", "");
				String data3content = data[3].replaceAll("^\"|\"$", "");
				System.out.println("Getting Data " + data1content + " " + data3content);

				if (data1content.equalsIgnoreCase("null") || data3content.equalsIgnoreCase("null")) {
					br.readLine();
				} else {
					valueofLoad = Double.parseDouble(data1content);
					valueofDayAheadSchedule = Double.parseDouble(data3content);
					sumofLoad += valueofLoad;
					noofrows++;
					System.out.println("Value of ABS  Per Block: " + serialno + " "
							+ f.format(Math.abs(valueofLoad - valueofDayAheadSchedule)));
					sumofNMAE += Double.parseDouble(f.format(Math.abs(valueofLoad - valueofDayAheadSchedule)));
					serialno++;

					/*** Load NMAE & NMAPE CSV File And Verify ***/

					String csvNMAEFile = "C:\\Users\\Manikaran\\Downloads\\NMAE_&_NMAPE.csv";
					BufferedReader nmaebr = null;
					String nmaeline = "";
					String nmaecvsSplitBy = ",";

					String csvNRMSEFile = "C:\\Users\\Manikaran\\Downloads\\NRMSE_&_NPRMSE.csv";
					BufferedReader NRMSEbr = null;
					String NRMSEline = "";
					String NRMSEcvsSplitBy = ",";

					try {
						nmaebr = new BufferedReader(new FileReader(csvNMAEFile));
						nmaebr.readLine();
						nmaebr.readLine();

						NRMSEbr = new BufferedReader(new FileReader(csvNRMSEFile));
						NRMSEbr.readLine();
						NRMSEbr.readLine();

						while ((nmaeline = nmaebr.readLine()) != null && ((NRMSEline = NRMSEbr.readLine()) != null)) {
							String[] nmaedata = nmaeline.split(nmaecvsSplitBy);
							int len = nmaedata[1].length();
							String stringvalueofnmae = nmaedata[1].substring(1, len - 1);
							valueofnmae = Double.parseDouble(stringvalueofnmae);
							System.out.println("Value of wind nmae " + valueofnmae);

							String[] nrmsedata = NRMSEline.split(NRMSEcvsSplitBy);
							int lenNRMSE = nrmsedata[1].length();
							String stringvalueofNRMSE = nrmsedata[1].substring(1, lenNRMSE - 1);
							valueofNRMSE = Double.parseDouble(stringvalueofNRMSE);
							System.out.println("Value of wind nrmse " + valueofNRMSE);
						}
					} catch (Exception e) {
						e.printStackTrace();

					}

				}
			}
			if (valueofnmae == (Double.parseDouble(f.format((sumofNMAE / noofrows) / installedcapacity)))) {
				System.out.println("Value Verified and Value of NMAE and NRMSE "
						+ (f.format((sumofNMAE / noofrows) / installedcapacity)) + " "
						+ f.format((sumofNRMSE / noofrows)));
			} else {
				System.out.println("Value NOT Verified and Value of NMAE and NRMSE "
						+ (f.format((sumofNMAE / noofrows) / installedcapacity)) + " "
						+ f.format((sumofNRMSE / noofrows)));
			}
			System.out.println("<=====****=====>");
			System.out.println("Average of Load: " + f.format(sumofLoad / noofrows));
			System.out.println("Average of NMAE:" + (f.format((sumofNMAE / noofrows) / installedcapacity)));
			System.out.println("Average of NRMSE:" + f.format(sumofNRMSE / noofrows));

		} catch (Exception e) {
			e.printStackTrace();
		}

		File file1 = new File("C:\\Users\\Manikaran\\Downloads\\Power_Forecast_Vs_Actual_Consumption.csv");
		File file2 = new File("C:\\Users\\Manikaran\\Downloads\\NMAE_&_NMAPE.csv");
		File file3 = new File("C:\\Users\\Manikaran\\Downloads\\NRMSE_&_NPRMSE.csv");
		try {
			if (!file1.exists() || !file2.exists() || !file3.exists()) {
				System.out.println("File Not Found");
			} else {
				file1.delete();
				file2.delete();
				file3.delete();
				System.out.println("File Deleted");
			}
		} catch (Exception e) {
			System.out.println("Why im Here");
		}
	}

}
