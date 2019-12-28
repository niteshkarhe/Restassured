package com.api.rest.api.helper;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.rest.api.model.ApiAll;
import com.api.rest.api.model.RestResponse;
import com.api.rest.api.util.SerializationOfJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetMethodTest
{
	@Test(priority=1)
	public void testGetPingAlive() throws Exception
	{
		String url="http://localhost:8080/laptop-bag/webapi/api/ping/hello";
		RestResponse response= RestApiHelper.performGetRequest(url, null);
		System.out.println(response.toString());
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		Assert.assertEquals("Hi! hello", response.getResponseBody() );
	}
	
	@Test(priority=2)
	public void testGetAll()
	{
		String url="http://localhost:8080/laptop-bag/webapi/api/all";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept","application/json");
		RestResponse response= RestApiHelper.performGetRequest(url, headers);
		System.out.println(response.toString());
		Assert.assertTrue(HttpStatus.SC_OK==response.getStatusCode() || (HttpStatus.SC_NO_CONTENT==response.getStatusCode()), "Expected Status Code not present" );	
	}
	
	@Test(priority=3)
	public void testGetFindWithId()
	{
		String url="http://localhost:8080/laptop-bag/webapi/api/find/126";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept","application/json");
		RestResponse response= RestApiHelper.performGetRequest(url, headers);
		System.out.println(response.toString());
		Assert.assertTrue(HttpStatus.SC_OK==response.getStatusCode() || (HttpStatus.SC_NO_CONTENT==response.getStatusCode()), "Expected Status Code not present" );	
		ApiAll body = new SerializationOfJson().SerializeGson(response.getResponseBody(), ApiAll.class);
		Assert.assertEquals("Mac", body.getBrandName());
	}
}