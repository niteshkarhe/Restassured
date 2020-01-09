package com.api.rest.api.restassuredtest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.api.rest.api.helper.RestApiHelper;
import com.api.rest.api.model.RestResponse;
import com.api.rest.api.restassuredhelper.BaseClass;
import com.api.rest.api.util.GenerateJsonPostData;
import com.api.rest.api.util.ReadJsonAsString;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetMethodTest extends BaseClass
{
	private static String id;
	private static String brandname;
	private static String laptopname;
	private static String features;
	@BeforeClass
	public void testPostData()
	{
		try
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
			//id=new ReadJsonAsString().getStringBetween(body, "\"Id\":", ",").replace(" ", "").replace("\n", "").replace("\r", "");
			brandname=new ReadJsonAsString().getStringBetween(jsonStr, "\"BrandName\":\"", "\",").replace("\"", "");
			laptopname=new ReadJsonAsString().getStringBetween(jsonStr, "\"LaptopName\":\"", "\"}");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=1)
	public void testGet()
	{
		System.out.println(baseURI+port+basePath);
		System.out.println("### Simple Get Method ###");
		URI uri=null;
		try 
		{
			uri = new URI("/all");
			
			Response response = RestAssured
							.given()
							.accept(ContentType.JSON)
							.when()
							.get(uri);
			System.out.println(response.asString());
		} 
		catch (URISyntaxException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(priority=2)
	public void testStatusCode()
	{
		System.out.println("### Simple Get Method to validate Status Code ###");
		URI uri=null;
		try
		{
			uri = new URI("/all");
			int statusCode =RestAssured
					.given()
					.accept(ContentType.JSON)
					.when()
					.get(uri)
					.thenReturn()
					.statusCode();
			System.out.println("Status Code: "+statusCode);
			Assert.assertEquals(HttpStatus.SC_OK, statusCode);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=3)
	public void testStatusCodeWithThen()
	{
		System.out.println("### Simple Get Method to validate status code using then().assertThat().statusCode() ###");
		URI uri=null;
		try
		{
			uri = new URI("/all");
			RestAssured
					.given()
					.accept(ContentType.JSON)
					.when()
					.get(uri)
					.then()
					.assertThat()
					.statusCode(HttpStatus.SC_OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=4)
	public void testGetId()
	{
		System.out.println("### Get Method to find id: "+id+" ###");
		URI uri=null;
		try
		{
			uri = new URI("/find/"+id);
			RestAssured
					.given()
					.accept(ContentType.JSON)
					.when()
					.get(uri)
					.then()
					.assertThat()
					.statusCode(HttpStatus.SC_OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=5)
	public void testGetWithIdWithHeader() 
	{
		System.out.println("### Get Method to find id with headers() method: "+id+" ###");
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		try 
		{
			String body = given()
					.headers(headers)
					.when()
					.get(new URI("/find/"+id))
					.thenReturn()
					.body()
					.asString();
		} 
		catch (URISyntaxException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	public void testGetNonExistantId()
	{
		System.out.println("### Get Method to find id: 125. Assertion should fail.###");
		URI uri=null;
		try
		{
			uri = new URI("/find/125");
			RestAssured
					.given()
					.accept(ContentType.JSON)
					.when()
					.get(uri)
					.then()
					.assertThat()
					.statusCode(HttpStatus.SC_OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=6)
	public void testContent()
	{
		System.out.println("### Test content using Hamcrest package ###");
		given()
		.accept(ContentType.JSON)
		.when()
		.get("/find/"+id)
		.then()
		.body("BrandName", equalToIgnoringCase(brandname), "Id",equalTo(new Integer(id)), "LaptopName", equalToIgnoringCase(laptopname));
	}
	
	@Test(priority=7)
	public void testContentWithJsonPath()
	{
		System.out.println("### Test content using Json path ###");
		given()
		.accept(ContentType.JSON)
		.when()
		.get("/find/"+id)
		.then()
		.body("Features.Feature", hasSize(3));	
		/* hasItem(), hasItems(), hasSize()*/
	}
	
	@Test(priority=8)
	public void testXmlContent()
	{
		System.out.println("### Test Xml content ###");
		String xml = given()
				.accept(ContentType.XML)
				.when()
				.get("/find/"+id)
				.thenReturn()
				.asString();
		
		System.out.println("Xml Content: "+xml);
		/*To check content at path
		 Laptop.BrandName
		 Laptop.LaptopName
		 */
	}
	
	@Test(priority=9)
	public void testXmlPathContent()
	{
		System.out.println("### Test Xml content at specific path###");
		given()
				.accept(ContentType.XML)
				.when()
				.get("/find/"+id)
				.then()
				.assertThat()
				.body("Laptop.BrandName", equalTo(brandname), "Laptop.Id", equalTo(id), "Laptop.LaptopName", equalTo(laptopname));
				
		/*To check content at path
		 Laptop.BrandName
		 Laptop.LaptopName
		 */
	}
	
	@Test(priority=10)
	public void testXmlContentWithXmlPath()
	{
		System.out.println("### Test Xml content at Xml path###");
		String xmlResp = given()
				.accept(ContentType.XML)
				.when()
				.get("/find/"+id)
				.thenReturn()
				.asString();
		
		System.out.println("Xml Response: "+xmlResp);
		XmlPath xml = new XmlPath(xmlResp); 
		System.out.println("Laptop Id: "+xml.getInt("Laptop.Id"));
		System.out.println("Laptop BrandName: "+xml.getString("Laptop.BrandName"));
		System.out.println("Laptop LaptopName: "+xml.getString("Laptop.LaptopName"));
		
		/*Method to get data from list*/
		List<String> featureLst = xml.getList("Laptop.Features.Feature");
		System.out.println("Feature List: ");
		for(String str: featureLst)
		{
			System.out.println(str);
		}
		
		Assert.assertEquals(new Integer(id).intValue(), xml.getInt("Laptop.Id"));
		Assert.assertEquals(brandname, xml.getString("Laptop.BrandName"));
		Assert.assertEquals(laptopname, xml.getString("Laptop.LaptopName"));

		//xml.getList("Laptop.Features.Feature").contains("feature_name")
		
		/*To check content at path
		 Laptop.BrandName
		 Laptop.LaptopName*/
	}
	
	@Test(priority=11)
	public void testJsonContentWithJsonPath()
	{
		System.out.println("### Test Json content at Json path###");
		String jsnResp = given()
				.accept(ContentType.JSON)
				.when()
				.get("/find/"+id)
				.thenReturn()
				.asString();
		System.out.println("Json Response: "+jsnResp);
		
		JsonPath json = new JsonPath(jsnResp);
		System.out.println("Laptop Id: "+json.getInt("Id"));
		System.out.println("Laptop BrandName: "+json.getString("BrandName"));
		System.out.println("Laptop LaptopName: "+json.getString("LaptopName"));
		
		/*Method to get data from list*/
		List<String> featureLst = json.getList("Features.Feature");
		System.out.println("Feature List: ");
		for(String str: featureLst)
		{
			System.out.println(str);
		}
		
		Assert.assertEquals(new Integer(id).intValue(), json.getInt("Id"));
		Assert.assertEquals(brandname, json.getString("BrandName"));
		Assert.assertEquals(laptopname, json.getString("LaptopName"));
	}
}