package com.api.rest.api.stepDefinitions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.api.rest.api.helper.PostRequest;
import com.api.rest.api.helper.RestApiHelper;
import com.api.rest.api.model.RestResponse;
import com.api.rest.api.util.CucumberHooks;
import com.api.rest.api.util.GenerateJsonPostData;
import com.api.rest.api.util.ReadJsonAsString;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetRequestSteps {
	
	private static Map<String,String> headers;
	private static RestResponse response;
	private static String id;
	
	/* Regular exceptions
	 * 1. \"([^\"]*)\"
	 * 2. \"(.*)\"
	 */
	
	@Given("^\"([^\"]*)\" header is captured$")
	public void header_is_captured(String header) throws Throwable {
	    headers= new HashMap<String,String>();
	}

	@When("^ping service is requested$")
	public void ping_service_is_requested() throws Throwable {
		String url="http://localhost:8080/laptop-bag/webapi/api/ping/hello";
		response= RestApiHelper.performGetRequest(url, headers);
	}

	@Then("^verify status code '(\\d+)'$")
	public void verify_status_code_(int code) throws Throwable {
		Integer statCode = new Integer(response.getCloseableResponse().getStatusLine().getStatusCode());
		Assert.assertEquals(statCode, new Integer(code));
	}

	@Then("^verify status phrase \"([^\"]*)\"$")
	public void verify_status_phrase(String phrase) throws Throwable {
	    String statPhrase = response.getCloseableResponse().getStatusLine().getReasonPhrase();
	    Assert.assertEquals(statPhrase.toString(), phrase);
	}
	
	@Then("^get the response from response body$")
	public void get_response_from_response_body()
	{
		String responseBody = response.getResponseBody();
		System.out.println(responseBody);
		Assert.assertEquals("Hi! hello", responseBody);
	}
	
	@Given("^the data is posted to the server$")
	public void the_data_is_posted_to_the_server() {
		String postJsonStr = new ReadJsonAsString().getJsonString(new GenerateJsonPostData().getClass());
		id = new PostRequest().PostRequestMethod(postJsonStr);
	}
	
	@Given("^the post data is retrieved from hook$")
	public void the_post_data_retrived_from_hooks() {
		id=CucumberHooks.getId();
	}

	@When("^all request is invoked$")
	public void all_request_is_invoked(){
		String url="http://localhost:8080/laptop-bag/webapi/api/all";
		response= RestApiHelper.performGetRequest(url, headers);
	}
	
	@Given("^headers are captured with map data$")
	public void headers_are_captured(DataTable headerData)
	{
		List<Map<String, String>> data = headerData.asMaps(String.class, String.class);
		for(Map<String, String> dt: data)
		{
			headers.put(dt.get("Key"), dt.get("Value"));
		}
		System.out.println(headers);
	}
	
	@Given("^headers are captured with list data$")
	public void headers_are_captured_with_list(DataTable headerData)
	{
		List<List<String>> data = headerData.raw();
		headers = new HashMap<String,String>();
		for(List<String> dt: data)
		{
			headers.put(dt.get(0), dt.get(1));
		}
	}
	
	@Given("^\"([^\"]*)\" and \"([^\"]*)\" header are captured$")
	public void and_header_are_captured(String key, String value) throws Throwable {
		headers = new HashMap<String,String>();
		headers.put(key, value);
	}
	
	@When("^get request with id is invoked$")
	public void get_request_with_id_is_invoked(){
		String url="http://localhost:8080/laptop-bag/webapi/api/find/"+id;
		response= RestApiHelper.performGetRequest(url, headers);
	}
}