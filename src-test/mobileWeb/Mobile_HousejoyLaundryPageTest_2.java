package mobileWeb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Map;

import org.housejoy.common.tools.BasePage;
import org.housejoy.common.tools.CommonTools;
import org.housejoy.common.tools.WebDriverProcessor;
import org.housejoy.data.HousejoyLaundryPageData;
import org.housejoy.pom.electrical.ElectricalThankUPage;
import org.housejoy.pom.homecleaning.HomeCleaningLandingPage;
import org.housejoy.pom.homecleaning.HomeCleaningLeadBookingPage;
import org.housejoy.pom.homecleaning.HomeCleaningThankUPage;
import org.housejoy.pom.laundry.LaundryLandingPage_2;
import org.housejoy.pom.laundry.LaundryLeadBookingPage;
import org.housejoy.pom.laundry.LaundryThankUPage;
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
 * @Desc  To test if SEM page of Laundry is working fine with a usecase.
 * 
 */
public class Mobile_HousejoyLaundryPageTest_2 {
	
	public WebDriver driver;
	LaundryLandingPage_2 LaundryLeadPage;
	LaundryLeadBookingPage LaundryLeadBookPage;
	LaundryThankUPage LaundryThankUPage;
	private static String sCookieID;
	
	
	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @Desc Initialize the browser and initialize the Elements of Laundry pages.
	 */
	@BeforeClass(alwaysRun = true)
	public void setup() throws FileNotFoundException, IOException {
		try {

			DesiredCapabilities capabilities = BasePage.getDesiredCapabilitiesforMobile();

			driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

			LaundryLeadPage = PageFactory.initElements(driver, LaundryLandingPage_2.class);
			LaundryLeadBookPage = PageFactory.initElements(driver, LaundryLeadBookingPage.class);
			LaundryThankUPage = PageFactory.initElements(driver, LaundryThankUPage.class);
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
	 * @Desc Test method is used for testing the landing page of Laundry.
	 */  
	@Test(groups = { "p1" }, dataProviderClass = HousejoyLaundryPageData.class, dataProvider = "LaundryLandingValues_2", priority = 1)
	public void testHousejoyLaundryLandingPage(Map<String,String> landingPageValues) throws Throwable {
		try {
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("QaSemPages")) {
				LaundryLeadPage.loadMobilePage(landingPageValues.get("QA_Url"));
			}
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("ProductionSemPages")) {
				LaundryLeadPage.loadMobilePage(landingPageValues.get("Prod_Url"));
			}
			Cookie cookie = driver.manage().getCookieNamed("cookieid");
			sCookieID = cookie.getValue();
			LaundryLeadPage.selectCity(landingPageValues.get("cityName"));
			LaundryLeadPage.setText_CustomerName(landingPageValues.get("custName"));
			LaundryLeadPage.setText_CustomerMobileNum(landingPageValues.get("custMobileNum"));
			LaundryLeadPage.setText_CustomerEmail(landingPageValues.get("custEmail"));
			LaundryLeadPage.click_BookNowBtn();
		} catch (Throwable t) {
			LaundryLeadPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for verifying the parameters of the URL in the SEM table in the DB and checking the LeadStatus .
	 */  
	@Test(groups = { "p1" }, priority = 2,dependsOnMethods = "testHousejoyLaundryLandingPage", dataProviderClass = HousejoyLaundryPageData.class, dataProvider = "SemTableValues")
	public void testHousejoySemTrackingTable(Map<String,String> semTableValues) throws Throwable {
		try {
			
			LaundryLeadPage.validateSemTableValues(sCookieID,semTableValues);
			//PlumbingMainPage.validateLeadStatusFromDB( semTableValues.get("columnName"),sCookieID,semTableValues.get("columnValue"));

		} catch (Throwable t) {
			LaundryLeadPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for testing the lead page of Beauty
	 *       This is dependent on landing Page test method.
	 */
	@Test(groups = {"p1" }, priority = 3, dependsOnMethods = "testHousejoyLaundryLandingPage", dataProviderClass = HousejoyLaundryPageData.class, dataProvider = "LaundryBookingPage")
	public void testHousejoyLaundryBookPage(Map<String,String> LeadBookingPageValues) throws Throwable {
		try {

			LaundryLeadBookPage.validateThankUHeader(LeadBookingPageValues.get("verifyMobilePage"));
			LaundryLeadBookPage.selectSubServiceButtonForMobile();
			LaundryLeadBookPage.selectDateFromDatePickerForMobile();
			LaundryLeadBookPage.selectTime(LeadBookingPageValues.get("bookingTime"));
			LaundryLeadBookPage.EnterLocation(LeadBookingPageValues.get("location"));
			LaundryLeadBookPage.setText_CustomerAddress(LeadBookingPageValues.get("locationAddress"));
			LaundryLeadBookPage.setText_CustPinCode(LeadBookingPageValues.get("pinCode"));
			LaundryLeadBookPage.setText_CouponCode(LeadBookingPageValues.get("couponCode"));
			LaundryLeadBookPage.click_ApplyCouponBtn();
			LaundryLeadBookPage.validateAppliedCoupon(LeadBookingPageValues.get("CouponText"));
			LaundryLeadBookPage.click_ConfirmBookingBtn();
		} catch (Throwable t) {
			LaundryLeadBookPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for testing the Thank you page of Beauty.
	 *       This is dependent on Lead Page test method.
	 *       Here we are verifying all the data displayed on the screen with DB Values.
	 */
	@Test(groups = { "p1" }, priority = 5, dependsOnMethods = "testHousejoyLaundryBookPage", dataProviderClass = HousejoyLaundryPageData.class, dataProvider = "LaundryThankUPage")
	public void testVerifyHousejoyLaundryThankUPageData(String verifyPage) throws Throwable {
		try {

			//LaundryThankUPage.validateThankUHeader(verifyPage);
			LaundryThankUPage.validatePageValues();

		} catch (Throwable t) {
			LaundryThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for verifying the values of the Lead Master table in the DB.
	 */
	@Test(groups = { "p1" }, priority = 4, dependsOnMethods = "testHousejoyLaundryBookPage", dataProviderClass = HousejoyLaundryPageData.class, dataProvider = "leadTableValues")
	public void testLeadMasterTableValidation(String serviceType, String cityName, String leadStatus, String emailID, String mobileNumber, String userName) throws Throwable {
		try {

			LaundryThankUPage.validateLeadMasterTableValues(sCookieID,serviceType,cityName,leadStatus,emailID,mobileNumber,userName);

		} catch (Throwable t) {
			LaundryThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
}
