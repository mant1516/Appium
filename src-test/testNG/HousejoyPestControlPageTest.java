package testNG;

import java.util.Map;

import org.housejoy.common.tools.CommonTools;
import org.housejoy.common.tools.WebDriverProcessor;
import org.housejoy.data.HousejoyPestControlPageData;
import org.housejoy.pom.pestcontrol.PestControlLandingPage;
import org.housejoy.pom.pestcontrol.PestControlLeadBookingPage;
import org.housejoy.pom.pestcontrol.PestControlThankUPage;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 
 * @author Kishlay Kumar
 * @Desc  To test if SEM page of Beauty is working fine with a usecase.
 * 
 */
public class HousejoyPestControlPageTest {
	
	public WebDriver driver;
	PestControlLandingPage PestControlLeadPage;
	PestControlLeadBookingPage PestControlLeadBookPage;
	PestControlThankUPage PestControlThankUPage;
	private static String sCookieID;
	
	
	/**
	 * @Desc Initialize the browser and initialize the Elements of Beauty pages.
	 */
	@BeforeClass(alwaysRun = true)
	public void setup() {
		this.driver = WebDriverProcessor.selectBrowser();
		PestControlLeadPage = PageFactory.initElements(driver, PestControlLandingPage.class);
		PestControlLeadBookPage = PageFactory.initElements(driver, PestControlLeadBookingPage.class);
		PestControlThankUPage = PageFactory.initElements(driver, PestControlThankUPage.class);
	}

	/**
	 * @Desc Close the Driver After the testcase has run.
	 */
	@AfterClass(alwaysRun = true)
	public void teardown() {
		this.driver.quit();
	}
	
	   	    
	/**
	 * @Desc Test method is used for testing the landing page of Beauty.
	 */  
	@Test(groups = { "p1" }, dataProviderClass = HousejoyPestControlPageData.class, dataProvider = "PestControlLandingValues", priority = 1)
	public void testHousejoyPestControlLandingPage(Map<String,String> landingPageValues) throws Throwable {
		try {
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("QaSemPages")) {
				PestControlLeadPage.loadPage(landingPageValues.get("QA_Url"));
			}
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("ProductionSemPages")) {
				PestControlLeadPage.loadPage(landingPageValues.get("Prod_Url"));
			}
			Cookie cookie = driver.manage().getCookieNamed("cookieid");
			sCookieID = cookie.getValue();
			PestControlLeadPage.selectCity(landingPageValues.get("cityName"));
			PestControlLeadPage.setText_CustomerName(landingPageValues.get("custName"));
			PestControlLeadPage.setText_CustomerMobileNum(landingPageValues.get("custMobileNum"));
			PestControlLeadPage.setText_CustomerEmail(landingPageValues.get("custEmail"));
			PestControlLeadPage.click_BookNowBtn();
		} catch (Throwable t) {
			PestControlLeadPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for verifying the parameters of the URL in the SEM table in the DB and checking the LeadStatus .
	 */  
	@Test(groups = { "p1" }, priority = 2,dependsOnMethods = "testHousejoyPestControlLandingPage", dataProviderClass = HousejoyPestControlPageData.class, dataProvider = "SemTableValues")
	public void testHousejoySemTrackingTable(Map<String,String> semTableValues) throws Throwable {
		try {
			
			PestControlLeadPage.validateSemTableValues(sCookieID,semTableValues);
			//PlumbingMainPage.validateLeadStatusFromDB( semTableValues.get("columnName"),sCookieID,semTableValues.get("columnValue"));

		} catch (Throwable t) {
			PestControlLeadPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for testing the lead page of Beauty
	 *       This is dependent on landing Page test method.
	 */
	@Test(groups = {"p1" }, priority = 3, dependsOnMethods = "testHousejoyPestControlLandingPage", dataProviderClass = HousejoyPestControlPageData.class, dataProvider = "PestControlBookingPage")
	public void testHousejoyPestControlBookPage(Map<String,String> LeadBookingPageValues) throws Throwable {
		try {

			PestControlLeadBookPage.validateThankUHeader(LeadBookingPageValues.get("verifyPage"));
			PestControlLeadBookPage.selectSubServiceButton();
			PestControlLeadBookPage.selectDateFromDatePicker();
			PestControlLeadBookPage.selectTime(LeadBookingPageValues.get("bookingTime"));
			PestControlLeadBookPage.EnterLocation(LeadBookingPageValues.get("location"));
			PestControlLeadBookPage.setText_CustomerAddress(LeadBookingPageValues.get("locationAddress"));
			PestControlLeadBookPage.setText_CustPinCode(LeadBookingPageValues.get("pinCode"));
			PestControlLeadBookPage.setText_CouponCode(LeadBookingPageValues.get("couponCode"));
			PestControlLeadBookPage.click_ApplyCouponBtn();
			PestControlLeadBookPage.validateAppliedCoupon(LeadBookingPageValues.get("CouponText"));
			PestControlLeadBookPage.click_ConfirmBookingBtn();
		} catch (Throwable t) {
			PestControlLeadBookPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for testing the Thank you page of Beauty.
	 *       This is dependent on Lead Page test method.
	 *       Here we are verifying all the data displayed on the screen with DB Values.
	 */
	@Test(groups = { "p1" }, priority = 4, dependsOnMethods = "testHousejoyPestControlBookPage", dataProviderClass = HousejoyPestControlPageData.class, dataProvider = "PestControlThankUPage")
	public void testVerifyHousejoyPestControlThankUPageData(String verifyPage) throws Throwable {
		try {

			PestControlThankUPage.validateThankUHeader(verifyPage);
			PestControlThankUPage.validatePageValues();

		} catch (Throwable t) {
			PestControlThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for verifying the values of the Lead Master table in the DB.
	 */
	@Test(groups = { "p1" }, priority = 5, dependsOnMethods = "testHousejoyPestControlBookPage", dataProviderClass = HousejoyPestControlPageData.class, dataProvider = "leadTableValues")
	public void testLeadMasterTableValidation(String serviceType, String cityName, String leadStatus, String emailID, String mobileNumber, String userName) throws Throwable {
		try {

			PestControlThankUPage.validateLeadMasterTableValues(sCookieID,serviceType,cityName,leadStatus,emailID,mobileNumber,userName);

		} catch (Throwable t) {
			PestControlThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
}
