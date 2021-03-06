package org.housejoy.pom.fitness;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.housejoy.common.tools.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class FitnessThankUPage extends BasePage {


	public FitnessThankUPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//div[starts-with(@class,'col-lg-11 col-md-12 col-sm-12 col-xs-12')]/div")
	public WebElement txtThankUHeader;
	
	@FindBy(xpath="//*[@id='view-job-table']/tbody/tr[1]/td[2]/label")
	public WebElement lblOrderID;
	
	@FindBy(xpath="//*[@id='view-job-table']/tbody/tr[2]/td[2]/label")
	public WebElement lblName;
	
	@FindBy(xpath="//*[@id='view-job-table']/tbody/tr[3]/td[2]/label")
	public WebElement lblAddress;
	
	@FindBy(xpath="//*[@id='view-job-table']/tbody/tr[4]/td[2]/label")
	public WebElement lblLocation;
	
	@FindBy(xpath="//*[@id='view-job-table']/tbody/tr[5]/td[2]/label")
	public WebElement lblServiceType;
	
	@FindBy(xpath="//*[@id='view-job-table']/tbody/tr[6]/td[2]")
	public WebElement lblSubService;
	
	@FindBy(xpath="//*[@id='view-job-table']/tbody/tr[7]/td[2]/label")
	public WebElement lblRequestedTime;
	
	@FindBy(xpath="//*[@id='view-job-table']/tbody/tr[8]/td[2]/label")
	public WebElement lblCouponCode;
	
	
	
	public void validateThankUHeader(String sHeadervalue) throws InterruptedException {
		//Thread.sleep(1000);
		Assert.assertEquals(true,txtThankUHeader.getText().equals(sHeadervalue));
		}
	
	public void validateOrderID(String sOrderID,String sCookieID) {
		//String sOrderIDvalue=lblOrderID.getText();
		//verifyOrderIDFromDB(sCookieID,sOrderIDvalue);
		Assert.assertEquals(true, lblOrderID.getText().equals(getColumnValueFromDB(sOrderID,sCookieID)));
    }
	
	public void validateName(String sName,String sCookieID) {
		//String sOrderIDvalue=lblName.getText();
		//verifyOrderIDFromDB(sCookieID,sOrderIDvalue);
		Assert.assertEquals(true, lblName.getText().equals(getColumnValueFromDB(sName,sCookieID)));
    }
	
	public void validatePageValues() throws Exception {
		String sOrderIDvalue=lblOrderID.getText();
		
		ArrayList<String> retrievedDBValues=getThankUPageContents(sOrderIDvalue);
		String sRequestedTimeFromDB=formatDBDateAndTimeSlot(retrievedDBValues.get(6), retrievedDBValues.get(8));
		
		Assert.assertEquals(sOrderIDvalue, retrievedDBValues.get(0));
		Assert.assertEquals(lblName.getText(), retrievedDBValues.get(1));
		Assert.assertEquals(lblAddress.getText(), retrievedDBValues.get(2));
		Assert.assertEquals(lblLocation.getText(), retrievedDBValues.get(3));
		Assert.assertEquals(lblServiceType.getText(), retrievedDBValues.get(4));
		Assert.assertEquals(true, lblSubService.getText().contains(retrievedDBValues.get(5)));
		Assert.assertEquals(lblRequestedTime.getText(), sRequestedTimeFromDB);
		if(retrievedDBValues.get(7)!=null)
		{
			Assert.assertEquals(lblCouponCode.getText(), retrievedDBValues.get(7));
		}
		
    }
		
	public void validateLeadMasterTableValues(String cookieID, String serviceType, String cityName, String leadStatus, String emailID, String mobileNumber, String userName) throws Exception {
		
		ArrayList<String> retrievedDBValues=getLeadMasterTableContents(cookieID);
		
		Assert.assertEquals(serviceType, retrievedDBValues.get(0));
		Assert.assertEquals(cityName, retrievedDBValues.get(1));
		Assert.assertEquals(lblOrderID.getText(), retrievedDBValues.get(2));
		Assert.assertEquals(leadStatus, retrievedDBValues.get(3));
		Assert.assertEquals(emailID, retrievedDBValues.get(4));
		Assert.assertEquals(mobileNumber, retrievedDBValues.get(5));
		Assert.assertEquals(userName, retrievedDBValues.get(6));
    }
		
}
