package com.api.rest.api.restassuredhelper;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class BaseClass extends RestAssured
{
	@BeforeSuite
	public static void setUp()
	{
		baseURI = "http://localhost";
		port = 8080;
		basePath = "laptop-bag/webapi/api";
	}
}
