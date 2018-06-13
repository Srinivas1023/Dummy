package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtilities {
Workbook b;
       public ExcelUtilities() throws Exception{
    	   FileInputStream fis=new FileInputStream("C:/Users/Kambalapally/workspace/automation/Hybrid_FrameWork/TestInputs/InputSheet1.xlsx");
       b=WorkbookFactory.create(fis);
       }
       public int rowCount(String sheet){
    	   return b.getSheet(sheet).getLastRowNum();
       }
       public int colCount(String sheet,int row){
    	   return b.getSheet(sheet).getRow(row).getLastCellNum();
       }
       public String getData(String sheet,int row,int column){
    	   String data="";
    	   if(b.getSheet(sheet).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC){
    		   int numeric=(int)b.getSheet(sheet).getRow(row).getCell(column).getNumericCellValue();
                data=String.valueOf(numeric);    	   
    	   }else{
    		   data=b.getSheet(sheet).getRow(row).getCell(column).getStringCellValue();
    	   }
    	   return data;
       }
       public void setData(String sheet,int row,int column,String status) throws Exception{
    	   Sheet sh=b.getSheet(sheet);
    		Row rownum = sh.getRow(row);
    		Cell cell = rownum.createCell(column);
    		cell.setCellValue(status);
    		if(status.equalsIgnoreCase("Pass"))
    		{
    			CellStyle style = b.createCellStyle();
    			Font font = b.createFont();
    			//Apply color To The Text
    			font.setColor(IndexedColors.GREEN.getIndex());
    			//Apply Bold To The Text
    			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    			style.setFont(font);
    			rownum.getCell(column).setCellStyle(style);
    			
    		}
    		else if(status.equalsIgnoreCase("Fail"))
    		{
    			CellStyle style = b.createCellStyle();
    			Font font = b.createFont();
    			//Apply color To The Text
    			font.setColor(IndexedColors.RED.getIndex());
    			//Apply Bold To The Text
    			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    			style.setFont(font);
    			rownum.getCell(column).setCellStyle(style);
    		}
    		else
    			if(status.equalsIgnoreCase("Not Executed"))
    			{
    				CellStyle style = b.createCellStyle();
    				Font font = b.createFont();
    				//Apply color To The Text
    				font.setColor(IndexedColors.BLUE.getIndex());
    				//Apply Bold To The Text
    				font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    				style.setFont(font);
    				rownum.getCell(column).setCellStyle(style);
    			}
    		FileOutputStream fos = new FileOutputStream("C:/Users/Kambalapally/workspace/automation/Hybrid_FrameWork/TestInputs/InputSheet1.xlsx");
    		b.write(fos);
    		fos.close();
    		
    			
    	}
       }

