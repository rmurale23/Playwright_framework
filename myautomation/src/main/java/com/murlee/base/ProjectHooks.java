package com.murlee.base;

import java.nio.file.Paths;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.Tracing;
import com.murlee.config.ConfigManager;
import com.murlee.utility.EmailUtility;

public class ProjectHooks extends PlaywrightWrapper{
	
	public static String tracesFolderName = "";

	@BeforeSuite 
	public void initSuite() {
		
		startReport();
		tracesFolderName = createDirectory("traces");
	}
	
	
	@BeforeClass
	public void startTestCaseReporting() {
		startTest();
		
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public void init() {
		
		try {
		setDriver(ConfigManager.configuration().getBrowser(),false);
		createNode();
		
		NewContextOptions newContext=new Browser.NewContextOptions().setIgnoreHTTPSErrors(true);
		
		//Auto Login if enabled
		if ( ConfigManager.configuration().enableAutoLogin().equals("enabled")) {
		newContext.setStorageStatePath(Paths.get("storage/login.json"));
		}
		 
		context.set(getDriver().newContext(newContext));
		
		page.set(getBrowserContext().newPage());
	
		
		getPage().setDefaultTimeout(ConfigManager.configuration().getTimeout());
		
		
		/*
		 * if (ConfigManager.configuration().enableTracing()) {
		 * getBrowserContext().tracing().start(new
		 * Tracing.StartOptions().setName(testName).setSnapshots(true).setTitle(testName
		 * )); }
		 */
		
		maximize();

		navigate(ConfigManager.configuration().getUrl());
		
		}
		catch (Exception e) {
			ReportStep("The browser and/or the URL could not be loaded as expected - "+e.getMessage(),"fail");
		}
		
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		try {
	//	getBrowserContext().tracing().stop(new Tracing.StopOptions().setPath(Paths.get(tracesFolderName+"/"+testName+".zip")));
		getPage().close();
		getBrowserContext().close();
		getPlaywright().close();
		endResult();		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@AfterSuite(alwaysRun = true)
	public void afterSuite() throws Exception {
		if (ConfigManager.configuration().getEmailReqFlag().equalsIgnoreCase("y")) {
			EmailUtility emailUntilityObj=new EmailUtility();
			emailUntilityObj.sendMail();
		}
	}
	

}
