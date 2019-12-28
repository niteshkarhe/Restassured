package com.api.rest.api.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.rest.api.model.ApiAll;
import com.api.rest.api.model.RestResponse;
import com.api.rest.api.util.GenerateJsonPostData;
import com.api.rest.api.util.GenerateJsonPutData;
import com.api.rest.api.util.ReadJsonAsString;
import com.api.rest.api.util.SerializationOfJson;

public class PutMethodTest 
{
	@Test
	public void testPutMethod()
	{
		String postJsonStr = new ReadJsonAsString().getJsonString(new GenerateJsonPostData().getClass());
		new PostRequest().PostRequestMethod(postJsonStr);
		String id = new ReadJsonAsString().getStringBetween(postJsonStr, "\"Id\":", ",");
		String putJsonStr = new ReadJsonAsString().getJsonString(new GenerateJsonPutData().getClass(), id);
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");
		String url="http://localhost:8080/laptop-bag/webapi/api/update";
		RestResponse response = RestApiHelper.performPutRequest(url, putJsonStr,ContentType.APPLICATION_JSON, headers);
		System.out.println("Status Code: "+response.getStatusCode());
		System.out.println("Response body: "+response.getResponseBody());
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		String getUrl="http://localhost:8080/laptop-bag/webapi/api/find/"+id;
		response = RestApiHelper.performGetRequest(getUrl, headers);
		String brandname=new ReadJsonAsString().getStringBetween(putJsonStr, "\"BrandName\":\"", "\",");
		ApiAll body = new SerializationOfJson().SerializeGson(response.getResponseBody(), ApiAll.class);
		Assert.assertEquals(brandname, body.getBrandName().toString());
	}
}
