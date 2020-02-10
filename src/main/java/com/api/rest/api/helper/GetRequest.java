package com.api.rest.api.helper;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.StatusLine;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.api.rest.api.model.RestResponse;
import com.api.rest.api.util.GenerateJsonPostData;
import com.api.rest.api.util.ReadJsonAsString;

public class GetRequest {
	//Step 1: Create Http Get method
	//Step 2: Create Http Client
	//Step 3: Execute Http method using client
	//Step 4: Catch the response of execution
	//Step 5: Display response at console
	public static void main(String[] args) throws UnsupportedOperationException, IOException {
		/*HttpGet get = new HttpGet("http://localhost:8080/laptop-bag/webapi/api/ping/hello");
		try(CloseableHttpClient client = HttpClientBuilder.create().build();
				CloseableHttpResponse response = client.execute(get)) 
		{	
			//StatusLine status = response.getStatusLine();
			ResponseHandler<String> body = new BasicResponseHandler();
			RestResponse restResponse = new RestResponse(response.getStatusLine().getStatusCode(), body.handleResponse(response));
			String getBody = body.handleResponse(response);
			System.out.println("Status code of response: "+status.getStatusCode());
			System.out.println("Protocol version of response: "+status.getProtocolVersion());
			System.out.println("Response of body: "+getBody);
			System.out.println(restResponse.toString());
			client.close();
			response.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
		RestResponse response = RestApiHelper.performGetRequest("http://localhost:8080/laptop-bag/webapi/api/ping/hello", null);
		CloseableHttpResponse resp = response.getCloseableResponse();
		System.out.println(response.toString());
		System.out.println("resp.containsHeader(Content-Type: text/plain): "+resp.containsHeader("Content-Type"));
		System.out.println("resp.getAllHeaders(): "+Arrays.toString(resp.getAllHeaders()));
		System.out.println("resp.getEntity(): "+resp.getEntity());
		System.out.println(String.format("resp.getEntity().getContentLength(): %1$s, resp.getEntity().getContentEncoding(): %2$s",resp.getEntity().getContentLength(), resp.getEntity().getContentEncoding()));
		System.out.println("resp.getFirstHeader(\"Date\") returns first header that matches: "+resp.getFirstHeader("Date"));
		System.out.println("resp.getHeaders(\"Content-Length\") returns all headers with specified name: "+Arrays.toString(resp.getHeaders("Content-Length")));
		System.out.println("resp.getLastHeader(\"Server\") returns last header that matches: "+resp.getLastHeader("Server"));
		System.out.println("resp.getLocale(). Locale is used to determine the reason phrase for the status code: "+resp.getLocale());
		System.out.println(String.format("resp.getLocale().getDisplayCountry(): %1$s, "
				+ "		\nresp.getLocale().getDisplayLanguage(): %2$s,"
				+ "		\nresp.getLocale().getCountry(): %3$s, "
				+ "\nresp.getLocale().getScript(): %4$s",
				resp.getLocale().getDisplayCountry(), resp.getLocale().getDisplayLanguage(), resp.getLocale().getCountry(), resp.getLocale().getScript()));
		System.out.println("resp.getProtocolVersion(): "+resp.getProtocolVersion());
		System.out.println("resp.getStatusLine(): "+resp.getStatusLine());
		System.out.println("resp.getStatusLine().getStatusCode(): "+resp.getStatusLine().getStatusCode());
		System.out.println("resp.getStatusLine().getReasonPhrase(): "+resp.getStatusLine().getReasonPhrase());
		System.out.println("resp.getStatusLine().getProtocolVersion(): "+resp.getStatusLine().getProtocolVersion());
		resp.setStatusCode(400);
		System.out.println("resp.getStatusLine().getStatusCode(): "+resp.getStatusLine().getStatusCode());
	}
}