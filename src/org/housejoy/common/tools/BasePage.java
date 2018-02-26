package org.housejoy.common.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by Kishlay on 08/23/2015.
 */
public class BasePage {

	public WebDriver driver;
	public WebDriverWait wait;

	private static long lngImplicitWait = 20;
	public String PAGE_URL;
	public String PAGE_TITLE;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 5);
	}

	public void loadPage() {
		driver.get(getPageUrl());
		driver.manage().window().maximize();
		// Commenting this line of code because we need cookie Id for further operaion
		// driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(lngImplicitWait, TimeUnit.SECONDS);
		// Assert.assertEquals(driver.getTitle(), getPageTitle());
	}

	public void loadPage(String URL) {
		driver.get(URL);
		driver.manage().window().maximize();
		// Commenting this line of code because we need cookie Id for further operaion
		// driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(lngImplicitWait, TimeUnit.SECONDS);
		// Assert.assertEquals(driver.getTitle(), getPageTitle());
	}

	public void loadMobilePage(String URL) {
		driver.get(URL);
		//driver.manage().window().maximize();
		// Commenting this line of code because we need cookie Id for further operaion
		// driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(lngImplicitWait, TimeUnit.SECONDS);
		// Assert.assertEquals(driver.getTitle(), getPageTitle());
	}
	public boolean verifyElementIsPresent(WebElement element) {
		try {
			element.getTagName();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

    public void clickElement(WebElement element){
        element.click();
    }

    public void setElementText(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
       // Assert.assertEquals(element.getAttribute("value"), text);
    }

    public void selectValueInDropdown(WebElement dropdown, int index) {
        Select select = new Select(dropdown);
        //select.selectByVisibleText(value);
        select.selectByIndex(index);
    }

    public void selectValueInDropdownByText(WebElement dropdown, String value) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(value);
        //select.selectByIndex(index);
    }

    public String getPageUrl(){
        return PAGE_URL;
    }

    public String getPageTitle(){
        return PAGE_TITLE;
    }

	// custom method for selecting the date
	public void SelectDate(WebElement scheduledDate,WebElement SelectNextMonth,WebElement SelectDate) {
		// Format to get the day of the date
		SimpleDateFormat format = new SimpleDateFormat("EE");
		// Date curDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.add(Calendar.DATE, 7);
		
		Date increasedDate = calendar.getTime();

		if (format.format(increasedDate).equals("Sun")) {
			calendar.add(Calendar.DATE, 1);
			increasedDate = calendar.getTime();
		}

		 

		// int Date=newDate.getDate();

		String sDate = Integer.toString(increasedDate.getDate());

		clickElement(scheduledDate);
		clickElement(SelectNextMonth);
		List<WebElement> rows = SelectDate.findElements(By.tagName("tr"));
		List<WebElement> columns = SelectDate.findElements(By.tagName("td"));

		for (WebElement cell : columns) {
			// Select next Day from today
			if (cell.getText().equals(sDate)) {
				cell.click();
				break;
			}
		}
	}
	
	// custom method for selecting the date
		public void SelectDateForMobile(WebElement scheduledDate) {
			// Format to get the day of the date
			SimpleDateFormat format = new SimpleDateFormat("EE");
			
			scheduledDate.sendKeys(Keys.ARROW_DOWN);
			
			// Date curDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			
			calendar.add(Calendar.DATE, 7);
			
			Date increasedDate = calendar.getTime();

			if (format.format(increasedDate).equals("Sun")) {
				scheduledDate.sendKeys(Keys.ARROW_RIGHT);
			}

			 		
		}
	
	public void takeScreenShot(String Filename) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// The below method will save the screen shot in screenshot folder with name "screenshot.png"
		try {
			FileUtils.copyFile(scrFile, new File(Filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getColumnValueFromDB(String sColumnName, String sCookieID) {
		// To run dataBase scripts for getting values from DB for TestCases
		DataBaseSeleniumUtil dbFixtureSupportImpl = new DataBaseSeleniumUtil();

		String singleValue = null;
		try {
			singleValue = dbFixtureSupportImpl.executeQueryTogetOrderID(sColumnName, sCookieID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return singleValue;
	}
    
	public void getAndValidateSemTableContents(String cookieID,Map<String,String> semTableValues) {
		DataBaseSeleniumUtil dbFixtureSupportImpl = new DataBaseSeleniumUtil();
		ArrayList<String> dbValues = dbFixtureSupportImpl.executeQueryTogetSemTableContents(cookieID);
		
		Assert.assertEquals(semTableValues.get("utm_source"), dbValues.get(0));
		Assert.assertEquals(semTableValues.get("utm_medium"), dbValues.get(1));
		Assert.assertEquals(semTableValues.get("utm_content"), dbValues.get(2));
		Assert.assertEquals(semTableValues.get("utm_campaign"), dbValues.get(3));
		Assert.assertEquals(semTableValues.get("account"), dbValues.get(4));
		Assert.assertEquals(semTableValues.get("campaign"), dbValues.get(5));
		Assert.assertEquals(semTableValues.get("adgroup"), dbValues.get(6));
		Assert.assertEquals(semTableValues.get("city"), dbValues.get(7));
		
		String leadStatusFromDB = getColumnValueFromDB( semTableValues.get("columnName"), cookieID);
		if (leadStatusFromDB.contentEquals("NEW")) {
			Assert.assertEquals(semTableValues.get("columnValue"), leadStatusFromDB);
		}
		else if(leadStatusFromDB.contentEquals("BLOCKED")) {
			Assert.assertEquals("BLOCKED", leadStatusFromDB);
		}
		else {
			Assert.assertEquals("Duplicate", leadStatusFromDB);
		}
		
	}
	
	public ArrayList<String> getThankUPageContents(String jobID) {
		DataBaseSeleniumUtil dbFixtureSupportImpl = new DataBaseSeleniumUtil();
		ArrayList<String> dbValues = dbFixtureSupportImpl.executeQueryTogetPageContents(jobID);
		return dbValues;
	}
	
	public String formatDBDateAndTimeSlot(String DateString,String Timeslot) throws ParseException{
		 DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d2 = df2.parse(DateString);
        SimpleDateFormat  format1= new SimpleDateFormat("dd-MMM-yyyy");
       // System.out.println((format1.format(d2)));
        String dbDateTimeSlot=format1.format(d2).toString()+" "+Timeslot;
        //System.out.println(dbDateTimeSlot);
        return dbDateTimeSlot;
	}
	
	public ArrayList<String> getLeadMasterTableContents(String cookieID) {
		DataBaseSeleniumUtil dbFixtureSupportImpl = new DataBaseSeleniumUtil();
		ArrayList<String> dbValues = dbFixtureSupportImpl.executeQueryTogetLeadMasterTableContents(cookieID);
		return dbValues;
	}
	
	public static DesiredCapabilities getDesiredCapabilitiesforMobile() throws ParseException, FileNotFoundException, IOException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("device", CommonTools.getProperty("device"));
		capabilities.setCapability("deviceName", CommonTools.getProperty("deviceName"));
		capabilities.setCapability("platformName", CommonTools.getProperty("platformName"));
		capabilities.setCapability(CapabilityType.BROWSER_NAME, CommonTools.getProperty("BROWSER_NAME")); //Name of mobile web browser to automate. Should be an empty string if automating an app instead.
		capabilities.setCapability(CapabilityType.VERSION, CommonTools.getProperty("Android_Version"));
		capabilities.setCapability("appPackage", CommonTools.getProperty("appPackage")); //For Chrome Browser
		capabilities.setCapability("appActivity", CommonTools.getProperty("appActivity")); //For Chrome Browser
       return capabilities;
	}
	
}
