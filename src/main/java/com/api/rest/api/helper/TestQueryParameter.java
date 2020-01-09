package com.api.rest.api.helper;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

import com.api.rest.api.model.RestResponse;

public class TestQueryParameter 
{
	public void testQuery()
	{
		try 
		{
			URI url = new URIBuilder()
			.setScheme("http")
			.setHost("localhost:8080")
			.setPath("laptop-bag/webapi/api/query")
			.setParameter("Id", "145")
			.setParameter("LaptopName", "Go Pie").build();
			
			RestResponse response = RestApiHelper.performGetRequest(url, null);
			System.out.println(response.toString());
		} 
		catch (URISyntaxException e) 
		{
			e.printStackTrace();
		}
	}
}
