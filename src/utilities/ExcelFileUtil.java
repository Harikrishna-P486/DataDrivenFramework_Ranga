package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	XSSFWorkbook wb;
	//constructor for creating excel path
    //ExcelFileUtil path = new ExcelFileUtil();
	public ExcelFileUtil(String Excelpath) throws Throwable 
	{
		FileInputStream fi = new FileInputStream(Excelpath);
		wb = new XSSFWorkbook(fi);
	}
	//method for counting number of rows in sheet
	public int rowCount(String sheetname) 
	{
	  return wb.getSheet(sheetname).getLastRowNum();
	}
	
	//Method for reading data from cells
	public String getCellData(String sheetname , int row ,int column)
	{
		String data ="";
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==CellType.NUMERIC) {
			int celldata =(int) wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data = String.valueOf(celldata);
		}else {
			data = wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	//Method for writing status into another wb
	public void setCellData(String sheetname , int row , int column ,String status ,String WriteExcel) throws Throwable {
		//get sheet from wb
		XSSFSheet ws = wb.getSheet(sheetname);
		//get row from sheet
		XSSFRow rowNum = ws.getRow(row);
		//create cell in row
		XSSFCell cell = rowNum.createCell(column);
		//write status into cell
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("pass")) {
			//create object for stype class
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
			
		}else if(status.equalsIgnoreCase("fail")) {
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}else if(status.equalsIgnoreCase("Blocked")) {
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(WriteExcel);
		wb.write(fo);
		
	}
	
	
	

}
