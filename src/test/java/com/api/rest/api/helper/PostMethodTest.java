package com.api.rest.api.helper;

import java.io.File;
import java.io.IOException;
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
import com.api.rest.api.util.SerializationOfXml;
import com.api.rest.api.xmlmodel.ApiXmlPost;

public class PostMethodTest 
{
	@Test(priority=1, enabled=true)
	public void testPostWithJsoStr()
	{
		String jsonStr = new ReadJsonAsString().getJsonString(new GenerateJsonPostData().getClass());
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		RestResponse response = RestApiHelper.performPostRequest("http://localhost:8080/laptop-bag/webapi/api/add", jsonStr, ContentType.APPLICATION_JSON, headers);
		System.out.println("Status Code: "+response.getStatusCode());
		System.out.println("Response body: "+response.getResponseBody());
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		String id=new ReadJsonAsString().getStringBetween(jsonStr, "\"Id\":", ",");
		String laptopName=new ReadJsonAsString().getStringBetween(jsonStr, "\"LaptopName\":\"", "\"}");
		String getUrl="http://localhost:8080/laptop-bag/webapi/api/find/"+id;
		response = RestApiHelper.performGetRequest(getUrl, headers);
		ApiAll body = new SerializationOfJson().SerializeGson(response.getResponseBody(), ApiAll.class);
		Assert.assertEquals(id, body.getId().toString());
		Assert.assertEquals(laptopName, body.getLaptopName().toString());
	}
	
	@Test(priority=2, enabled=true)
	public void testPostWithJsonFile()
	{
		File file = new File("D:\\NK\\API\\API Projects\\rest-api-helper\\inputjson\\api-post.json");
		String jsonStr = new ReadJsonAsString().getJsonString(file);
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		RestResponse response = RestApiHelper.performPostRequest("http://localhost:8080/laptop-bag/webapi/api/add", file, ContentType.APPLICATION_JSON, headers);
		System.out.println("Status Code: "+response.getStatusCode());
		System.out.println("Response body: "+response.getResponseBody());
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		String id=new ReadJsonAsString().getStringBetween(jsonStr, "\"Id\":", ",");
		String laptopName=new ReadJsonAsString().getStringBetween(jsonStr, "\"LaptopName\":\"", "\"}");
		String getUrl="http://localhost:8080/laptop-bag/webapi/api/find/"+id;
		response = RestApiHelper.performGetRequest(getUrl, headers);
		ApiAll body = new SerializationOfJson().SerializeGson(response.getResponseBody(), ApiAll.class);
		Assert.assertEquals(id, body.getId().toString());
		Assert.assertEquals(laptopName, body.getLaptopName().toString());
	}
	
	@Test(priority=3, enabled=true)
	public void testPostWithXml()
	{
		ApiXmlPost body=null;
		File file = new File("D:\\NK\\API\\API Projects\\rest-api-helper\\inputjson\\api-post.json");
		String jsonStr = new ReadJsonAsString().getJsonString(file);
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Accept", "application/xml");
		headers.put("Content-Type", "application/json");
		RestResponse response = RestApiHelper.performPostRequest("http://localhost:8080/laptop-bag/webapi/api/add", file, ContentType.APPLICATION_JSON, headers);
		System.out.println("Status Code: "+response.getStatusCode());
		System.out.println("Response body: "+response.getResponseBody());
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		String id=new ReadJsonAsString().getStringBetween(jsonStr, "\"Id\":", ",");
		String laptopName=new ReadJsonAsString().getStringBetween(jsonStr, "\"LaptopName\":\"", "\"}");
		String getUrl="http://localhost:8080/laptop-bag/webapi/api/find/"+id;
		response = RestApiHelper.performGetRequest(getUrl, headers);
		body = new SerializationOfXml().DeserializeXml((Object) response.getResponseBody(), ApiXmlPost.class);
		Assert.assertEquals(id, body.getLaptop().getId().toString());
		Assert.assertEquals(laptopName, body.getLaptop().getLaptopName().toString());
	}
}
