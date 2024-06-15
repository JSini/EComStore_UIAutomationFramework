package com.ecom.utilities;

import java.io.FileInputStream;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	public static FileInputStream fis;
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	
	public static String getCellValue(String fileName, String sheetName, int rowNum, int colNum) {
		
		try {
			fis = new FileInputStream(fileName);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(sheetName);
			cell = wb.getSheet(sheetName).getRow(rowNum).getCell(colNum);
			
			wb.close();
			
			return cell.getStringCellValue();
		
		} catch (Exception e) {
			
			return "";
		}
	}
	
	
	public static int getRowCount(String fileName, String sheetName) {
		
		try {
			fis = new FileInputStream(fileName);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(sheetName);
			
			int totalRows = sheet.getLastRowNum()+1;
			
			wb.close();
			return totalRows;
			
		} catch (Exception e) {
			return 0;
		}
		
	}
	
	public static int getCellCount(String fileName, String sheetName) {
		
		try {
			fis = new FileInputStream(fileName);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(sheetName);
			
			int totalCols = sheet.getRow(0).getLastCellNum();
			
			wb.close();
			return totalCols;
			
		} catch (Exception e) {
			return 0;
		}
		
	}
	

}
