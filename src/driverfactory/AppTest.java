package driverfactory;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

public class AppTest {
@Test
public void start() throws Exception{
	try{DriverScript script=new DriverScript();
	script.startTest();
}catch (Exception e) {
   e.printStackTrace();	
}
}
}