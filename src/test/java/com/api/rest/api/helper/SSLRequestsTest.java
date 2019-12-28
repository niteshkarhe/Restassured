package com.api.rest.api.helper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.rest.api.model.ApiAll;
import com.api.rest.api.model.RestResponse;
import com.api.rest.api.util.GenerateJsonPostData;
import com.api.rest.api.util.ReadJsonAsString;
import com.api.rest.api.util.SerializationOfJson;

public class SSLRequestsTest 
{	
	@Test(priority=1)
	public void testPost()
	{
		String jsonStr = new ReadJsonAsString().getJsonString(new GenerateJsonPostData().getClass());
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		RestResponse response = HttpsClientHelper.performPostWithSSL("https://localhost:8443/laptop-bag/webapi/sslres/add", jsonStr, ContentType.APPLICATION_JSON, headers);
		System.out.println("Status Code: "+response.getStatusCode());
		System.out.println("Response body: "+response.getResponseBody());
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		System.out.println("### Post SSL Request Ends ###");
		String id=new ReadJsonAsString().getStringBetween(jsonStr, "\"Id\":", ",");
		String laptopName=new ReadJsonAsString().getStringBetween(jsonStr, "\"LaptopName\":\"", "\"}");
		String getUrl="https://localhost:8443/laptop-bag/webapi/sslres/find/"+id;
		response = HttpsClientHelper.performGetRequestWithSSL(getUrl, headers);
		ApiAll body = new SerializationOfJson().SerializeGson(response.getResponseBody(), ApiAll.class);
		Assert.assertEquals(id, body.getId().toString());
		Assert.assertEquals(laptopName, body.getLaptopName().toString());
	}
	
	@Test(priority=2)
	public void testGetAll()
	{
		String url="https://localhost:8443/laptop-bag/webapi/sslres/all";
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		RestResponse response= HttpsClientHelper.performGetRequestWithSSL(url, headers);
		System.out.println(response.toString());
		Assert.assertTrue(HttpStatus.SC_OK==response.getStatusCode() || (HttpStatus.SC_NO_CONTENT==response.getStatusCode()), "Expected Status Code not present" );
		System.out.println("### Get SSL Request Ends ###");
	}
}
