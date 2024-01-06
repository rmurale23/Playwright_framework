package com.murlee.base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.murlee.config.ConfigManager;
public class DriverFactory {
	
	private static final ThreadLocal<Playwright> playwright=new ThreadLocal<Playwright>();
	private static final ThreadLocal<Browser> driver=new ThreadLocal<Browser>();
	protected static final ThreadLocal<BrowserContext> context=new ThreadLocal<BrowserContext>();
	protected static final ThreadLocal<Page> page=new ThreadLocal<Page>();
	protected static final ThreadLocal<FrameLocator> frame=new ThreadLocal<FrameLocator>();
	
	
	public void setDriver(String browser , boolean headless) {
		
		playwright.set(Playwright.create());
		
		switch(browser) {
		case "chrome" :
			driver.set(getPlaywright().chromium().launch
					(new BrowserType.LaunchOptions().
							setChannel("chrome").
							setHeadless(headless).
							setSlowMo(ConfigManager.configuration().getSlowmoTimeout())));
			break;
		case "firefox" :
			driver.set(getPlaywright().chromium().launch
					(new BrowserType.LaunchOptions().
							setHeadless(ConfigManager.configuration().getHeadless()).
							setSlowMo(ConfigManager.configuration().getSlowmoTimeout())));
			break;	
			
		default :
			break;
		
		}
			
	}

public Playwright getPlaywright() {
	return playwright.get();
}
	
public BrowserContext getBrowserContext() {
	return context.get();
}

public Browser getDriver() {
	return driver.get();
}

public Page getPage() {
	return page.get();
}


public FrameLocator getFrame() {
	return frame.get();
}

}
