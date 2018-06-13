package commonfunctionallibrary;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.PropertyFile;

public class FunctionLibrary {

	public static WebDriver startBrowser(WebDriver driver) throws Exception{
		if(PropertyFile.getValuesForKey("Browser").equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver","F:/chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		return driver;
	}
	public static void openApplication(WebDriver driver) throws Exception{
		driver.get(PropertyFile.getValuesForKey("url"));
	}
	public static void typeAction(WebDriver driver,String locator_Type,String locator_Value,String data){
         if(locator_Type.equalsIgnoreCase("xpath")){
        	 driver.findElement(By.xpath(locator_Value)).clear();
        	 driver.findElement(By.xpath(locator_Value)).sendKeys(data);
         }
	}
	public static void clickAction(WebDriver driver,String locator_Type,String locator_Value){
		if(locator_Type.equalsIgnoreCase("xpath")){
			driver.findElement(By.xpath(locator_Value)).click();
		}
	}
	public static void wait(WebDriver driver){
		driver.manage().timeouts().implicitlyWait(1,TimeUnit.MINUTES);
	}
}
