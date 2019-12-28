package com.api.rest.api.helper;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpStatus;
import org.apache.http.client.protocol.HttpClientContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.rest.api.model.ApiAll;
import com.api.rest.api.model.RestResponse;
import com.api.rest.api.util.GenerateJsonPostData;
import com.api.rest.api.util.ReadJsonAsString;
import com.api.rest.api.util.SerializationOfJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetMethodTest
{
	@Test(priority=1)
	public void testGetPingAlive() throws Exception
	{
		String url="http://localhost:8080/laptop-bag/webapi/api/ping/hello";
		Map<String, String> headers = new HashMap<String, String>();
		RestResponse response= RestApiHelper.performGetRequest(url, headers);
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
		String url="http://localhost:8080/laptop-bag/webapi/api/find/145";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept","application/json");
		RestResponse response= RestApiHelper.performGetRequest(url, headers);
		System.out.println(response.toString());
		Assert.assertTrue(HttpStatus.SC_OK==response.getStatusCode() || (HttpStatus.SC_NO_CONTENT==response.getStatusCode()), "Expected Status Code not present" );	
		ApiAll body = new SerializationOfJson().SerializeGson(response.getResponseBody(), ApiAll.class);
		Assert.assertEquals("Google", body.getBrandName());
	}
	
	@Test(priority=4)
	public void testSecureGetFindWithId()
	{
		System.out.println("### Get Secure Method ###");
		String url="http://localhost:8080/laptop-bag/webapi/secure/find/145";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept","application/json");
		//headers.put("Authorization","Basic YWRtaW46d2VsY29tZQ==");
		headers.put("Authorization", Base64.encodeBase64String("admin:welcome".getBytes()));
		RestResponse response= RestApiHelper.performGetRequest(url, headers);
		System.out.println(response.toString());
		Assert.assertTrue(HttpStatus.SC_OK==response.getStatusCode() || (HttpStatus.SC_NO_CONTENT==response.getStatusCode()), "Expected Status Code not present" );	
		ApiAll body = new SerializationOfJson().SerializeGson(response.getResponseBody(), ApiAll.class);
		Assert.assertEquals("Google", body.getBrandName());
	}
	
	@Test(priority=5)
	public void testSecurePromptGetFindAll()
	{
		System.out.println("### Prompt Secure Method ###");
		String postJsonStr = new ReadJsonAsString().getJsonString(new GenerateJsonPostData().getClass());
		new PostRequest().PostRequestMethod(postJsonStr);
		String url = "http://localhost:8080/laptop-bag/webapi/prompt/all";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept","application/json");
		HttpClientContext context = new PromptAuth().setCredentials();
		RestResponse response= RestApiHelper.performGetRequest(url, headers, context);
		System.out.println(response.toString());
		Assert.assertTrue(HttpStatus.SC_OK==response.getStatusCode() || (HttpStatus.SC_NO_CONTENT==response.getStatusCode()), "Expected Status Code not present" );
	}
}