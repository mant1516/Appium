package org.housejoy.pom.fitness;

import org.housejoy.common.tools.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class FitnessLeadBookingPage extends BasePage {


	public FitnessLeadBookingPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//div[starts-with(@class,'try-box')]")
	public WebElement txtThankUHeader;

	@FindBy(id="subService")
	public WebElement selSubService;
	
	@FindBy(id="fitness_goal")
	public WebElement selFitnessGoal;
	
	@FindBy(id="age")
	public WebElement selAge;
	
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
	
	@FindBy(id="comments")
	public WebElement textDetails;
	
	@FindBy(id="scroll")
	public WebElement btnBookNow;
	
	@FindBy(xpath="//button[starts-with(@class,'book-btn1 btn_submit')]")
	public WebElement btnBookNow_4;
	
	@FindBy(xpath="//button[starts-with(@class,'btn btn-default bk-now btn_submit')]")
	public WebElement btnConfirmBooking;
	
	@FindBy(xpath="//button[contains(text(),'Book Now')]")
	public WebElement btnConfirmBookingMob;
	
	public void validateThankUHeader(String sHeadervalue) throws InterruptedException {
		//Thread.sleep(1000);
		Assert.assertEquals(true, txtThankUHeader.getText().equals(sHeadervalue));
	}
	
	public void selectSubService(String text) {
		selectValueInDropdownByText(selSubService, text);
	}
	
	public void selectFitnessGoal(String text) {
		selectValueInDropdownByText(selFitnessGoal, text);
	}
	
	public void selectAge(String text) {
		selectValueInDropdownByText(selAge, text);
	}
	
	public void selectDateFromDatePicker() {
		SelectDate(txtDatePicker,oNextMonth,txtSelectDate);
	}
	
	public void selectDateFromDatePickerForMobile() throws InterruptedException {
		SelectDateForMobile(txtDatePicker);
		Thread.sleep(1000);
	}
	
	public void selectTime(String index) {
		int indexNum= Integer.parseInt(index);
		selectValueInDropdown(selTime, indexNum);
	}

	// Custom method for selecting the Location Area
	public void EnterLocation(String sLocation) throws InterruptedException {
		setElementText(textLocation, sLocation);
		textLocation.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		textLocation.sendKeys(Keys.ENTER);

	}

	public void setText_CustomerAddress(String text) {
		textAddress.sendKeys(text);
		// Assert.assertEquals(textMobileNum.getAttribute("value"), text);
	}

	public void setText_EmailId(String text) {
		textEmailId.sendKeys(text);
		// Assert.assertEquals(textMobileNum.getAttribute("value"), text);
	}
	
	public void setText_CustPinCode(String text) {
		textPincode.sendKeys(text);
		// Assert.assertEquals(textMobileNum.getAttribute("value"), text);
	}
	
	public void setText_Details(String text) {
		textDetails.sendKeys(text);
		// Assert.assertEquals(textMobileNum.getAttribute("value"), text);
	}

	public void click_BookNowBtn() {
		clickElement(btnBookNow);
	}
	
	public void click_BookNowBtn_4() {
		clickElement(btnBookNow_4);
	}
	
	public void click_ConfirmBookNowBtn() {
		clickElement(btnConfirmBooking);
	}
	
	public void click_ConfirmBookNowBtnMobile() {
		textDetails.sendKeys(Keys.TAB);
		btnConfirmBookingMob.sendKeys(Keys.ENTER);;
	}
}
