package comComCastCRMGenericFileUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String getDataFromExcelFile(String sheetName,int rowNum,int cellNum) throws Throwable, IOException {
		FileInputStream fis=new FileInputStream("./TestData/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data=wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		wb.close();
		return data;		
	}
	
	public int getRowCount(String sheetName) throws Throwable, IOException {
		FileInputStream fis=new FileInputStream("./TestData/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount=wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowCount;	
	}
	
	public void setDataIntoExcel(String sheetName,int rowNum,int cellNum,String data) throws Throwable, IOException {
		FileInputStream fis=new FileInputStream("./TestData/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum);	
		FileOutputStream fos=new FileOutputStream("./TestData/testScriptData.xlsx");
		wb.write(fos);
		wb.close();
			
	}
	public int getCellCount(String sheetName) throws Throwable, IOException {
		FileInputStream fis=new FileInputStream("./TestData/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int cellCount=wb.getSheet(sheetName).getRow(0).getLastCellNum();
		wb.close();
		return cellCount;	
	}

}
