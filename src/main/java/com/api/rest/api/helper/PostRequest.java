package com.api.rest.api.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.entity.ContentType;

import com.api.rest.api.model.RestResponse;
import com.api.rest.api.util.GenerateJsonPostData;
import com.api.rest.api.util.ReadJsonAsString;

public class PostRequest 
{
	public static void main(String[] args)
	{
		String jsonStr = new ReadJsonAsString().getJsonString(new GenerateJsonPostData().getClass());
		PostRequest o = new PostRequest();
		o.PostRequestMethod(jsonStr);
		/*HttpPost post = new HttpPost("http://localhost:8080/laptop-bag/webapi/api/add");
		try(CloseableHttpClient client=HttpClientBuilder.create().build())
		{
			post.addHeader("Content-Type", "application/json");
			post.addHeader("Accept", "application/json");
			StringEntity data = new StringEntity(jsonStr, ContentType.APPLICATION_JSON);
			post.setEntity(data);
			File file = new File("D:\\NK\\API\\API Projects\\rest-api-helper\\inputjson\\api-post.json");
			FileEntity data = new FileEntity(file, ContentType.APPLICATION_JSON);
			post.setEntity(data);
			CloseableHttpResponse response = client.execute(post);
			ResponseHandler<String> handler = new BasicResponseHandler() ;
			RestResponse restResponse=new RestResponse(response.getStatusLine().getStatusCode(), handler.handleResponse(response));
			System.out.println(restResponse.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
	}
	
	public String PostRequestMethod(String jsonStr)
	{
		System.out.println("### Post Request Starts ###");
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		RestResponse response = RestApiHelper.performPostRequest("http://localhost:8080/laptop-bag/webapi/api/add", jsonStr, ContentType.APPLICATION_JSON, headers);
		System.out.println("Status Code: "+response.getStatusCode());
		System.out.println("Response body: "+response.getResponseBody());
		String id=new ReadJsonAsString().getStringBetween(jsonStr, "\"Id\":", ",");
		System.out.println("### Post Request Ends ###");
		return id;
	}
}
