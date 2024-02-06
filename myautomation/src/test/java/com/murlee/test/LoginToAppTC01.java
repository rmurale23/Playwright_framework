package com.murlee.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.murlee.base.ProjectHooks;
import com.murlee.config.ConfigManager;
import com.murlee.page.base.LoginPage;

public class LoginToAppTC01 extends ProjectHooks {
	
	
	@BeforeTest
	public void setReportValues() {
		testName = "TC01 - Login to ROCPS Application";
		testDescription = "Login to ROCPS with Root credentials";
		authors = "Rahul Murlee";
		category = "Login Management";
	}
		
	@Test(retryAnalyzer = com.murlee.utility.CustomeTestListner.class)
	public void logintoROCPS() throws InterruptedException {
		
		new LoginPage().doLogin();
		
	
	}
	
	
	

}
