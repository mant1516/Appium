package org.housejoy.pom.appliances;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.housejoy.common.tools.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AppliancesLeadBookingPage extends BasePage {


	public AppliancesLeadBookingPage(WebDriver driver) {
		 super(driver);
	}
	
	@FindBy(xpath="//div[starts-with(@class,'col-lg-7 col-md-7 text-center tnk-pg')]")
	public WebElement txtThankUHeader;
	
	@FindBy(xpath="//button[starts-with(@class,'multiselect dropdown-toggle btn btn-default')]")
	public WebElement btnSelectSubService;
	
	@FindBy(xpath="//input[starts-with(@value,'1121')]")
	public WebElement chkBoxSelect;
	
	@FindBy(id="scheduledDate")
	public WebElement txtDatePicker;
	
	@FindBy(xpath="//div[starts-with(@class,'datepicker-days')]/table/thead/tr[1]/th[3]")
	public WebElement oNextMonth;
	
	@FindBy(xpath="//div[starts-with(@class,'datepicker-days')]")
	public WebElement txtSelectDate;
	
	@FindBy(id="scheduledTime")
	public WebElement selTime;
	
	@FindBy(id="geocomplete")
	public WebElement textLocation;
	
	@FindBy(id="address1")
	public WebElement textAddress;
	
	@FindBy(id="emailId")
	public WebElement textEmailId;
	
	@FindBy(id="pinCode")
	public WebElement textPincode;
	
	@FindBy(id="comments1")
	public WebElement textSplInstructions;
	
	@FindBy(id="couponCode")
	public WebElement textCouponCode;
	
	@FindBy(xpath="//button[starts-with(@class,'btn btn-success btn-block')]")
	public WebElement btnCouponApply;
	
	@FindBy(id="successMsg")
	public WebElement lblCouponText;
	
	@FindBy(xpath="//button[starts-with(@class,'btn btn-big btn-innr btn-block')]")
	public WebElement btnBookNowSubmit;
	
	
	
	public void validateThankUHeader(String sHeadervalue) throws InterruptedException {
		//Thread.sleep(1000);
		Assert.assertEquals(true, txtThankUHeader.getText().equals(sHeadervalue));
	}
	
		
	//Custom method for Selecting SubService		
	public void selectSubServiceButton() {
        //selectValueInDropdown(selService, value);
		clickElement(btnSelectSubService);
		clickElement(chkBoxSelect);
		clickElement(btnSelectSubService);
    }
	
	// Custom method for Selecting SubService
	public void selectSubServiceButtonForMobile() {
		clickElement(btnSelectSubService);
		clickElement(chkBoxSelect);
		chkBoxSelect.sendKeys(Keys.TAB);
	}

	public void selectDateFromDatePicker() {
		SelectDate(txtDatePicker,oNextMonth,txtSelectDate);
	}
	
	public void selectDateFromDatePickerForMobile() throws InterruptedException {
		SelectDateForMobile(txtDatePicker);
		Thread.sleep(1000);
	}
	
	public void selectTime(String index) {
		int indexNum=Integer.parseInt(index);
        selectValueInDropdown(selTime, indexNum);
    }
	
	//Custom method for selecting the Location Area
	public void EnterLocation(String sLocation) throws InterruptedException {
		setElementText(textLocation, sLocation);
		
		//textLocation.sendKeys(sLocation);
		textLocation.sendKeys(Keys.ARROW_DOWN);	
		Thread.sleep(1000);
		textLocation.sendKeys(Keys.ENTER);
		
	}
	
	public void setText_CustomerAddress(String text){
		textAddress.sendKeys(text);
       // Assert.assertEquals(textMobileNum.getAttribute("value"), text);
    }
	
	public void setText_EmailId(String text){
		textEmailId.sendKeys(text);
       // Assert.assertEquals(textMobileNum.getAttribute("value"), text);
    }
	
	public void setText_CustPinCode(String text){
		textPincode.sendKeys(text);
       // Assert.assertEquals(textMobileNum.getAttribute("value"), text);
    }
	
	public void setText_SpecialInstructions(String text){
		textSplInstructions.sendKeys(text);
       // Assert.assertEquals(textMobileNum.getAttribute("value"), text);
    }
	
	public void setText_CouponCode(String text){
		textCouponCode.sendKeys(text);
       // Assert.assertEquals(textMobileNum.getAttribute("value"), text);
    }
	
	public void click_ApplyCouponBtn(){
        clickElement(btnCouponApply);
    }
	
	public void validateAppliedCoupon(String Text) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(true, lblCouponText.getText().contains(Text));
	}
	
	public void click_BookNowBtn(){
        clickElement(btnBookNowSubmit);
    }
	
	
		
}
