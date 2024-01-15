package com.murlee.test;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.murlee.helpers.ROCScreenList;
import com.murlee.page.base.LoginPage;
import com.murlee.page.base.ROCPSHooks;

public class TC03NavigatoAllScreensinROCPS extends ROCPSHooks{
	
	
	@BeforeTest
	public void setReportValues() {
		testName = "TC03 - Navigate to All Screens";
		testDescription = "Navigate to All Screens in ROCPS";
		authors = "Rahul Murlee";
		category = "Login Management";
		datafilename=System.getProperty("user.dir")+"\\data\\ROCPS_ScreenList.xlsx";
	}
	
	
	@Test
	public void NavigateToROCPS() throws InterruptedException, IOException {
		
		new LoginPage().doLogin();
		List<ROCScreenList> myList=ROCScreenList.excelDataToListOfObjets_withApachePOI(datafilename);
		for(int i=0;i<5;i++) {
			navigatetoScreen(myList.get(i).getToolbaritem());
		}
		
		
		
	}
	

}
