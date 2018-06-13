package utilities;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.Test;

public class PropertyFile {
public static String getValuesForKey(String key)throws Exception{
	FileInputStream fis=new FileInputStream("C:/Users/Kambalapally/workspace/automation/Hybrid_FrameWork/Environment.properties");
	Properties p=new Properties();
	p.load(fis);
	System.out.println(p.getProperty("Browser"));
	return p.getProperty(key);
}
}
