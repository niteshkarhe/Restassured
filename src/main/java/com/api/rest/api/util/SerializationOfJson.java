package com.api.rest.api.util;

import com.api.rest.api.model.ApiAll;
import com.api.rest.api.xmlmodel.ApiXmlPost;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SerializationOfJson 
{
	public <T> ApiAll SerializeGson(String jsonString, Class<ApiAll> type)
	{
		//String newJsonStr = "["+jsonString+"]";
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		return gson.fromJson(jsonString, type);
	}
	
	public <T> ApiXmlPost SerializeXml(String jsonString, Class<ApiXmlPost> type)
	{
		//String newJsonStr = "["+jsonString+"]";
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		return gson.fromJson(jsonString, type);
	}
}