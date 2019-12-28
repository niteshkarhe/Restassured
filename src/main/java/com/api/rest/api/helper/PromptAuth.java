package com.api.rest.api.helper;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;

public class PromptAuth {

	public static void main(String[] args) 
	{
		
	}
	
	public HttpClientContext setCredentials()
	{
		CredentialsProvider provider=new BasicCredentialsProvider();
		provider.setCredentials(AuthScope.ANY, new  UsernamePasswordCredentials("admin", "welcome"));
		HttpClientContext context=HttpClientContext.create();
		context.setCredentialsProvider(provider);
		return context;
	}

}
