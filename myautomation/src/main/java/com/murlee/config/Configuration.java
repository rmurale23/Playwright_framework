package com.murlee.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
	"system:properties",
	"classpath:local.properties",
	"classpath:app.properties"
})

public interface Configuration extends Config{
	
	@Key("browser")
	String getBrowser();
	

	@Key("base.url")
	String getUrl();

	@Key("app.timeout")
	int getTimeout();
	
	@Key("headless.mode")
	boolean getHeadless();
	
	@Key("enable.tracing")
	boolean enableTracing();
	
	@Key("report.Theme")
	String reportTheme();
	
	@Key("report.Title")
	String reportTitle();
	
	@Key("report.Name")
	String reportName();
	
	@Key("auto.login")
	String enableAutoLogin();
	
	@Key("app.username")
	String getAppUsername();
	
	@Key("app.password")
	String getAppUserPassword();
	
	@Key("slowmo.timeout")
	int getSlowmoTimeout();
	
	@Key("pause.slow")
	long pauseSLow();
	
	@Key("pause.medium")
	long pauseMed();
	
	@Key("pause.high")
	long pauseHigh();
	
}
