package org.housejoy.pom.beauty;

import java.util.ArrayList;
import java.util.Map;

import org.housejoy.common.tools.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class BeautyLandingPage_10 extends BasePage {

	
	public BeautyLandingPage_10(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="cityNameOption")
	public WebElement selCityName;
	
	@FindBy(id = "userName")
	public WebElement textUsername;

	@FindBy(id = "mobileNumber")
	public WebElement textMobileNum;

	@FindBy(id = "userEmailid")
	public WebElement textEmailId;

	@FindBy(xpath="//button[starts-with(@class,'beauty-button book')]")
	public WebElement btnSubmit;
	
	@FindBy(xpath="//input[starts-with(@class,'beauty-button book_btn')]")
	public WebElement btnSubmit_17;
	
	@FindBy(xpath="//*[@id='beautyForm']/button")
	public WebElement btnSubmitMobile;
	//td[contains(text()

		
	public void selectCity(String text) {
        selectValueInDropdownByText(selCityName, text);
    }
	
	public void setText_CustomerName(String text) {
		textUsername.sendKeys(text);
		// Assert.assertEquals(textUsername.getAttribute("value"), text);
	}

	public void setText_CustomerMobileNum(String text) {
		textMobileNum.sendKeys(text);
		// Assert.assertEquals(textMobileNum.getAttribute("value"), text);
	}

	public void setText_CustomerEmail(String text) {
		textEmailId.sendKeys(text);
		// Assert.assertEquals(textEmailId.getAttribute("value"), text);
	}

	public void click_SubmitBtn() {
		clickElement(btnSubmit);
	}

	public void click_SubmitBtn_17() {
		clickElement(btnSubmit_17);
	}
	
	public void click_SubmitBtnMobile() {
		//clickElement(btnSubmitMobile);
		textMobileNum.sendKeys(Keys.TAB);
		btnSubmit.sendKeys(Keys.ENTER);
	}
	
	public void click_SubmitBtnMobile_17() {
		//clickElement(btnSubmitMobile);
		textMobileNum.sendKeys(Keys.TAB);
		btnSubmit_17.sendKeys(Keys.ENTER);
	}
	
	public void validateSemTableValues(String cookieID, Map<String, String> semTableValues) throws Exception {

		getAndValidateSemTableContents(cookieID, semTableValues);
	}
	
	public void validateLeadStatusFromDB(String sColumnName,String cookieID,String expectedLeadStatus) throws Exception {
		String leadStatusFromDB=getColumnValueFromDB( sColumnName, cookieID);
		
		Assert.assertEquals(expectedLeadStatus, leadStatusFromDB);
		
    }
	
}
