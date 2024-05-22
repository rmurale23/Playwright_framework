package com.murlee.helpers;

import java.io.File;
import java.util.Properties;
import javax.mail.Transport;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.murlee.base.HTMLReporter;

public class SendEmailHelper  extends HTMLReporter{
	
	private Session getSession(String host,String password) throws Exception {
		try {
			Properties properties = System.getProperties();
			properties.setProperty("mail.transport.protocol", "smtp");
			 properties.setProperty("mail.smtp.host", host);
		    properties.setProperty("mail.smtp.port", "25");
		    properties.setProperty("mail.smtp.user", "support.alerts@subex.com");
		    properties.setProperty("mail.smtp.password", password);
		    properties.setProperty("mail.smtp.connectiontimeout", "30000");
		    Session session = Session.getInstance(properties);	
		   return session;
		} catch (Exception e) {
			ReportStep("Email sending failed.. pleae check the configuration"+e.getMessage(), "fail");
			throw e;
		}
	}
	
	public void sendMail(String host, String password,String toMailId, String ccMailId, String subject, StringBuffer content, String fileName) throws Exception {
		try {
			
				Session session = getSession(host,password);
			    
			    MimeMessage message = new MimeMessage(session);  
		        message.setFrom(new InternetAddress("support.alerts@subex.com"));
		        	message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMailId));
		        	message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccMailId));
		        message.setSubject(subject);
		        
		        BodyPart messageBodyPart1 = new MimeBodyPart();  
		        messageBodyPart1.setText(content.toString());   
		        MimeBodyPart messageBodyPart2 = new MimeBodyPart();
		        DataSource source = new FileDataSource(fileName); 
		        messageBodyPart2.setDataHandler(new DataHandler(source));
		        String filename = new File(fileName).getName();
		        messageBodyPart2.setFileName(filename);
		    
		        Multipart multipart = new MimeMultipart();  
		        multipart.addBodyPart(messageBodyPart1);
		        multipart.addBodyPart(messageBodyPart2);
		        
		        message.setContent(multipart);
		        try {
					Transport.send(message);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
		        ReportStep("Email Sent successfully", "pass");
			}
		 catch (MessagingException me) {
			 ReportStep("Email sending failed.. pleae check the configuration"+me.getMessage(), "fail");
			throw me;
		} catch (Exception e) {
			throw e;
		}
		
		
	}

}
