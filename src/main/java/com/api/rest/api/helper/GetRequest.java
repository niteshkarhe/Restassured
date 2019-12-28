package com.api.rest.api.helper;
import java.io.File;
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
	public static void main(String[] args) {
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
		System.out.println(response.toString());
	}
}