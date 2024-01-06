package com.murlee.base;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;

import com.microsoft.playwright.PlaywrightException;

public class PlaywrightWrapper extends HTMLReporter {

	public boolean navigate(String url) {
		
		try {
		getPage().navigate(url);
		ReportStep("Successfully Launched Base URL", "pass");
		}
		catch (PlaywrightException e) {
			ReportStep("Navigatiion Failed "+e.getMessage(), "fail");
			return false;
		}
		return true;
	}
	
	public void maximize() {
		try {
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			getPage().setViewportSize(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight());
		} catch (HeadlessException e) {

		}
	}

	
	public boolean type(String locator, String value, String name) {
		try {
			//getPage().locator(locator).fill("");
			getPage().locator(locator).type(value);
			ReportStep("The text box :"+name+" is typed with value :"+value, "pass");
			return true;
		} catch (PlaywrightException e) {
			e.printStackTrace();
			ReportStep("PlaywrightException : \n" + e.getMessage(), "fail");
		}
		return false;
	}
	
	
	public boolean click(String locator, String value, String type) {
		try {
			getPage().locator(locator).scrollIntoViewIfNeeded();
			getPage().locator(locator).click();
			ReportStep("The text box :"+type+" is clicked with value :"+value, "pass");
			return true;
		} catch (PlaywrightException e) {
			e.printStackTrace();
			ReportStep("PlaywrightException : \n" + e.getMessage(), "fail");
		}
		return false;
	}
	
	
	public boolean verifyTitle(String title) {
		try {
			if(getPage().title().contains(title)) {
				ReportStep("The page with title :"+title+" displayed as expected", "pass");
				return true;
			}else
				ReportStep("The page with title :"+title+" did not match", "fail");

		} catch (PlaywrightException e) {
			ReportStep("PlaywrightException : \n" + e.getMessage(), "fail");
		}
		return false;

	}
	
	public boolean mouseOver(String locator, String name) {
		try {
			getPage().locator(locator).hover();
			ReportStep("The element :"+name+" is moused over successfully", "pass");
			return true;
		} catch (PlaywrightException e) {
			e.printStackTrace();
			ReportStep("PlaywrightException : \n" + e.getMessage(), "fail");
		}
		return false;
	}
	
}
