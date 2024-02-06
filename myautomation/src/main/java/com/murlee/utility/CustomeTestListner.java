package com.murlee.utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.murlee.base.HTMLReporter;

public class CustomeTestListner extends HTMLReporter implements ITestListener, IRetryAnalyzer  {
	
	int counter = 0;
	int retryLimit = 3;
	
	public void onTestStart(ITestResult result) {
        System.out.println("Test Started: "+result.getName());
        ReportStep("TEST Started"+result.getName(), "info");
    }
    public void onTestSuccess(ITestResult result) {
    	System.out.println("Test Passed: "+result.getName());
    	ReportStep("TEST Success"+result.getName(), "pass");
    }
    public void onTestFailure(ITestResult result) {
    	System.out.println("Test Failed: "+result.getName());
    	ReportStep("TEST Failed"+result.getName(), "fail");
    }
	@Override
	public boolean retry(ITestResult result) {
		
		if(counter < retryLimit)
		{
			counter++;
			return true;
		}
		return false;
	}
	

}
