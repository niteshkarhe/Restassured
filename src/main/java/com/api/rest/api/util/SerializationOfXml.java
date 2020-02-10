package com.api.rest.api.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.api.rest.api.xmlmodel.ApiXmlPost;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class SerializationOfXml 
{
	public <T> ApiXmlPost DeserializeXml(String jsonString, Class<ApiXmlPost> type)
	{
		XmlMapper xml = new XmlMapper();
		xml.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		xml.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return xml.readValue(jsonString, ApiXmlPost.class);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public <T> ApiXmlPost DeserializeXml(Object xmlString, Class<ApiXmlPost> type)
	{
		String jsonString = new XmlToJson().convertXmlToJson((String)xmlString);
		System.out.println(jsonString);
		try(FileWriter file = new FileWriter("D:\\NK\\API\\APIProjects\\RestApiHelper\\inputjson\\api-xml-post.json"))
		{
			file.write(jsonString);
			file.flush();
			file.close();
			System.out.println("Json details: "+jsonString);
			System.out.println("The Json file api-xml-post.json is written succefully");
			String packageName="com.api.rest.api.xmlmodel";
			File inputJson = new File("D:\\NK\\API\\APIProjects\\RestApiHelper\\inputjson\\api-xml-post.json");
			File outputPojoDirectory=new File("D:\\NK\\API\\API Projects\\rest-api-helper\\src\\main\\java");
			outputPojoDirectory.mkdirs();
			new JsonSchemaToPOJO().convert2JSON(inputJson.toURI().toURL(), outputPojoDirectory, packageName, inputJson.getName().replace(".json", "")); 
			return new SerializationOfJson().SerializeXml(jsonString, type);
		}
		catch(Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}
}
