package com.murlee.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.murlee.base.HTMLReporter;
import com.murlee.config.ConfigManager;
import com.murlee.helpers.FileHelper;
import com.murlee.helpers.SendEmailHelper;

public class EmailUtility extends HTMLReporter{
	
	final String hostName = ConfigManager.configuration().getEmailHost();
	final String password = ConfigManager.configuration().getEmailPassword();
	
	public void sendMail() throws Exception {

			
			String subject = "Automation Execution Report ";
		//	String fileName = automationPath+"\\"+ "reports" + "\\" + "myReport_22-May-2024 14-14-53.html";
			String fileName =FileHelper.getLastModifiedFile(automationPath+"\\"+ "reports");
			
			if (!hostName.equals("") && !password.equals("") && !ConfigManager.configuration().getToEmailUserName().equals("")) {
				StringBuffer content = emailContent();
			//	String[] toMailId = configProp.getMailToAddress().split(",");
			//	String[] ccMailId = configProp.getMailCCAddress().split(",");
				SendEmailHelper sendMailHelper = new SendEmailHelper();
				sendMailHelper.sendMail(hostName,password, ConfigManager.configuration().getToEmailUserName(),ConfigManager.configuration().getToEmailUserName(), subject, content, fileName);
			}
			else {
				ReportStep("Email sending failed.. please check configurations and settings", "fail");
			}

	}
	
	private StringBuffer emailContent() throws Exception {

			
		
			StringBuffer content = new StringBuffer();
			content.append("Hi,\n");
			content.append("\n");
			content.append("PFA Automation Execution Report as on " +  new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss").format(new Date()) + "\n");
			content.append("\n");
			content.append("Regards,\n");
			return content;
}
}
