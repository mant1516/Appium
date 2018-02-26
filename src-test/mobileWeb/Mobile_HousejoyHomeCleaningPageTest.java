package mobileWeb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Map;

import org.housejoy.common.tools.BasePage;
import org.housejoy.common.tools.CommonTools;
import org.housejoy.common.tools.WebDriverProcessor;
import org.housejoy.data.HousejoyHomeCleaningPageData;
import org.housejoy.pom.homecleaning.HomeCleaningLandingPage;
import org.housejoy.pom.homecleaning.HomeCleaningLandingPage_1;
import org.housejoy.pom.homecleaning.HomeCleaningLeadBookingPage;
import org.housejoy.pom.homecleaning.HomeCleaningThankUPage;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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
public class Mobile_HousejoyHomeCleaningPageTest {
	
	public WebDriver driver;
	HomeCleaningLandingPage HomeCleaningLeadPage;
	HomeCleaningLeadBookingPage HomeCleaningLeadBookPage;
	HomeCleaningThankUPage HomeCleaningThankUPage;
	private static String sCookieID;
	
	
	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @Desc Initialize the browser and initialize the Elements of Beauty pages.
	 */
	@BeforeClass(alwaysRun = true)
	public void setup() throws FileNotFoundException, IOException {
		try {

			DesiredCapabilities capabilities = BasePage.getDesiredCapabilitiesforMobile();

			driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

			HomeCleaningLeadPage = PageFactory.initElements(driver, HomeCleaningLandingPage.class);
			HomeCleaningLeadBookPage = PageFactory.initElements(driver, HomeCleaningLeadBookingPage.class);
			HomeCleaningThankUPage = PageFactory.initElements(driver, HomeCleaningThankUPage.class);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	@Test(groups = { "p1" }, dataProviderClass = HousejoyHomeCleaningPageData.class, dataProvider = "HomeCleaningLandingValues", priority = 1)
	public void testHousejoyHomeCleaningLandingPage(Map<String,String> landingPageValues) throws Throwable {
		try {
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("QaSemPages")) {
				HomeCleaningLeadPage.loadMobilePage(landingPageValues.get("QA_Url"));
			}
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("ProductionSemPages")) {
				HomeCleaningLeadPage.loadMobilePage(landingPageValues.get("Prod_Url"));
			}
			Cookie cookie = driver.manage().getCookieNamed("cookieid");
			sCookieID = cookie.getValue();
			HomeCleaningLeadPage.selectCity(landingPageValues.get("cityName"));
			HomeCleaningLeadPage.setText_CustomerName(landingPageValues.get("custName"));
			HomeCleaningLeadPage.setText_CustomerMobileNum(landingPageValues.get("custMobileNum"));
			HomeCleaningLeadPage.setText_CustomerEmail(landingPageValues.get("custEmail"));
			HomeCleaningLeadPage.click_SubmitBtnMobile();
		} catch (Throwable t) {
			HomeCleaningLeadPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for verifying the parameters of the URL in the SEM table in the DB and checking the LeadStatus .
	 */  
	@Test(groups = { "p1" }, priority = 2,dependsOnMethods = "testHousejoyHomeCleaningLandingPage", dataProviderClass = HousejoyHomeCleaningPageData.class, dataProvider = "SemTableValues")
	public void testHousejoySemTrackingTable(Map<String,String> semTableValues) throws Throwable {
		try {
			
			HomeCleaningLeadPage.validateSemTableValues(sCookieID,semTableValues);
			//PlumbingMainPage.validateLeadStatusFromDB( semTableValues.get("columnName"),sCookieID,semTableValues.get("columnValue"));

		} catch (Throwable t) {
			HomeCleaningLeadPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for testing the lead page of Beauty
	 *       This is dependent on landing Page test method.
	 */
	@Test(groups = {"p1" }, priority = 3, dependsOnMethods = "testHousejoyHomeCleaningLandingPage", dataProviderClass = HousejoyHomeCleaningPageData.class, dataProvider = "HomeCleaningBookingPage")
	public void testHousejoyHomeCleaningBookPage(Map<String,String> LeadBookingPageValues) throws Throwable {
		try {

			HomeCleaningLeadBookPage.validateThankUHeader(LeadBookingPageValues.get("verifyMobilePage"));
			HomeCleaningLeadBookPage.selectSubServiceButtonForMobile();
			HomeCleaningLeadBookPage.selectDateFromDatePickerForMobile();
			HomeCleaningLeadBookPage.selectTime(LeadBookingPageValues.get("bookingTime"));
			HomeCleaningLeadBookPage.EnterLocation(LeadBookingPageValues.get("location"));
			HomeCleaningLeadBookPage.setText_CustomerAddress(LeadBookingPageValues.get("locationAddress"));
			HomeCleaningLeadBookPage.setText_CustPinCode(LeadBookingPageValues.get("pinCode"));
			HomeCleaningLeadBookPage.setText_CouponCode(LeadBookingPageValues.get("couponCode"));
			HomeCleaningLeadBookPage.click_ApplyCouponBtn();
			HomeCleaningLeadBookPage.validateAppliedCoupon(LeadBookingPageValues.get("CouponText"));
			HomeCleaningLeadBookPage.click_ConfirmBookingBtn();
		} catch (Throwable t) {
			HomeCleaningLeadBookPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for testing the Thank you page of Beauty.
	 *       This is dependent on Lead Page test method.
	 *       Here we are verifying all the data displayed on the screen with DB Values.
	 */
	@Test(groups = { "p1" }, priority = 5, dependsOnMethods = "testHousejoyHomeCleaningBookPage", dataProviderClass = HousejoyHomeCleaningPageData.class, dataProvider = "HomeCleaningThankUPage")
	public void testVerifyHousejoyHomeCleaningThankUPageData(String verifyPage) throws Throwable {
		try {

			HomeCleaningThankUPage.validateThankUHeader(verifyPage);
			HomeCleaningThankUPage.validatePageValues();

		} catch (Throwable t) {
			HomeCleaningThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for verifying the values of the Lead Master table in the DB.
	 */
	@Test(groups = { "p1" }, priority = 4, dependsOnMethods = "testHousejoyHomeCleaningBookPage", dataProviderClass = HousejoyHomeCleaningPageData.class, dataProvider = "leadTableValues")
	public void testLeadMasterTableValidation(String serviceType, String cityName, String leadStatus, String emailID, String mobileNumber, String userName) throws Throwable {
		try {

			HomeCleaningThankUPage.validateLeadMasterTableValues(sCookieID,serviceType,cityName,leadStatus,emailID,mobileNumber,userName);

		} catch (Throwable t) {
			HomeCleaningThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
}
