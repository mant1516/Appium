package org.housejoy.common.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonTools {
	
	private static Properties properties;
	
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void WaitForSeconds(long seconds)
	{
		try
		{
			Thread.sleep(seconds* 1000L);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------
	public static Properties getProperties(String sPropertyFile)
	{
		try
		{
			Properties oProperties;
			InputStream oFile;
			oFile = new FileInputStream(sPropertyFile);
			oProperties= new Properties();
			oProperties.load(oFile);
			
			return oProperties;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static String getProperty(String key) throws FileNotFoundException, IOException {
		properties= new Properties();
		properties.load(new FileInputStream("config/AutomationInputs.properties"));
		return properties.getProperty(key);
	}
}
