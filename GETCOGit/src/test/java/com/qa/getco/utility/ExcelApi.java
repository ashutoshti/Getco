package com.qa.getco.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelApi 
{
	public FileInputStream fis=null;
	public FileOutputStream fos=null;
	public XSSFWorkbook workbook=null;
	public XSSFSheet sheet=null;
	public XSSFRow row=null;
	public XSSFCell cell=null; //Column No
	String excelfilepath;
	XSSFFont font=null;
	XSSFCellStyle style=null;
	
	public ExcelApi(String excelfilepath) throws Exception
	{
		this.excelfilepath=excelfilepath;
		fis=new FileInputStream(excelfilepath);
		workbook =new XSSFWorkbook(fis);
		fis.close();
	}
	
	public String getCellData(String sheetName, int colNum, int rowNum)
	{
		try 
		{
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		cell=row.getCell(colNum);
				if (cell.getCellTypeEnum()==CellType.STRING) 
				{
					return cell.getStringCellValue();
				} 
				else if(cell.getCellTypeEnum()==CellType.NUMERIC||cell.getCellTypeEnum()==CellType.FORMULA)
				{
					String cellValue=String.valueOf(cell.getNumericCellValue());
					if(HSSFDateUtil.isCellDateFormatted(cell))
					{
						DateFormat dt=new SimpleDateFormat("dd/MM/yyy");
						Date date=cell.getDateCellValue();
						cellValue=dt.format(date);
					}
					return cellValue;
				}
				else if (cell.getCellTypeEnum()==CellType.BLANK) 
				return "";
				
				else
					return String.valueOf(cell.getBooleanCellValue());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "Data Not Found";
		}
	}

	public String getCellData(String sheetName, String colName, int rowNum)
	{
		try 
		{
			int colNum=-1;
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) 
			{
				if (row.getCell(i).getStringCellValue().trim().equals(colName)) 
				colNum=i;
			}
			
			row=sheet.getRow(rowNum-1);
			cell=row.getCell(colNum);

			if (cell.getCellTypeEnum()==CellType.STRING) 
			{
				return cell.getStringCellValue();
			} 
			else if(cell.getCellTypeEnum()==CellType.NUMERIC||cell.getCellTypeEnum()==CellType.FORMULA)
			{
				String cellValue=String.valueOf(cell.getNumericCellValue());
				if(HSSFDateUtil.isCellDateFormatted(cell))
				{
					DateFormat dt=new SimpleDateFormat("dd/MM/yyy");
					Date date=cell.getDateCellValue();
					cellValue=dt.format(date);
				}
				return cellValue;
			}
			else if (cell.getCellTypeEnum()==CellType.BLANK) 
			return "";
			
			else
				return String.valueOf(cell.getBooleanCellValue());
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Data Not Found");
		}
		return null;
	}
	
	public boolean setCellData(String sheetName, int colNum, int rowNum, String value)
	{
		try 
		{
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(rowNum);
			if(row==null)
				row=sheet.createRow(rowNum);
			
			cell=row.getCell(colNum);
			
			if(cell==null)
				cell=row.createCell(colNum);
			cell.setCellValue(value);

			fos=new FileOutputStream(excelfilepath);
			workbook.write(fos);
			fos.close();
			return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Data Not Found");
			return false;
		}
	
		
	}

	public boolean setCellData(String sheetName, String colName, int rowNum, String value)
	{
		try 
		{
			int colNum=-1;
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) 
			{
				if(row.getCell(i).getStringCellValue().trim().equals(colName))
				{
					colNum=i;
				}
			}
				row=sheet.getRow(rowNum-1);

				if(row==null)
					row=sheet.createRow(rowNum-1);

				cell=row.createCell(colNum);
					
				if(cell==null)
					cell=row.createCell(colNum);
				cell.setCellValue(value);
				
				fos=new FileOutputStream(excelfilepath);
				workbook.write(fos);
				fos.close();
				
			
			return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Data Not Found");
			return false;
		}
	
		
	}

	public int getRowCount(String sheetName)
	{
		sheet=workbook.getSheet(sheetName);
				int rowCount=sheet.getLastRowNum()+1;
		return rowCount;
	}

	public int getColumnCount(String sheetName)
	{
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(0);
		int columnCount=row.getLastCellNum();
		return columnCount;
	}

	public int getlastrowcount(String SheetName, int rownumber)
	{
		sheet=workbook.getSheet(SheetName);
		int rowlastcount=sheet.getRow(rownumber).getLastCellNum();
		return rowlastcount;
	}
}
