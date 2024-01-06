package com.murlee.page.base;

import java.nio.file.Paths;

import com.microsoft.playwright.BrowserContext;
import com.murlee.base.ProjectHooks;
import com.murlee.config.ConfigManager;

public class LoginPage extends ProjectHooks {
	
	public LoginPage typeUsername(String username) {	
		type("#username-input-area",username,"User Name");
		return this;
	}
	
	
	public LoginPage typePassword(String password) {	
		type("#password-input-area",password,"User Password");
		return this;
	}
	
	public LoginPage clickLogin(String logintxt) {	
		click("input#btn",logintxt,"button");
		return this;
	}
	
	public LoginPage verifyROCPSTitle(String pstitle) {	
		verifyTitle(pstitle);
		return this;
	}
	
	
	
	public LoginPage doLogin() throws InterruptedException {	
		if (getPage().title().contains("ROCPS")) {
			typeUsername(ConfigManager.configuration().getAppUsername());
			typePassword(ConfigManager.configuration().getAppUserPassword());
			clickLogin("Login");
			Thread.sleep(ConfigManager.configuration().pauseHigh());
			verifyROCPSTitle("ROC Partner Settlement - ROCPS Internal - Home Dashboard");
			
			
		}
		//getBrowserContext().storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("storage/login.json")));
		return this;
	}
	

	

}
