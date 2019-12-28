package com.api.rest.api.helper;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.api.rest.api.model.RestResponse;
import com.api.rest.api.util.GenerateJsonPostData;
import com.api.rest.api.util.GenerateJsonPutData;
import com.api.rest.api.util.ReadJsonAsString;

public class PutRequest 
{
	public static void main(String[] args)
	{
		new PutRequest().PutRequestMethod();
	}
	
	public void PutRequestMethod()
	{
		String postJsonStr = new ReadJsonAsString().getJsonString(new GenerateJsonPostData().getClass());
		new PostRequest().PostRequestMethod(postJsonStr);
		String id = new ReadJsonAsString().getStringBetween(postJsonStr, "\"Id\":", ",");
		System.out.println("### Put Request Starts ###");
		String putJsonStr = new ReadJsonAsString().getJsonString(new GenerateJsonPutData().getClass(), id);
		RequestBuilder buildPut = RequestBuilder.put("http://localhost:8080/laptop-bag/webapi/api/update").
		setHeader("Content-Type", "application/json").
		setHeader("Accept", "application/json");
		HttpUriRequest put= buildPut.setEntity(new StringEntity(putJsonStr, ContentType.APPLICATION_JSON)).build();
		CloseableHttpResponse response=null;
		try(CloseableHttpClient client = HttpClientBuilder.create().build();)
		{
			response=client.execute(put);
			ResponseHandler<String> handler = new BasicResponseHandler();
			RestResponse restResponse=new RestResponse(response.getStatusLine().getStatusCode(), handler.handleResponse(response));
			System.out.println("Response code: "+restResponse.getStatusCode());
			System.out.println("Response body: "+restResponse.getResponseBody());
			System.out.println("### Put Request Ends ###");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
