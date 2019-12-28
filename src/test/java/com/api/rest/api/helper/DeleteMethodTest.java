package com.api.rest.api.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.rest.api.model.RestResponse;
import com.api.rest.api.util.GenerateJsonPostData;
import com.api.rest.api.util.ReadJsonAsString;

public class DeleteMethodTest 
{
	@Test
	public void testDelete()
	{
		String jsonStr = new ReadJsonAsString().getJsonString(new GenerateJsonPostData().getClass());
		String id = new PostRequest().PostRequestMethod(jsonStr);
		String url = "http://localhost:8080/laptop-bag/webapi/api/delete/"+id;
		Map<String, String> headers = new LinkedHashMap<String, String>();
		/*headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");*/
		RestResponse response = RestApiHelper.performDeleteRequest(url);
		System.out.println("Status Code: "+response.getStatusCode());
		System.out.println("Response body: "+response.getResponseBody());
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		String getUrl="http://localhost:8080/laptop-bag/webapi/api/find/"+id;
		response = RestApiHelper.performGetRequest(getUrl, headers);
		System.out.println("After deleting id: ["+id+"], Get Request response code: "+HttpStatus.SC_NOT_FOUND);
		Assert.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
	}
}
