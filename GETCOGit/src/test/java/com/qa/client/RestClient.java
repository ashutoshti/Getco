package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

	// GET METHOD without Headers
	public CloseableHttpResponse getMethod(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url); // http get request
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet); // hit the GET URL
		return closeableHttpResponse;
	}
	
	// GET METHOD with Headers
	public CloseableHttpResponse getMethod(String url, HashMap<String, String> headermap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url); // http get request
		for (Map.Entry<String, String> entry:headermap.entrySet()) {
			httpGet.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet); // hit the GET URL
		return closeableHttpResponse;
	}

	//Post Method
		public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
			CloseableHttpClient httpClient=HttpClients.createDefault();
			HttpPost httpPost=new HttpPost(url);//Http Post Request
			httpPost.setEntity(new StringEntity(entityString));// for body
			
			//for header
			for (Map.Entry<String, String> entry:headerMap.entrySet()) {
				httpPost.addHeader(entry.getKey(),entry.getValue());
			}
			CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
			return closeableHttpResponse;

		}
}