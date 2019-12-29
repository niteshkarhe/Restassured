package com.api.rest.api.helper;

import java.io.File;
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
import com.api.rest.api.util.GenerateJsonPutData;
import com.api.rest.api.util.ReadJsonAsString;
import com.api.rest.api.util.SerializationOfJson;

public class AsyncRequestTest 
{
	private static String id;
	
	@Test(priority=1)
	public void testPostRequest()
	{
		File file = new File("D:\\NK\\API\\API Projects\\rest-api-helper\\inputjson\\api-post.json");
		String jsonStr = new ReadJsonAsString().getJsonString(file);
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		RestResponse response = HttpAsyncClientHelper.performPostRequestAsync("http://localhost:8080/laptop-bag/webapi/api/add", file, ContentType.APPLICATION_JSON, headers);
		System.out.println("Status Code: "+response.getStatusCode());
		System.out.println("Response body: "+response.getResponseBody());
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		System.out.println("### Post Async Request Ends ###");
		String id=new ReadJsonAsString().getStringBetween(jsonStr, "\"Id\":", ",");
		String laptopName=new ReadJsonAsString().getStringBetween(jsonStr, "\"LaptopName\":\"", "\"}");
		String getUrl="http://localhost:8080/laptop-bag/webapi/api/find/"+id;
		response = HttpAsyncClientHelper.performGetRequestAsync(getUrl, headers);
		ApiAll body = new SerializationOfJson().SerializeGson(response.getResponseBody(), ApiAll.class);
		Assert.assertEquals(id, body.getId().toString());
		Assert.assertEquals(laptopName, body.getLaptopName().toString());
		System.out.println("### Get Async Request Ends ###");
	}
	
	@Test(priority=2)
	public void testPutRequest()
	{
		String postJsonStr = new ReadJsonAsString().getJsonString(new GenerateJsonPostData().getClass());
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");
		HttpAsyncClientHelper.performPostRequestAsync("http://localhost:8080/laptop-bag/webapi/api/add", postJsonStr, ContentType.APPLICATION_JSON, headers);
		System.out.println("### Post Async Request Ends ###");
		id = new ReadJsonAsString().getStringBetween(postJsonStr, "\"Id\":", ",");
		String putJsonStr = new ReadJsonAsString().getJsonString(new GenerateJsonPutData().getClass(), id);
		String url="http://localhost:8080/laptop-bag/webapi/api/update";
		RestResponse response = HttpAsyncClientHelper.performPutRequestAsync(url, putJsonStr,ContentType.APPLICATION_JSON, headers);
		System.out.println("Status Code: "+response.getStatusCode());
		System.out.println("Response body: "+response.getResponseBody());
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		System.out.println("### Put Async Request Ends ###");
		String getUrl="http://localhost:8080/laptop-bag/webapi/api/find/"+id;
		response = HttpAsyncClientHelper.performGetRequestAsync(getUrl, headers);
		String brandname=new ReadJsonAsString().getStringBetween(putJsonStr, "\"BrandName\":\"", "\",");
		ApiAll body = new SerializationOfJson().SerializeGson(response.getResponseBody(), ApiAll.class);
		Assert.assertEquals(brandname, body.getBrandName().toString());
		System.out.println("### Get Async Request Ends ###");
	}
	
	@Test(priority=3)
	public void testGetRequest()
	{
		String url="http://localhost:8080/laptop-bag/webapi/api/all";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept","application/json");
		RestResponse response = HttpAsyncClientHelper.performGetRequestAsync(url, headers);
		System.out.println("Status Code: "+response.getStatusCode());
		System.out.println("Response body: "+response.getResponseBody());
		System.out.println("### Get Async Request Ends ###");
	}
	
	@Test(priority=4)
	public void testDelete()
	{
		String url = "http://localhost:8080/laptop-bag/webapi/api/delete/"+id;
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");
		RestResponse response = HttpAsyncClientHelper.performDeleteRequestAsync(url, null);
		System.out.println("Status Code: "+response.getStatusCode());
		System.out.println("Response body: "+response.getResponseBody());
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
	}
}
