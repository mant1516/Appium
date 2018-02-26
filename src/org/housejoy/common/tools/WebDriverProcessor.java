package org.housejoy.common.tools;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverProcessor {

	public enum BrowserType {
        FIREFOX,
        CHROME,
        IE,
        SAFARI;
	}
	
	
	public static WebDriver oBrowser;
	private static boolean isBrowserLaunched = false;
	//private static String sPropertyInputFile="config/AutomationInputs.properties";
	private static Properties oProperties =CommonTools.getProperties("config/AutomationInputs.properties");
	private static long lngImplicitWait = 20;
	private static long lngPageLoadTimeout = 30;
	
	
	/*public WebDriverProcessor() {
			launchBrowser();				
	}*/

	//Parameterised the constructor with urls
/*	public WebDriverProcessor(ApplicationLink applicationLink) {
		launchBrowser(applicationLink);
	}*/

	//Parameterised the browser launch with different urls
	/*public void launchBrowser(ApplicationLink applicationLinks) {
		try {
			if (!isBrowserLaunched) {
				selectBrowser();
				isBrowserLaunched = true;
			}
			getApplicationURL(applicationLinks);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
/*	public void getApplicationURL(ApplicationLink applicationLinks) {
		try{
			//oProperties = 
			
			if(oProperties.containsKey("ImplicitWait"))
			{
				if(!oProperties.getProperty("ImplicitWait").isEmpty())
				{
					lngImplicitWait = Long.valueOf(oProperties.getProperty("ImplicitWait"));
				}
			}
			
			if(oProperties.containsKey("PageLoadTimeout"))
			{
				if(!oProperties.getProperty("PageLoadTimeout").isEmpty())
				{
					lngPageLoadTimeout = Long.valueOf(oProperties.getProperty("PageLoadTimeout"));
				}
			}
			oBrowser.manage().window().maximize();
			//Commented this line of code since we need cookie id for verificaion in DB
			//oBrowser.manage().deleteAllCookies();
			oBrowser.manage().timeouts().implicitlyWait(lngImplicitWait, TimeUnit.SECONDS);
			//oBrowser.manage().timeouts().pageLoadTimeout(lngPageLoadTimeout, TimeUnit.SECONDS);
			
			if (ApplicationLink.URL2.equals(applicationLinks)) {
				oBrowser.get(oProperties.getProperty("URL2"));
			}
			else {
				oBrowser.get(oProperties.getProperty("BaseUrl"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/

	//Default Url for the browser launch
	/*private void launchBrowser() {
		launchBrowser(ApplicationLink.BASEURL);
	}*/
	
	
	public static WebDriver selectBrowser() {
		
		String sIEDriverPath,sChromeDriverPath;
		
		sIEDriverPath=oProperties.getProperty("IEDriver64Bit").trim();
		sChromeDriverPath=oProperties.getProperty("ChromeDriver").trim();
		
		if(oProperties.getProperty("Browser").equals(BrowserType.CHROME.toString())){

			System.setProperty("webdriver.chrome.driver", sChromeDriverPath);
			oBrowser = new ChromeDriver();
			//oBrowser = new ChromeDriver(getNoProxyCapability());
		}
		
		
		else if(oProperties.getProperty("Browser").equals(BrowserType.IE.toString())){
			System.setProperty("webdriver.ie.driver",sIEDriverPath);
			//oBrowser= new InternetExplorerDriver();
			oBrowser= new InternetExplorerDriver(getCapability());
			
		}
		else{
			oBrowser= new FirefoxDriver();
		}
		
		return oBrowser;
	}	

	//Setting up desired capabilities of the browsers
	public static DesiredCapabilities getNoProxyCapability()
	{
		try
		{
			DesiredCapabilities oCapability;
			Proxy oProxy;
			
			oProxy =new Proxy();
			oProxy.setProxyType(ProxyType.DIRECT);
			
			oCapability = new DesiredCapabilities();
			oCapability.setCapability(CapabilityType.PROXY, oProxy);
			
			return oCapability;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public static DesiredCapabilities geProxyCapability()
	{
		try
		{
			DesiredCapabilities oCapability;
			Proxy oProxy;
			
			oProxy =new Proxy();
			oProxy.setProxyType(ProxyType.MANUAL);
			oProxy.setHttpProxy("ip_or_proxySysName:port");
			oProxy.setFtpProxy("ip_or_proxySysName:port");
			oProxy.setSslProxy("ip_or_proxySysName:port");
			
			oCapability = new DesiredCapabilities();
			oCapability.setCapability(CapabilityType.PROXY, oProxy);
			
			return oCapability;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public static DesiredCapabilities getCapability()
	{
		try
		{
			DesiredCapabilities oCapability;
						
			oCapability = new DesiredCapabilities();
			
			oCapability.setJavascriptEnabled(true);
			oCapability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			
			if (oProperties.getProperty("Browser").equals(BrowserType.FIREFOX)) //FF
			{
			//	oCapability.setCapability(FirefoxDriver.ACCEPT_UNTRUSTED_CERTIFICATES,true);
			//	oCapability.setCapability(FirefoxDriver.ASSUME_UNTRUSTED_ISSUER,true);
			//	oCapability.setCapability(FirefoxDriver.DEFAULT_ENABLE_NATIVE_EVENTS,true);
				
				FirefoxProfile oProfile=new FirefoxProfile();
				oProfile.setPreference("network.disable.ipc.security", true);
				oProfile.setAcceptUntrustedCertificates(true);
				oProfile.setAssumeUntrustedCertificateIssuer(false);
				
			}
			
			if (oProperties.getProperty("Browser").equals(BrowserType.IE)) //IE
			{
				oCapability.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
				oCapability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,"true");
								
			}
			
			if (oProperties.getProperty("Browser").equals(BrowserType.CHROME)) //Chrome
			{
				ChromeOptions oChromeOption;
				
			}
			return oCapability;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public void closeWebDriver() {

		try {
			if (oBrowser != null) {
				oBrowser.quit();
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
