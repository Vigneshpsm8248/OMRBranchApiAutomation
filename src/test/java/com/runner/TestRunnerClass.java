package com.runner;


import org.junit.runner.RunWith;

import com.base.BaseClass;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@CucumberOptions(tags =(""),dryRun =false,features ="src\\test\\resources\\Features",glue="com.stepdefnition",snippets=SnippetType.CAMELCASE,monochrome=true)
@RunWith(Cucumber.class)
public class TestRunnerClass extends BaseClass{
	
//	@AfterClass
//	public static void afterClass() throws FileNotFoundException, IOException {
//		Reporting.generateJVMReport(getProjectpath() + getPropertyFileValue("json"));
//	}

}


