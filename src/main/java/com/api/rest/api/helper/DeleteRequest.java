package com.api.rest.api.helper;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.api.rest.api.model.RestResponse;
import com.api.rest.api.util.GenerateJsonPostData;
import com.api.rest.api.util.ReadJsonAsString;

public class DeleteRequest 
{
	public static void main(String[] args)
	{
		DeleteRequest o = new DeleteRequest();
		o.RequestBuilderDelete();
		o.HttpDeleteMethod();
	}
	
	public void RequestBuilderDelete()
	{
		System.out.println("### RequestBuilderDelete Method###");
		String jsonStr = new ReadJsonAsString().getJsonString(new GenerateJsonPostData().getClass());
		String id = new PostRequest().PostRequestMethod(jsonStr);
		String URI="http://localhost:8080/laptop-bag/webapi/api/delete/"+id;
		System.out.println("Delete URI: "+URI);
		HttpUriRequest delete = RequestBuilder.delete(URI).build();
		CloseableHttpResponse response=null;
		try(CloseableHttpClient client = HttpClientBuilder.create().build();)
		{
			response = client.execute(delete);
			ResponseHandler<String> handler = new BasicResponseHandler();
			RestResponse restResponse=new RestResponse(response.getStatusLine().getStatusCode(), handler.handleResponse(response));
			System.out.println(restResponse.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void HttpDeleteMethod()
	{
		System.out.println("### HttpDeleteMethod Method###");
		String jsonStr = new ReadJsonAsString().getJsonString(new GenerateJsonPostData().getClass());
		String id = new PostRequest().PostRequestMethod(jsonStr);
		String URI="http://localhost:8080/laptop-bag/webapi/api/delete/"+id;
		HttpDelete delete = new HttpDelete(URI);
		CloseableHttpResponse response=null;
		try(CloseableHttpClient client = HttpClientBuilder.create().build();)
		{
			response = client.execute(delete);
			ResponseHandler<String> body = new BasicResponseHandler();
			RestResponse restResponse=new RestResponse(response.getStatusLine().getStatusCode(), body.handleResponse(response));
			System.out.println("Response code: "+restResponse.getStatusCode());
			System.out.println("Response body: "+restResponse.getResponseBody());
		}
		catch(Exception e)
		{
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}