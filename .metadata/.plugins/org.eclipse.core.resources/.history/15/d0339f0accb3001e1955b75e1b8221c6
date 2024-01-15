package com.murlee.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.murlee.config.ConfigManager;
import com.murlee.utility.MediaEntityBuilder;

public class HTMLReporter extends DriverFactory{
	
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> parentTest=new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentTest> test=new ThreadLocal<ExtentTest>();
	private static ThreadLocal<String> testCaseName=new ThreadLocal<String>();
	
	public String testName,testDescription,authors,category;
	private static final String repName="myReport.html";
	private static String pattern = "dd-MMM-yyyy HH-mm-ss";
	public static String folderName = "";
	public static final String automationPath = System.getProperty("user.dir");
	
	
	public String createDirectory(String dirName) {
		
		String date = new SimpleDateFormat(pattern).format(new Date());
		dirName = dirName+"\\" + date;	
		File file=new File(automationPath+"\\"+dirName);
		
		if (!file.exists()) {
			file.mkdir();
			
		}
		return dirName;
				
	}
	

	
	public synchronized void startReport() {
		folderName = createDirectory("reports");
		ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter(automationPath+"\\"+ folderName + "\\" + repName);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(!true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		if(ConfigManager.configuration().reportTheme().equalsIgnoreCase("dark"))
			htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(ConfigManager.configuration().reportTitle());
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(ConfigManager.configuration().reportName());
		htmlReporter.setAppendExisting(true);
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		
	}
	
	
	
public synchronized void startTest() {
	ExtentTest parent=extent.createTest(testName);
	parent.assignAuthor(authors);
	parent.assignCategory(category);
	parentTest.set(parent);
	testCaseName.set(testName);
}

public synchronized  void createNode() {
	ExtentTest child=parentTest.get().createNode(testCaseName.get());
	test.set(child);
}
	

public synchronized  void endResult() {
	extent.flush();
}


public void ReportStep(String desc, String status) {
	ReportStep(desc, status, true);
}

	
public void ReportStep(String description,String status,boolean bSnap) {
	
	synchronized(test) {
		
		// Start reporting the step and snapshot
		MediaEntityModelProvider img = null;
		if (bSnap && !(status.equalsIgnoreCase("INFO") || status.equalsIgnoreCase("skipped"))) {
			img = MediaEntityBuilder.createScreenCapture(new String(Base64.getEncoder().encode(getPage().screenshot())),"Snap",true).build();
		}
	
	if (status.equalsIgnoreCase("Pass")) {
		test.get().pass(description,img);
	}
	
	if (status.equalsIgnoreCase("Fail")) {
		test.get().fail(description,img);
	}
	
	if (status.equalsIgnoreCase("Warn")) {
		test.get().warning(description,img);
	}
	
	if (status.equalsIgnoreCase("Info")) {
		test.get().info(description,img);
	}
	}
}
	
public String getTestCaseName() {
	return testCaseName.get();
}

	
}
