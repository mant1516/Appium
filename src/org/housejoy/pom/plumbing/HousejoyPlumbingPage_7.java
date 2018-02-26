package org.housejoy.pom.plumbing;

import java.util.Map;

import org.housejoy.common.tools.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HousejoyPlumbingPage_7 extends BasePage {

	
	public HousejoyPlumbingPage_7(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "cityNameOption")
	public WebElement selCityName;

	@FindBy(xpath = "//*[@id='selectedcity']/a")
	public WebElement txtselectedcity;

	@FindBy(id = "userName")
	public WebElement textUsername;

	@FindBy(id = "mobileNumber")
	public WebElement textMobileNum;

	@FindBy(id = "userEmailid")
	public WebElement textEmailId;

	@FindBy(xpath = "//*[@id='beautyForm']/button")
	public WebElement btnBookPlumber;

		
	
	public void click_CityName(String text) {
		selectValueInDropdownByText(selCityName, text);
	}

	public void validateSelectedCityName(String sCityName) {
		Assert.assertEquals(true, txtselectedcity.getText().equals(sCityName));

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

	public void click_BookPlumber() {
		clickElement(btnBookPlumber);
	}

	public void click_BookPlumberMobile() {
		textMobileNum.sendKeys(Keys.TAB);
		btnBookPlumber.sendKeys(Keys.ENTER);
	}
	
	public void validateSemTableValues(String cookieID, Map<String, String> semTableValues) throws Exception {

		getAndValidateSemTableContents(cookieID, semTableValues);
	}
	
	public void validateLeadStatusFromDB(String sColumnName,String cookieID,String expectedLeadStatus) throws Exception {
		String leadStatusFromDB=getColumnValueFromDB( sColumnName, cookieID);
		
		Assert.assertEquals(expectedLeadStatus, leadStatusFromDB);
		
    }
	
}
