package testNG;

import java.util.Map;

import org.housejoy.common.tools.BasePage;
import org.housejoy.common.tools.CommonTools;
import org.housejoy.common.tools.WebDriverProcessor;
import org.housejoy.data.HousejoyPlumbingPageData;
import org.housejoy.pom.plumbing.HousejoyPlumbingPage;
import org.housejoy.pom.plumbing.PlumbingServiceRequestPage;
import org.housejoy.pom.plumbing.VerifyPlumbingDetails;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 
 * @author Kishlay Kumar
 * @Desc  To test if SEM page of Plumbing is working fine with a usecase.
 * 
 */
public class HousejoyPlumbingPageTest_6 {
	
	public WebDriver driver;
	BasePage MainPage;
	HousejoyPlumbingPage PlumbingMainPage;
	PlumbingServiceRequestPage PlumbingReqFeed;
	VerifyPlumbingDetails PlumbingDetails;
	private static String sCookieID;
	
	
	/**
	 * @Desc Initialize the browser and initialize the Elements of Plumbing pages.
	 */
	@BeforeClass(alwaysRun = true)
	public void setup() {
		this.driver = WebDriverProcessor.selectBrowser();
		// MainPage = PageFactory.initElements(driver,HousejoyPlumbingPage.class );
		PlumbingMainPage = PageFactory.initElements(driver, HousejoyPlumbingPage.class);
		PlumbingReqFeed = PageFactory.initElements(driver, PlumbingServiceRequestPage.class);
		PlumbingDetails = PageFactory.initElements(driver, VerifyPlumbingDetails.class);
	}

	/**
	 * @Desc Close the Driver After the testcase has run.
	 */
	@AfterClass(alwaysRun = true)
	public void teardown() {
		this.driver.quit();
	}
	
	   	    
	/**
	 * @Desc Test method is used for testing the landing page of Plumbing.
	 */  
	@Test(groups = { "p1" }, dataProviderClass = HousejoyPlumbingPageData.class, dataProvider = "pageValues", priority = 1)
	public void testHousejoyPlumbingPage(Map<String,String> landingPageValues) throws Throwable {
		try {
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("QaSemPages")) {
				PlumbingMainPage.loadPage(landingPageValues.get("QA_Url_6"));
			}
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("ProductionSemPages")) {
				PlumbingMainPage.loadPage(landingPageValues.get("Prod_Url_6"));
			}
			Cookie cookie = driver.manage().getCookieNamed("cookieid");
			sCookieID = cookie.getValue();
			PlumbingMainPage.click_CityName();
			PlumbingMainPage.setText_CustomerName(landingPageValues.get("custName"));
			PlumbingMainPage.setText_CustomerMobileNum(landingPageValues.get("custMobileNum"));
			PlumbingMainPage.setText_CustomerEmail(landingPageValues.get("custEmail"));
			PlumbingMainPage.click_BookPlumber();

		} catch (Throwable t) {
			PlumbingMainPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for verifying the parameters of the URL in the SEM table in the DB and checking the LeadStatus .
	 */  
	@Test(groups = { "p1" }, priority = 2,dependsOnMethods = "testHousejoyPlumbingPage", dataProviderClass = HousejoyPlumbingPageData.class, dataProvider = "testWithMap")
	public void testHousejoyPlumbingSemTrackingTable(Map<String,String> semTableValues) throws Throwable {
		try {
			
			PlumbingMainPage.validateSemTableValues(sCookieID,semTableValues);
			//PlumbingMainPage.validateLeadStatusFromDB( semTableValues.get("columnName"),sCookieID,semTableValues.get("columnValue"));

		} catch (Throwable t) {
			PlumbingMainPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for testing the lead page of Plumbing .
	 *       This is dependent on landing Page test method.
	 */
	@Test(groups = {"p1" }, priority = 3, dependsOnMethods = "testHousejoyPlumbingPage", dataProviderClass = HousejoyPlumbingPageData.class, dataProvider = "plumbingservicereq")
	public void testHousejoyPlumbingBookPage(Map<String,String> LeadBookingPageValues) throws Throwable {
		try {

			PlumbingReqFeed.validateThankUHeader(LeadBookingPageValues.get("verifyPage"));
			PlumbingReqFeed.selectSubServiceButton();
			PlumbingReqFeed.selectDateFromDatePicker();
			PlumbingReqFeed.selectTime(LeadBookingPageValues.get("bookingTime"));
			PlumbingReqFeed.EnterLocation(LeadBookingPageValues.get("location"));
			PlumbingReqFeed.setText_CustomerAddress(LeadBookingPageValues.get("locationAddress"));
			PlumbingReqFeed.setText_CustPinCode(LeadBookingPageValues.get("pinCode"));
			PlumbingReqFeed.setText_CouponCode(LeadBookingPageValues.get("couponCode"));
			PlumbingReqFeed.click_ApplyCouponBtn();
			PlumbingReqFeed.validateAppliedCoupon(LeadBookingPageValues.get("CouponText"));
			PlumbingReqFeed.click_SendPlumberBtn();

		} catch (Throwable t) {
			PlumbingReqFeed.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for testing the Thank you page of Plumbing.
	 *       This is dependent on Lead Page test method.
	 *       Here we are verifying all the data displayed on the screen with DB Values.
	 */
	@Test(groups = { "p1" }, priority = 5, dependsOnMethods = "testHousejoyPlumbingBookPage", dataProviderClass = HousejoyPlumbingPageData.class, dataProvider = "verifyPlumbingData")
	public void testVerifyHousejoyPlumbingThankUPageData(String verifyPage) throws Throwable {
		try {

			PlumbingDetails.validateThankUHeader(verifyPage);
			PlumbingDetails.validatePageValues();

		} catch (Throwable t) {
			PlumbingDetails.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for verifying the values of the Lead Master table in the DB.
	 */
	@Test(groups = { "p1" }, priority = 4, dependsOnMethods = "testHousejoyPlumbingBookPage", dataProviderClass = HousejoyPlumbingPageData.class, dataProvider = "leadTableValues")
	public void testLeadMasterTableValidation(String serviceType, String cityName, String leadStatus, String emailID, String mobileNumber, String userName) throws Throwable {
		try {

			PlumbingDetails.validateLeadMasterTableValues(sCookieID,serviceType,cityName,leadStatus,emailID,mobileNumber,userName);

		} catch (Throwable t) {
			PlumbingDetails.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
}
