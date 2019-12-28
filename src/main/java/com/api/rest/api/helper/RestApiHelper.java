package com.api.rest.api.helper;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import com.api.rest.api.model.RestResponse;

public class RestApiHelper {
	
	public static RestResponse performPostRequest(String url, Object content, ContentType type, Map<String, String> headers)
	{
		try {
			return performPostRequest(new URI(url), content, type, headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performPostRequest(URI url, Object content, ContentType type, Map<String, String> headers)
	{
		HttpPost post = new HttpPost(url);
		if(headers !=null)
		{
			post.setHeaders(getCustomHeaders(headers));
		}
		post.setEntity(getHttpEntity(content, type));
		return performRequest(post);
	}
	
	public static RestResponse performGetRequest(URI url, Map<String, String> headers, HttpClientContext... context)
	{
		HttpGet get = new HttpGet(url);
		System.out.println("Get Request url: ["+url+"]");
		if(headers!=null)
		{
			get.setHeaders(getCustomHeaders(headers));
		}
		System.out.println("### Get Request Ends ###");
		if(context.length!=0)
		{
			return performRequest(get, context[0]);
		}
		else
		{
			return performRequest(get);
		}
	}
	
	public static RestResponse performGetRequest(String url, Map<String, String> headers, HttpClientContext... context)
	{
		System.out.println("### Get Request Starts ###");
		try {
			if(context.length!=0)
			{
				return performGetRequest(new URI(url), headers, context[0]);
			}
			else 
			{
				return performGetRequest(new URI(url), headers);
			}
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	private static HttpEntity getHttpEntity(Object content, ContentType type)
	{
		if(content instanceof String)
		{
			return new StringEntity((String)content, type);
		}
		else if(content instanceof File)
		{
			return new FileEntity((File)content, type);
		}
		else
		{
			throw new RuntimeException("Entity type not found.");
		}
	}
	
	public static Header[] getCustomHeaders(Map<String, String> headers)
	{
		Header[] customHeaders = new  Header[headers.size()];
		int i=0;
		for(String key: headers.keySet())
		{
			customHeaders[i++]=new BasicHeader(key, headers.get(key));
		}
		return customHeaders;
	}
	
	private static RestResponse performRequest(HttpUriRequest method)
	{
		CloseableHttpResponse response=null;
		try(CloseableHttpClient client = HttpClientBuilder.create().build();)
		{
			response = client.execute(method);
			ResponseHandler<String> body = new BasicResponseHandler();
			RestResponse restResponse=new RestResponse(response.getStatusLine().getStatusCode(), body.handleResponse(response));
			return restResponse;
			//System.out.println(restResponse.toString());
		}
		catch(Exception e)
		{
			if(e instanceof HttpResponseException)
			{
				return new RestResponse(response.getStatusLine().getStatusCode(), e.getMessage());
			}
			throw new RuntimeException(e.getMessage(), e);
		} 
	}
	
	private static RestResponse performRequest(HttpUriRequest method, HttpClientContext context)
	{
		CloseableHttpResponse response=null;
		try(CloseableHttpClient client = HttpClientBuilder.create().build();)
		{
			response = client.execute(method, context);
			ResponseHandler<String> body = new BasicResponseHandler();
			RestResponse restResponse=new RestResponse(response.getStatusLine().getStatusCode(), body.handleResponse(response));
			return restResponse;
			//System.out.println(restResponse.toString());
		}
		catch(Exception e)
		{
			if(e instanceof HttpResponseException)
			{
				return new RestResponse(response.getStatusLine().getStatusCode(), e.getMessage());
			}
			throw new RuntimeException(e.getMessage(), e);
		} 
	}
	
	public static RestResponse performDeleteRequest(String url)
	{
		System.out.println("### Delete Request Starts ###");
		System.out.println("URL: "+url);
		try 
		{
			return performDeleteRequest(new URI(url));
		} 
		catch (URISyntaxException e) 
		{
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performDeleteRequest(URI uri)
	{
		HttpUriRequest delete = RequestBuilder.delete(uri).build();
		/*if(headers!=null)
		{
			delete.setHeaders(getCustomHeaders(headers));
		}*/
		System.out.println("### Delete Request Ends ###");
		return performRequest(delete);
	}
	
	public static RestResponse performPutRequest(String url, Object content, ContentType type, Map<String, String> headers)
	{
		System.out.println("### Put Request Starts ###");
		System.out.println("URL: "+url);
		try 
		{
			return performPutRequest(new URI(url), content, type, headers);
		} 
		catch (URISyntaxException e) 
		{
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static RestResponse performPutRequest(URI uri, Object content, ContentType type, Map<String, String> headers)
	{
		RequestBuilder buildPut = RequestBuilder.put(uri);
		if(headers!=null)
		{
			for(String key: headers.keySet())
			{
				buildPut.setHeader(key, headers.get(key));
			}
		}
		HttpUriRequest put = buildPut.setEntity(getHttpEntity(content, type)).build();
		System.out.println("### Put Request Ends ###");
		return performRequest(put);
	}
}
