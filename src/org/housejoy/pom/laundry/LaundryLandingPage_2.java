package org.housejoy.pom.laundry;

import java.util.Map;

import org.housejoy.common.tools.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LaundryLandingPage_2 extends BasePage {

	
	
	 public LaundryLandingPage_2(WebDriver driver) {
	        super(driver);
	  }
	 
	
	@FindBy(xpath="//div[starts-with(@class,'col-lg-12 city-links')]/a[1]")
	public WebElement txtCityName;
	 
	@FindBy(id="userName")
	public WebElement textUsername; 
	
	@FindBy(id="mobileNumber")
	public WebElement textMobileNum;

	@FindBy(id="userEmailid")
	public WebElement textEmailId;
			
	@FindBy(xpath="//button[starts-with(@class,'btn btn-danger submit beauty-button')]")
	public WebElement btnBookNow;

	
	public void selectCity(String text) {
		clickElement(txtCityName);
    }
	
	public void setText_CustomerName(String text){
		textUsername.sendKeys(text);
       // Assert.assertEquals(textUsername.getAttribute("value"), text);
    }
	
	public void setText_CustomerMobileNum(String text){
		textMobileNum.sendKeys(text);
       // Assert.assertEquals(textMobileNum.getAttribute("value"), text);
    }
	
	public void setText_CustomerEmail(String text){
		textEmailId.sendKeys(text);
       // Assert.assertEquals(textEmailId.getAttribute("value"), text);
    }
	
	public void click_BookNowBtn(){
        clickElement(btnBookNow);
    }
	
	public void click_BookNowBtnMobile() {
		textMobileNum.sendKeys(Keys.TAB);
		btnBookNow.sendKeys(Keys.ENTER);
	}
	
	public void validateSemTableValues(String cookieID, Map<String, String> semTableValues) throws Exception {

		getAndValidateSemTableContents(cookieID, semTableValues);
	}

}
