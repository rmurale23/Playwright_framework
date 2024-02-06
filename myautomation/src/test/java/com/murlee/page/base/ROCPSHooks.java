package com.murlee.page.base;

import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.PlaywrightException;
import com.murlee.base.ProjectHooks;
import com.murlee.config.ConfigManager;

public class ROCPSHooks extends ProjectHooks {
	
	
	public  void navigatetoScreen(String screenName) throws InterruptedException {
		
		try {
			
			String frmtdScreenName=null;
			click("#navigationLabel","Navigation Search","NA");
			Thread.sleep(ConfigManager.configuration().pauseSLow());
			type("[placeholder=\"Type to search\"]",screenName, "Event Statistics Screen");
			Thread.sleep(ConfigManager.configuration().pauseSLow());
			getPage().keyboard().press("ArrowDown+Enter");
			Thread.sleep(ConfigManager.configuration().pauseSLow());
			
			if (getPage().locator("text=OK").isVisible())
			{
				getPage().locator("text=OK").click();
			}
						
			if (screenName.length()>19) {
				frmtdScreenName=screenName.substring(0, 19);
			}
			else {
				frmtdScreenName=screenName;
			}
			if (getPage().locator("#filterpanel-header-label").innerText().contains(frmtdScreenName)) {
				
				ReportStep("Successfully launched the screen -"+screenName, "pass");
			}
			else {
			ReportStep("Unable to Open the screen -"+screenName, "fail");
			}
			
			
		}
		catch (PlaywrightException e) {
			ReportStep("PlaywrightException : \n" + e.getMessage(), "fail");
		}
		
	}
	
	
	
	

}
