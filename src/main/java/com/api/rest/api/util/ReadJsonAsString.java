package com.api.rest.api.util;

import java.io.File;
import java.io.FileReader;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadJsonAsString 
{
	JSONParser parser = new JSONParser();
	public String getJsonString(Class<?> object, String... Id)
	{
		try
		{
			Object obj=null;
			if(object.isInstance(new GenerateJsonPostData()))
			{
				new GenerateJsonPostData().GenerateJsonData();
				obj= parser.parse(new FileReader("D:\\NK\\API\\APIProjects\\RestApiHelper\\inputjson\\api-post.json"));
			}
			else if(object.isInstance(new GenerateJsonPutData()))
			{
				new GenerateJsonPutData().GenerateJsonData(Id[0]);
				obj= parser.parse(new FileReader("D:\\NK\\API\\APIProjects\\RestApiHelper\\inputjson\\api-put.json"));
			}
			String json="";
			JSONObject jsonObject = (JSONObject) obj;
			json=jsonObject.toJSONString();
			return json;
		}
		catch(Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public String getJsonString(File file)
	{
		try
		{
			String json="";
			Object obj = parser.parse(new FileReader(file));
			JSONObject jsonObject = (JSONObject) obj;
			json=jsonObject.toJSONString();
			System.out.println(json);
			return json;
		}
		catch(Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public String getStringBetween(String input, String open, String close)
	{
		
		String output=StringUtils.substringBetween(input, open, close);
		return output;
	}
	
	public static void main(String[] args)
	{
		ReadJsonAsString o = new ReadJsonAsString();
		String laptopName=o.getStringBetween(o.getJsonString(new GenerateJsonPostData().getClass()), "\"LaptopName\":\"", "\"}");
		
		System.out.println(laptopName);
	}
}