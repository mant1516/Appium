package testNG;

import java.util.Map;

import org.housejoy.common.tools.CommonTools;
import org.housejoy.common.tools.WebDriverProcessor;
import org.housejoy.data.HousejoyCarpentryPageData;
import org.housejoy.pom.carpentry.CarpentryLandingPage;
import org.housejoy.pom.carpentry.CarpentryLeadBookingPage;
import org.housejoy.pom.carpentry.CarpentryThankUPage;
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
public class HousejoyCarpentryPageTest {
	
	public WebDriver driver;
	CarpentryLandingPage CarpentryLeadPage;
	CarpentryLeadBookingPage CarpentryLeadBookPage;
	CarpentryThankUPage CarpentryThankUPage;
	private static String sCookieID;
	
	
	/**
	 * @Desc Initialize the browser and initialize the Elements of Beauty pages.
	 */
	@BeforeClass(alwaysRun = true)
	public void setup() {
		this.driver = WebDriverProcessor.selectBrowser();
		CarpentryLeadPage = PageFactory.initElements(driver, CarpentryLandingPage.class);
		CarpentryLeadBookPage = PageFactory.initElements(driver, CarpentryLeadBookingPage.class);
		CarpentryThankUPage = PageFactory.initElements(driver, CarpentryThankUPage.class);
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
	@Test(groups = { "p1" }, dataProviderClass = HousejoyCarpentryPageData.class, dataProvider = "CarpentryLandingValues", priority = 1)
	public void testHousejoyCarpentryLandingPage(Map<String,String> landingPageValues) throws Throwable {
		try {
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("QaSemPages")) {
				CarpentryLeadPage.loadPage(landingPageValues.get("QA_Url"));
			}
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("ProductionSemPages")) {
				CarpentryLeadPage.loadPage(landingPageValues.get("Prod_Url"));
			}
			Cookie cookie = driver.manage().getCookieNamed("cookieid");
			sCookieID = cookie.getValue();
			CarpentryLeadPage.selectCity(landingPageValues.get("cityName"));
			CarpentryLeadPage.setText_CustomerName(landingPageValues.get("custName"));
			CarpentryLeadPage.setText_CustomerMobileNum(landingPageValues.get("custMobileNum"));
			CarpentryLeadPage.setText_CustomerEmail(landingPageValues.get("custEmail"));
			CarpentryLeadPage.click_BookNowBtn();
		} catch (Throwable t) {
			CarpentryLeadPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for verifying the parameters of the URL in the SEM table in the DB and checking the LeadStatus .
	 */  
	@Test(groups = { "p1" }, priority = 2,dependsOnMethods = "testHousejoyCarpentryLandingPage", dataProviderClass = HousejoyCarpentryPageData.class, dataProvider = "SemTableValues")
	public void testHousejoySemTrackingTable(Map<String,String> semTableValues) throws Throwable {
		try {
			
			CarpentryLeadPage.validateSemTableValues(sCookieID,semTableValues);
			//PlumbingMainPage.validateLeadStatusFromDB( semTableValues.get("columnName"),sCookieID,semTableValues.get("columnValue"));

		} catch (Throwable t) {
			CarpentryLeadPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for testing the lead page of Beauty
	 *       This is dependent on landing Page test method.
	 */
	@Test(groups = {"p1" }, priority = 3, dependsOnMethods = "testHousejoyCarpentryLandingPage", dataProviderClass = HousejoyCarpentryPageData.class, dataProvider = "CarpentryBookingPage")
	public void testHousejoyCarpentryBookPage(Map<String,String> LeadBookingPageValues) throws Throwable {
		try {

			CarpentryLeadBookPage.validateThankUHeader(LeadBookingPageValues.get("verifyPage"));
			CarpentryLeadBookPage.selectSubServiceButton();
			CarpentryLeadBookPage.selectDateFromDatePicker();
			CarpentryLeadBookPage.selectTime(LeadBookingPageValues.get("bookingTime"));
			CarpentryLeadBookPage.EnterLocation(LeadBookingPageValues.get("location"));
			CarpentryLeadBookPage.setText_CustomerAddress(LeadBookingPageValues.get("locationAddress"));
			CarpentryLeadBookPage.setText_CustPinCode(LeadBookingPageValues.get("pinCode"));
			CarpentryLeadBookPage.setText_CouponCode(LeadBookingPageValues.get("couponCode"));
			CarpentryLeadBookPage.click_ApplyCouponBtn();
			CarpentryLeadBookPage.validateAppliedCoupon(LeadBookingPageValues.get("CouponText"));
			CarpentryLeadBookPage.click_ConfirmBookingBtn();
		} catch (Throwable t) {
			CarpentryLeadBookPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for testing the Thank you page of Beauty.
	 *       This is dependent on Lead Page test method.
	 *       Here we are verifying all the data displayed on the screen with DB Values.
	 */
	@Test(groups = { "p1" }, priority = 4, dependsOnMethods = "testHousejoyCarpentryBookPage", dataProviderClass = HousejoyCarpentryPageData.class, dataProvider = "CarpentryThankUPage")
	public void testVerifyHousejoyCarpentryThankUPageData(String verifyPage) throws Throwable {
		try {

			CarpentryThankUPage.validateThankUHeader(verifyPage);
			CarpentryThankUPage.validatePageValues();

		} catch (Throwable t) {
			CarpentryThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for verifying the values of the Lead Master table in the DB.
	 */
	@Test(groups = { "p1" }, priority = 5, dependsOnMethods = "testHousejoyCarpentryBookPage", dataProviderClass = HousejoyCarpentryPageData.class, dataProvider = "leadTableValues")
	public void testLeadMasterTableValidation(String serviceType, String cityName, String leadStatus, String emailID, String mobileNumber, String userName) throws Throwable {
		try {

			CarpentryThankUPage.validateLeadMasterTableValues(sCookieID,serviceType,cityName,leadStatus,emailID,mobileNumber,userName);

		} catch (Throwable t) {
			CarpentryThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
}
