package com.murlee.helpers;

import java.io.File;

import com.murlee.base.HTMLReporter;

public class FileHelper extends HTMLReporter {
	

	public static String getLastModifiedFile(String directoryName) throws Exception {
		
		File dir = new File(directoryName);
		File[] files = dir.listFiles();
		File lastModifiedFile = null;
		String modifiedFile = null;
		
		if (files != null && files.length > 0 && files[0] != null) {
			lastModifiedFile = files[0];
			for (int i = 1; i < files.length; i++) {
				if (lastModifiedFile.lastModified() < files[i].lastModified() && (files[i].isFile()))
					lastModifiedFile = files[i];
			}
		}
		if (lastModifiedFile != null)
			modifiedFile = lastModifiedFile.toString();
		

		return modifiedFile;
			
	}
	
}
