package com.qa.getco.testcases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.BodyofAPI;
import com.qa.getco.utility.TestUtil;;

public class POSTAPITest extends TestBase {
	TestBase testBase;
	String serviceurl;
	String apiurl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;

	/*
	 * @BeforeMethod
	 */	public void setUp() {
		testBase = new TestBase();
		serviceurl = prop.getProperty("URL");
		apiurl = prop.getProperty("serviceURL");

		url = serviceurl + apiurl;
	}

	/* @Test */
	public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		List<String> generationType=Arrays.asList("WIND","SOLAR");
		for (String generation : generationType) {
			if (generation.equalsIgnoreCase("WIND")) {
				restClient = new RestClient();
				HashMap<String, String> headerMap = new HashMap<String, String>();
				headerMap.put("Content-Type", "application/json");
				headerMap.put("cookie",
						"sel=cPF31mO1304cmHQnLZ1JwsIfyhJKgqkw1kCS1ddoaYUyYqe5gySgildYgDh1J0nAbbXPKR0gYi3aDcbMv0CskZL6qqg3g6slIycBji15WRMbgUFd5x4jxgFBYB4FIC54m3KJIK0fOJUB3CF63NXs7lEEyIPeuxewu85hNxzLF3MPIh22iKHS8mZK5iEa1ZDBIREjMPQw7col4vOLA6nIa3ihl6q7kmj02D4jx14E4daGV7ViKl3LP7t7DWFCykc");

				// Jacson API
				ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

				BodyofAPI boapi = new BodyofAPI("GJ", dateFormat.format(cal.getTime()), "WIND");// use 1

				// Object to Json File Conversion
				mapper.writeValue(new File(System.getProperty("user.dir") + "/src/test/java/com/qa/data/body.json"), boapi);

				// object to jsonString
				String boapiString = mapper.writeValueAsString(boapi);
				System.out.println(boapiString);

				closeableHttpResponse = restClient.post(url, boapiString, headerMap);

				// Check Status
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
				Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

				// JsonString
				String postResponseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(postResponseString);
				System.out.println("The Response POST API is:---> " + postResponseString);

				// Json to java Object
				BodyofAPI bodyofApi = mapper.readValue(postResponseString, BodyofAPI.class);
				/* System.out.println("Waste of Print " + bodyofApi); */

				int lenthofJsonArray = responseJson.getJSONArray("data").length();
				/*
				 * System.out.println("Length " + lenthofJsonArray);
				 */
				// LoadCVS File
				String csvWindFile = "C:\\Users\\Manikaran\\Downloads\\Wind_Forecast.csv";
				BufferedReader br = null;
				String line = "";
				String cvsSplitBy = ",";
				br = new BufferedReader(new FileReader(csvWindFile));
				br.readLine();
				br.readLine();
				String[] windGenerationValue;
				String uivalue;

				for (int j = 0; j < lenthofJsonArray; j++) {
					String actualPower = TestUtil.getValueByJPath(responseJson, "/data[" + j + "]/actualPower");

					int itemCount = line.split(",").length;
					// System.out.println("Item Count "+itemCount);

					if (actualPower != null && (line = br.readLine()) != null) {
						windGenerationValue = line.split(cvsSplitBy);
						if (itemCount == 6 && windGenerationValue.length==6) {
							uivalue = windGenerationValue[3].replaceAll("^\"|\"$", "");
							if (uivalue.equals(actualPower)) {
								System.out.println("Matched UI Value " + j + " " + uivalue + " API Value " + actualPower+" @ Wind");
							} else {
								System.out.println("NOT-Matched UI Value " + j + " " + uivalue + " API Value " + actualPower+" @ Wind");
							}

						} if(itemCount<6) {
							System.out.println("Line Having less Than 6 Elements:: "+itemCount+" @ Line Number"+j+" @ Wind");
						}

					} else {
						System.out.println("Actual Power====>" + j + " ZERO @ Wind");
					}
				}

		
			} else {
				restClient = new RestClient();
				HashMap<String, String> headerMap = new HashMap<String, String>();
				headerMap.put("Content-Type", "application/json");
				headerMap.put("cookie",
						"sel=cPF31mO1304cmHQnLZ1JwsIfyhJKgqkw1kCS1ddoaYUyYqe5gySgildYgDh1J0nAbbXPKR0gYi3aDcbMv0CskZL6qqg3g6slIycBji15WRMbgUFd5x4jxgFBYB4FIC54m3KJIK0fOJUB3CF63NXs7lEEyIPeuxewu85hNxzLF3MPIh22iKHS8mZK5iEa1ZDBIREjMPQw7col4vOLA6nIa3ihl6q7kmj02D4jx14E4daGV7ViKl3LP7t7DWFCykc");

				// Jacson API
				ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

				BodyofAPI boapi = new BodyofAPI("GJ", dateFormat.format(cal.getTime()), "SOLAR");// use 1

				// Object to Json File Conversion
				mapper.writeValue(new File(System.getProperty("user.dir") + "/src/test/java/com/qa/data/body.json"), boapi);

				// object to jsonString
				String boapiString = mapper.writeValueAsString(boapi);
				System.out.println(boapiString);

				closeableHttpResponse = restClient.post(url, boapiString, headerMap);

				// Check Status
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
				Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);

				// JsonString
				String postResponseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(postResponseString);
				System.out.println("The Response POST API is:---> " + postResponseString);

				// Json to java Object
				BodyofAPI bodyofApi = mapper.readValue(postResponseString, BodyofAPI.class);
				/*
				 * System.out.println("Waste of Print " + bodyofApi);
				 */
				int lenthofJsonArray = responseJson.getJSONArray("data").length();
				/*
				 * System.out.println("Length " + lenthofJsonArray);
				 */
				// LoadCVS File
				String csvWindFile = "C:\\Users\\Manikaran\\Downloads\\Solar_Forecast.csv";
				BufferedReader br = null;
				String line = "";
				String cvsSplitBy = ",";
				br = new BufferedReader(new FileReader(csvWindFile));
				br.readLine();
				br.readLine();
				String[] windGenerationValue;
				String uivalue;

				for (int j = 0; j < lenthofJsonArray; j++) {
					String actualPower = TestUtil.getValueByJPath(responseJson, "/data[" + j + "]/actualPower");

					int itemCount = line.split(",").length;
					if (actualPower != null && (line = br.readLine()) != null) {
						windGenerationValue = line.split(cvsSplitBy);
						if (itemCount == 6 && windGenerationValue.length==6) {
							uivalue = windGenerationValue[3].replaceAll("^\"|\"$", "");
							// System.out.println("ui Value "+uivalue);
							// uivalue = windGenerationValue[3].replaceAll("^\"|\"$", "");
							if (uivalue.equals(actualPower)) {
								System.out.println("Matched UI Value " + j + " " + uivalue + " API Value " + actualPower+" @ Solar");
							} else  {
								System.out.println("NOT-Matched UI Value " + j + " " + uivalue + " API Value " + actualPower+" @ Solar");
							}

						} if(itemCount<6) {
							System.out.println("Line Having less Than 6 Elements:: "+itemCount+" @ Line Number"+j+" @ Solar");
						}

					} else {
						System.out.println("Actual Power====>" + j + " ZERO @ Solar");
					}
				}
			}
		}
	}
}
