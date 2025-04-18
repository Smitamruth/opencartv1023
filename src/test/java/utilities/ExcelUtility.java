package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	static String path;
	
	public ExcelUtility(String path) {
		this.path = path;
	}

	public static int getRowCount(String xlsheet) throws IOException {
		try {
			fi = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wb = new XSSFWorkbook(fi);
		int rowCount = wb.getSheet(xlsheet).getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}
	
	public static int getColCount(String xlsheet, int rowNum) throws IOException {
		try {
			fi = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wb = new XSSFWorkbook(fi);
		int colCount = wb.getSheet(xlsheet).getRow(rowNum).getLastCellNum();
		wb.close();
		fi.close();
		return colCount;
	}
	
	public static String getCellData(String xlsheet, int rowNum,  int colNum) throws IOException{
		try {
			fi = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(xlsheet);
		row = sh.getRow(rowNum);
		cell = row.getCell(colNum);
		
		String data;
		
		try {
		//data = cell.toString(); //to send the data in string format.
		
		//DataFormatter provided by Apache POI.
		DataFormatter format = new DataFormatter();
		data = format.formatCellValue(cell); // returns the formatted value of a cell as a string regardless of the cell type.
		}catch(Exception e) {
			data = "";
			System.out.println(e.getMessage());
		}
		
		wb.close();
		fi.close();
		return data;
	}
	
	public static void setCellData(String xlsheet, int rowNum,  int colNum, String data) throws IOException {
		File xfile = new File(path);
		if(!xfile.exists()) {				//if file not exists then create new file.
			wb = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			wb.write(fo);
		}
		
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		
		if(wb.getSheetIndex(xlsheet)==-1)	//if sheet not exists then create new sheet.
			wb.createSheet(xlsheet);
		sh = wb.getSheet(xlsheet);
		
		if(sh.getRow(rowNum)==null)			//if row not exists then create new row.
			sh.createRow(rowNum);
		row = sh.getRow(rowNum);
		
		cell = row.getCell(colNum);
		cell.setCellValue(data);
		
		fo = new FileOutputStream(path);
		wb.write(fo);
		
		wb.close();
		fi.close();
		fo.close();
	}
	
	public static void fillGreenColor(String xlsheet, int rowNum,  int colNum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(xlsheet);
		row = sh.getRow(rowNum);
		cell = row.getCell(colNum);
		
		style = wb.createCellStyle();
		
		style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		
		fo = new FileOutputStream(path);
		wb.write(fo);
		
		wb.close();
		fi.close();
		fo.close();
	}
	
	public static void fillRedColor(String xlsheet, int rowNum,  int colNum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(xlsheet);
		row = sh.getRow(rowNum);
		cell = row.getCell(colNum);
		
		style = wb.createCellStyle();
		
		style.setFillBackgroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		
		fo = new FileOutputStream(path);
		wb.write(fo);
		
		wb.close();
		fi.close();
		fo.close();
	}
	
}
