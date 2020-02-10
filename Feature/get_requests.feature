Feature: Get Request test cases 

@Smoke @Regression
Scenario: Simple Get Request to print message passed in url 
	Given "null" header is captured 
	When ping service is requested 
	Then verify status code '200' 
	Then verify status phrase "OK" 
	Then get the response from response body 
	
@Negative
Scenario: Negative scenario where invalid status code is passed 
	Given "null" header is captured 
	When ping service is requested 
	Then verify status code '400' 

@E2E
Scenario: Get request to retrieve all device details - Datatable Map example
	Given the data is posted to the server 
	Given headers are captured with map data 
		|Key|Value|
		|Accept|application/json|
	When all request is invoked 
	Then verify status code '200' 
	Then verify status phrase "OK" 
	Then this is useless step 

@Regression	
Scenario Outline: Get request to find given id 
	Given the data is posted to the server 
	Given "<key>" and "<value>" header are captured 
	When get request with id is invoked 
	Then verify status code '200' 
	Then verify status phrase "OK" 
	
	Examples: 
		|key|value|
		|Accept|application/json|

@ListDatatable	
Scenario: Get request to find given id - Datatable List example 
	Given the data is posted to the server 
	Given headers are captured with list data 
		|Accept|application/json|
	When all request is invoked 
	Then verify status code '200' 
	Then verify status phrase "OK"
	
@Smoke @RunPostService
Scenario: Get request to find given id - Hook Example 
	Given the post data is retrieved from hook
	Given headers are captured with list data 
		|Accept|application/json|
#	When all request is invoked 
#	Then verify status code '200' 
#	Then verify status phrase "OK"