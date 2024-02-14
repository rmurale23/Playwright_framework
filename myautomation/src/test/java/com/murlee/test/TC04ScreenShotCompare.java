package com.murlee.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page.ScreenshotOptions;
import com.murlee.base.ProjectHooks;
import com.murlee.config.ConfigManager;
import com.murlee.page.base.LoginPage;
import com.murlee.page.base.ROCPSHooks;
import com.murlee.utility.CompareScreenShot;

public class TC04ScreenShotCompare extends ROCPSHooks {
	
	
	@BeforeTest
	public void setReportValues() {
		testName = "TC04 - ScreenShot Compare";
		testDescription = "Compare 2 screenshot and see if its same";
		authors = "Rahul Murlee";
		category = "Screen Comparison";
	}
		
	@Test
	public void logintoROCPS() throws InterruptedException, IOException {
		
		new LoginPage().doLogin();
		navigatetoScreen("Band");
		Path screenshotPath = Paths.get(automationPath+"\\"+"outputs"+"\\"+"sccompare"+"\\"+"source.png");
		Thread.sleep(ConfigManager.configuration().pauseSLow());
        getPage().screenshot(new ScreenshotOptions().setPath(screenshotPath));
        BufferedImage sourceImage = ImageIO.read(new File(screenshotPath.toString()));
        BufferedImage destImage   = ImageIO.read(new File(automationPath+"\\"+"outputs"+"\\"+"sccompare"+"\\"+"dest.png"));
        CompareScreenShot cs=new CompareScreenShot();
        if(!cs.compareTowImages(sourceImage, destImage))
        		{
        	ReportStep("Screenshot Comaprison matches "+"ROCPS", "pass");
        }
        else {
        	ReportStep("Screenshot Comaprison Failed "+"ROCPS", "fail");
        }
	}

	
	
	

}
