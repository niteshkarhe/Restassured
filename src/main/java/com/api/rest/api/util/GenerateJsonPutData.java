package com.api.rest.api.util;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GenerateJsonPutData {

	
	public static void main(String[] args) 
	{
		
	}
	
	private enum Brandname{
		Mac, Lenevo, HP, Sony, Google;
	}
	
	private static String getBrandname()
	{
		Brandname brands[]=Brandname.values();
		int index = new Random().nextInt(5);
		return  brands[index].toString();
	}
	
	private static String getLaptopname()
	{
		String[] lapnames = new String[] {"M120 Series", "LK Series", "HP Dault", "SO Keys", "Go Pie"};
		int index = new Random().nextInt(5);
		return lapnames[index];
	}
	
	private static String getFeatures(String key)
	{
		Map<String, List<String>> features = new HashMap<String, List<String>>();
		int index = new Random().nextInt(5);
		
		features.put("RAM", new ArrayList<String>(){{
		    add("14GB RAM");
		    add("9GB RAM");
		    add("10GB RAM");
		    add("4GB RAM");
		    add("9GB RAM");
		}});
		
		features.put("HardDrive", new ArrayList<String>() {{
			 add("1TB Hard Drive");
			 add("15TB Hard Drive");
			 add("5TB Hard Drive");
			 add("8TB Hard Drive");
			 add("7TB Hard Drive");
		}});
		
		features.put("Extra", new ArrayList<String>() {{
			 add("Touch Display");
			 add("LED Display");
			 add("Extra Wide Display");
			 add("Gorilla Glass Display");
			 add("Cristal Display");
		}});
		
		return features.get(key).get(index);
	}
	
	public void GenerateJsonData(String Id)
	{
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONObject lapDetails = new JSONObject();
		lapDetails.put("BrandName", getBrandname());
		lapDetails.put("Id", Id);
		lapDetails.put("LaptopName", getLaptopname());
		
		JSONArray featuresArr = new JSONArray();
		featuresArr.add(getFeatures("RAM"));
		featuresArr.add(getFeatures("HardDrive"));	
		featuresArr.add(getFeatures("Extra"));
		
		JSONObject features = new JSONObject();
		features.put("Feature", featuresArr);
		
		lapDetails.put("Features", features);
		
		try(FileWriter file = new FileWriter("D:\\NK\\API\\APIProjects\\RestApiHelper\\inputjson\\api-put.json"))
		{
			file.write(lapDetails.toJSONString());
			System.out.println("Json details: "+lapDetails.toJSONString());
			file.flush();
			file.close();
			System.out.println("The Json file api-put.json is written succefully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
