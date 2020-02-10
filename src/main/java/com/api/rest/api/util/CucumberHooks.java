package com.api.rest.api.util;

import com.api.rest.api.helper.PostRequest;

import cucumber.api.java.Before;

public class CucumberHooks 
{
	private static String id;
	public void setId(String id_fromPost)
	{
		id=id_fromPost;
	}
	
	public static String getId()
	{
		return id;
	}
	
	@Before(order=1)
	public void GlobalFirstBefore()
	{
		System.out.println("Global First @Before");
	}
	
	@Before(order=2)
	public void GlobalSecondBefore()
	{
		System.out.println("Global Second @Before");
	}
	
	@Before("@RunPostService")
	public void PostData()
	{
		String postJsonStr = new ReadJsonAsString().getJsonString(new GenerateJsonPostData().getClass());
		setId(new PostRequest().PostRequestMethod(postJsonStr));
		System.out.println("Before hook is run");
	}
}
