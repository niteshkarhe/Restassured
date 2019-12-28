package com.api.rest.api.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.GsonAnnotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.RuleFactory;

import com.sun.codemodel.JCodeModel;

public class JsonSchemaToPOJO {

	public static void main(String[] args) 
	{
		String packageName="com.api.rest.api.model";  
        File inputJson= new File("D:\\NK\\API\\API Projects\\rest-api-helper\\inputjson\\api-all.json");  
        File outputPojoDirectory=new File("D:\\NK\\API\\API Projects\\rest-api-helper\\src\\main\\java");  
        outputPojoDirectory.mkdirs();  
        try {  
             new JsonSchemaToPOJO().convert2JSON(inputJson.toURI().toURL(), outputPojoDirectory, packageName, inputJson.getName().replace(".json", ""));  
        } catch (IOException e) {  
             // TODO Auto-generated catch block  
             System.out.println("Encountered issue while converting to pojo: "+e.getMessage());  
             e.printStackTrace();  
        }  
	}
	
	public void convert2JSON(URL inputJson, File outputPojoDirectory, String packageName, String className) throws IOException{  
        JCodeModel codeModel = new JCodeModel();  
        URL source = inputJson;  
        GenerationConfig config = new DefaultGenerationConfig() {  
            @Override  
            public boolean isGenerateBuilders() { // set config option by overriding method  
            return true;  
            }
            public SourceType getSourceType(){  
        return SourceType.JSON;  
      }  
            };  
        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new GsonAnnotator(config), new SchemaStore()), new SchemaGenerator());  
        mapper.generate(codeModel, className, packageName, source);  
        codeModel.build(outputPojoDirectory);  
   }
}