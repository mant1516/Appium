package org.housejoy.pom.computerrepair;

import java.util.ArrayList;
import java.util.Map;

import org.housejoy.common.tools.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ComputerRepairLandingPage extends BasePage {

	
	
	 public ComputerRepairLandingPage(WebDriver driver) {
	        super(driver);
	  }
	 
	/*@FindBy(xpath="//*[@id='myModal']/div[1]/input")
	public WebElement txtCityName;
	
	@FindBy(xpath="//*[@id='selectedcity']/a")
	public WebElement txtselectedcity;*/
	
	@FindBy(id="cityNameOption")
	public WebElement selCityName;
	 
	@FindBy(id="userName")
	public WebElement textUsername; 
	
	@FindBy(id="mobileNumber")
	public WebElement textMobileNum;

	@FindBy(id="userEmailid")
	public WebElement textEmailId;
			
	@FindBy(xpath="//button[starts-with(@class,'btn btn-big btn-block homeclean-button')]")
	public WebElement btnCompRepairSubmit;

	
	
	/* public void click_CityName(){
	        clickElement(txtCityName);
	    }
	
	public void validateSelectedCityName(String sCityName) {
		Assert.assertEquals(true,txtselectedcity.getText().equals(sCityName));
		
	}*/
	
	public void selectCity(String text) {
        selectValueInDropdownByText(selCityName, text);
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
	
	public void click_BookComputerRepair(){
        clickElement(btnCompRepairSubmit);
    }
	
	public void click_SubmitBtnMobile() {
		textMobileNum.sendKeys(Keys.TAB);
		btnCompRepairSubmit.sendKeys(Keys.ENTER);
	}
	
	public void validateSemTableValues(String cookieID, Map<String, String> semTableValues) throws Exception {

		getAndValidateSemTableContents(cookieID, semTableValues);
	}

}
