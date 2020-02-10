package com.api.rest.api.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Feature",
		glue = {"com.api.rest.api.stepDefinitions", "com.api.rest.api.util"} //Path of step definition file
		,plugin= {"pretty", "html:target/cucumber-html-report", "junit:junit_xml/cucumber.xml"} //To generate types of reporting
		,tags= {"~@Smoke", "@Regression", "~@ListDatatable"}
		,monochrome=true //To print output on console in readable format
		,dryRun=false //When set true, it will check mapping between feature file and step definition file and will not run any scenario actually
		,strict=true //When dryRun is false and strict is true, it will run all Scenarios and will check if ant step def is not defined in StepDefinition
		)
public class TestRunner {

}