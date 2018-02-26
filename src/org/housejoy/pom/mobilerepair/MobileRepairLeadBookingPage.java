package org.housejoy.pom.mobilerepair;

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

public class MobileRepairLeadBookingPage extends BasePage {


	public MobileRepairLeadBookingPage(WebDriver driver) {
		 super(driver);
	}
	
	@FindBy(xpath="//div[starts-with(@class,'col-md-8 col-sm-9 col-xs-12 part1-sub')]")
	public WebElement txtThankUHeader;
	

	@FindBy(xpath="//div[starts-with(@class,'col-lg-7 col-md-7 text-center tnk-pg')]")
	public WebElement txtThankUHeader_1;
		
	@FindBy(id="subService")
	public WebElement selService;
	
	@FindBy(xpath="//button[starts-with(@class,'multiselect dropdown-toggle btn btn-default')]")
	public WebElement btnSelectSubService;
	
	@FindBy(xpath="//input[starts-with(@value,'3259')]")
	public WebElement chkBoxSelectQA;
	
	@FindBy(xpath="//input[starts-with(@value,'133')]")
	public WebElement chkBoxSelectProd;
	
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
	public WebElement textEmaild;

	@FindBy(id="brand")
	public WebElement textMobileBrand;
	
	@FindBy(id="model")
	public WebElement textMobileModel;
	
	@FindBy(id="pinCode")
	public WebElement textPincode;
	
	@FindBy(id="comments")
	public WebElement textSplInstructions;
	
	@FindBy(id="comments1")
	public WebElement textSplInstructionsMob;
	
	@FindBy(id="comments1")
	public WebElement textSplInstructions_1;
	
	@FindBy(id="couponCode")
	public WebElement textCouponCode;
	
	//@FindBy(xpath="//button[starts-with(@class,'btn btn-success btn-block')]") removing the "btn-block" part to make it wor on mobile web automation
	@FindBy(xpath="//button[starts-with(@class,'btn btn-success')]")
	public WebElement btnCouponApply;
	
	@FindBy(id="successMsg")
	public WebElement lblCouponText;
	
	//@FindBy(xpath="//*[@id='leadsIntermediateForm']/div[11]/div/button") successMsg 
	@FindBy(xpath="//button[starts-with(@class,'btn btn-lg btn-primary btn-block btn-signin')]")
	public WebElement btnConfirmBooking;
	
	@FindBy(xpath="//button[starts-with(@class,'btn btn-big btn-innr book-btn2 btn_submit')]")
	public WebElement btnConfirmBookingMob;
	
	@FindBy(xpath="//button[starts-with(@class,'btn btn-big btn-innr btn-block btn_submit')]")
	public WebElement btnConfirmBooking_1;
	
	
	
	public void validateThankUHeader(String sHeadervalue) throws InterruptedException {
		//Thread.sleep(1000);
		Assert.assertEquals(true, txtThankUHeader.getText().equals(sHeadervalue));
	}
	
	public void validateThankUHeader_1(String sHeadervalue) throws InterruptedException {
		//Thread.sleep(1000);
		Assert.assertEquals(true, txtThankUHeader_1.getText().equals(sHeadervalue));
	}
		
	//Custom method for Selecting SubService
		
	public void selectSubServiceButtonQA() {
        //selectValueInDropdown(selService, value);
		clickElement(btnSelectSubService);
		clickElement(chkBoxSelectQA);
		clickElement(btnSelectSubService);
    }
	
	public void selectSubServiceButtonProd() {
        //selectValueInDropdown(selService, value);
		clickElement(btnSelectSubService);
		clickElement(chkBoxSelectProd);
		clickElement(btnSelectSubService);
    }
	
	public void selectSubServiceButtonForQAMobile() {
		clickElement(btnSelectSubService);
		clickElement(chkBoxSelectQA);
		chkBoxSelectQA.sendKeys(Keys.TAB);
	}
	
	public void selectSubServiceButtonForProdMobile() {
		clickElement(btnSelectSubService);
		clickElement(chkBoxSelectProd);
		chkBoxSelectProd.sendKeys(Keys.TAB);
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
	
	public void setText_CustomerEmailId(String text){
		textEmaild.sendKeys(text);
       // Assert.assertEquals(textMobileNum.getAttribute("value"), text);
    }
	
	public void setText_MobileBrand(String text){
		textMobileBrand.sendKeys(text);
       // Assert.assertEquals(textMobileNum.getAttribute("value"), text);
    }
	
	public void setText_MobileModel(String text){
		textMobileModel.sendKeys(text);
       // Assert.assertEquals(textMobileNum.getAttribute("value"), text);
    }
	
	public void setText_SpecialInstructions(String text){
		textSplInstructions.sendKeys(text);
       // Assert.assertEquals(textMobileNum.getAttribute("value"), text);
    }
	
	public void setText_SpecialInstructionsMobile(String text){
		textSplInstructionsMob.sendKeys(text);
       // Assert.assertEquals(textMobileNum.getAttribute("value"), text);
    }
	
	public void setText_SpecialInstructions_1(String text){
		textSplInstructions_1.sendKeys(text);
       // Assert.assertEquals(textMobileNum.getAttribute("value"), text);
    }
	
	public void setText_CustPinCode(String text){
		textPincode.sendKeys(text);
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
	
	public void click_ConfirmBookingBtn(){
        clickElement(btnConfirmBooking);
    }
	
	public void click_ConfirmBookingBtnMobile(){
		clickElement(btnConfirmBookingMob);
        
    }
	
	public void click_ConfirmBookingBtn_1(){
        clickElement(btnConfirmBooking_1);
    }
		
}
