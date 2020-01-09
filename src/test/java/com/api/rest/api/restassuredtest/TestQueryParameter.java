package com.api.rest.api.restassuredtest;

import static io.restassured.RestAssured.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.api.rest.api.restassuredhelper.BaseClass;
import com.api.rest.api.util.GenerateJsonPostData;
import com.api.rest.api.util.ReadJsonAsString;

import io.restassured.http.ContentType;

public class TestQueryParameter extends BaseClass
{
	private static String id;
	private static String laptopname;
	@Test
	public void testQueryPara()
	{
		System.out.println("### Running Post data method ###");
		String jsonStr = new ReadJsonAsString().getJsonString(new GenerateJsonPostData().getClass());
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		//RestApiHelper.performPostRequest("/add", jsonStr, null, headers); 
		
		String body = given()
		.accept(ContentType.JSON)
		.with()
		.contentType(ContentType.JSON)
		.and()
		.body(jsonStr)
		.when()
		.post("/add").body().asString();
		
		id=new ReadJsonAsString().getStringBetween(jsonStr, "\"Id\":", ",");
		laptopname=new ReadJsonAsString().getStringBetween(jsonStr, "\"LaptopName\":\"", "\"}");
		
		String response = given()
		.accept(ContentType.JSON)
		.param("id", id)
		.param("laptopName", laptopname)
		.when()
		.get("/query")
		.thenReturn()
		.asString();
		
		System.out.println("Query parameter GET: "+response);
	}
}
