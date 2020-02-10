package com.api.rest.api.model;

import org.apache.http.client.methods.CloseableHttpResponse;

public class RestResponse {
	private int statusCode;
	private String responseBody;
	private CloseableHttpResponse response;
	
	public int getStatusCode()
	{
		return statusCode;
	}
	
	public String getResponseBody()
	{
		return responseBody;
	}
	
	public CloseableHttpResponse getCloseableResponse()
	{
		return response;
	}
	
	public RestResponse(int statusCode, String responseBody, CloseableHttpResponse response)
	{
		this.statusCode=statusCode;
		this.responseBody=responseBody;
		this.response=response;
	}
	
	@Override
	public String toString()
	{
		return String.format("Status Code : %1s Body : %2s", this.statusCode, this.responseBody);
	}

	public static void main(String[] args) 
	{
		
	}
}