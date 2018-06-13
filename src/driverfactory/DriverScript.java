package driverfactory;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonfunctionallibrary.FunctionLibrary;
import utilities.ExcelUtilities;

public class DriverScript {
WebDriver driver;
ExtentReports report;
ExtentTest test;
ExcelUtilities excel;
public void startTest() throws Exception{
        excel=new ExcelUtilities();
for(int i=1;i<=excel.rowCount("MasterTestCases");i++){
	String ModuleStatus = "";

	if(excel.getData("MasterTestCases",i,2).equalsIgnoreCase("Y")){
		String Module_name=excel.getData("MasterTestCases",i,1);
		report=new ExtentReports("./Reports/"+Module_name+".html");
		test=report.startTest(Module_name);
		for(int j=1;j<=excel.rowCount(Module_name);j++){
			String Description=excel.getData(Module_name, j,0);
			String object_Type=excel.getData(Module_name, j,1);
			String locator_Type=excel.getData(Module_name, j,2);
			String locator_Value=excel.getData(Module_name, j,3);
			String testData=excel.getData(Module_name, j, 4);
			try{
				if(object_Type.equalsIgnoreCase("startBrowser")){
				driver=FunctionLibrary.startBrowser(driver);
				test.log(LogStatus.INFO,Description);
			}else if(object_Type.equalsIgnoreCase("openApplication")){
				FunctionLibrary.openApplication(driver);
				test.log(LogStatus.INFO,Description);
			}else if(object_Type.equalsIgnoreCase("typeAction")){
				FunctionLibrary.typeAction(driver, locator_Type, locator_Value, testData);
				test.log(LogStatus.INFO,Description);
			}else if(object_Type.equalsIgnoreCase("clickAction")){
				FunctionLibrary.clickAction(driver, locator_Type, locator_Value);
				test.log(LogStatus.INFO,Description);
			}
				excel.setData(Module_name,j,5,"Pass");
				ModuleStatus = "true";
             test.log(LogStatus.PASS,Description+"PASS");
             File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	           FileUtils.copyFile(scrFile,new File("./ScreenShots/image.png"));
	           String path=test.addScreenCapture("./ScreenShots/image.png");			}catch(AssertionError e){
				   test.log(LogStatus.INFO,path);
	        	   excel.setData(Module_name,j,5,"Fail");
				ModuleStatus = "false";
             test.log(LogStatus.FAIL,Description+"Fail");
			}finally{
				report.endTest(test);
				report.flush();
			}
	}
}
}
}
}



