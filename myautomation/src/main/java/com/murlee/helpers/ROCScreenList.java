package com.murlee.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.murlee.utility.ReadExcelFile;

public class ROCScreenList {
	
	String toolbar,toolbaritem;

	
	
	public static List<ROCScreenList> excelDataToListOfObjets_withApachePOI(String fileLocation)
			  throws IOException {
				XSSFSheet mysheet=ReadExcelFile.initialise(fileLocation);
			    List<ROCScreenList> rocpsdata = new ArrayList<ROCScreenList>();
			    DataFormatter dataFormatter = new DataFormatter();
			    for (int n = 1; n < mysheet.getPhysicalNumberOfRows(); n++) {
			        XSSFRow row = mysheet.getRow(n);
			        ROCScreenList rocpsscreeenlist = new ROCScreenList();
			        int i = row.getFirstCellNum();

			        rocpsscreeenlist.setToolbar(dataFormatter.formatCellValue(row.getCell(i)));
			        rocpsscreeenlist.setToolbaritem(dataFormatter.formatCellValue(row.getCell(++i)));
			       
			        rocpsdata.add(rocpsscreeenlist);
			    }
			    return rocpsdata;
			}
	
	
	public String getToolbar() {
		return toolbar;
	}

	public void setToolbar(String toolbar) {
		this.toolbar = toolbar;
	}

	public String getToolbaritem() {
		return toolbaritem;
	}

	public void setToolbaritem(String toolbaritem) {
		this.toolbaritem = toolbaritem;
	}
	
	
}
