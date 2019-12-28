package com.api.rest.api.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class XmlToJson 
{
	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	
	public String convertXmlToJson(String xml)
	{
		 try 
		 {
		    JSONObject xmlJSONObj = XML.toJSONObject(xml);
		    String jsonPrettyPrintString = xmlJSONObj.toString();
		    return jsonPrettyPrintString;
	     } 
		 catch (JSONException je) 
		 {
	         throw new RuntimeException(je.getMessage(), je);
	     }
	}
	
	public static void main(String[] args)
	{
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" + 
		 		"<Laptop>\r\n" + 
		 		"    <BrandName>Lenevo</BrandName>\r\n" + 
		 		"    <Features>\r\n" + 
		 		"        <Feature>9GB RAM</Feature>\r\n" + 
		 		"        <Feature>20 inch screen</Feature>\r\n" + 
		 		"        <Feature>Bella Glass</Feature>\r\n" + 
		 		"    </Features>\r\n" + 
		 		"    <Id>128</Id>\r\n" + 
		 		"    <LaptopName> Series</LaptopName>\r\n" + 
		 		"</Laptop>";
		XmlToJson o = new XmlToJson();
		o.convertXmlToJson(xml);
	}
}
