package comComCastCRMGenericFileUtility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility {

	public String getDataFromPropertyFile(String key) throws Throwable {
		FileInputStream fis=new FileInputStream("./configAppData/Commondata.properties");
		Properties pro=new Properties();
		pro.load(fis);
		String data=pro.getProperty(key);		
		return data;
		
	}
	

}
