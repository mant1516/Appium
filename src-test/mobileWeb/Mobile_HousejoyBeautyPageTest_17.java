package mobileWeb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Map;

import org.housejoy.common.tools.BasePage;
import org.housejoy.common.tools.CommonTools;
import org.housejoy.common.tools.WebDriverProcessor;
import org.housejoy.data.HousejoyBeautyPageData;
import org.housejoy.pom.beauty.BeautyLandingPage_1;
import org.housejoy.pom.beauty.BeautyLandingPage_10;
import org.housejoy.pom.beauty.BeautyLeadBookingPage;
import org.housejoy.pom.beauty.BeautyThankUPage;
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
public class Mobile_HousejoyBeautyPageTest_17 {
	
	public WebDriver driver;
	BeautyLandingPage_10 BeautyLeadPage;
	BeautyLeadBookingPage BeautyLeadBookPage;
	BeautyThankUPage BeautyThankUPage;
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

			BeautyLeadPage = PageFactory.initElements(driver, BeautyLandingPage_10.class);
			BeautyLeadBookPage = PageFactory.initElements(driver, BeautyLeadBookingPage.class);
			BeautyThankUPage = PageFactory.initElements(driver, BeautyThankUPage.class);
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
	@Test(groups = { "p1" }, dataProviderClass = HousejoyBeautyPageData.class, dataProvider = "BeautyLandingPageValues_14", priority = 1)
	public void testHousejoyBeautyLandingPage(Map<String,String> landingPageValues) throws Throwable {
		try {
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("QaSemPages")) {
				BeautyLeadPage.loadMobilePage(landingPageValues.get("QA_Url_17"));
			}
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("ProductionSemPages")) {
				BeautyLeadPage.loadMobilePage(landingPageValues.get("Prod_Url_17"));
			}
			Cookie cookie = driver.manage().getCookieNamed("cookieid");
			sCookieID = cookie.getValue();
			BeautyLeadPage.selectCity(landingPageValues.get("cityName"));
			BeautyLeadPage.setText_CustomerName(landingPageValues.get("custName"));
			BeautyLeadPage.setText_CustomerMobileNum(landingPageValues.get("custMobileNum"));
			BeautyLeadPage.click_SubmitBtnMobile_17();
		} catch (Throwable t) {
			BeautyLeadPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for verifying the parameters of the URL in the SEM table in the DB and checking the LeadStatus .
	 */  
	@Test(groups = { "p1" }, priority = 2,dependsOnMethods = "testHousejoyBeautyLandingPage", dataProviderClass = HousejoyBeautyPageData.class, dataProvider = "SemTableValues")
	public void testHousejoyBeautySemTrackingTable(Map<String,String> semTableValues) throws Throwable {
		try {
			
			BeautyLeadPage.validateSemTableValues(sCookieID,semTableValues);
			//PlumbingMainPage.validateLeadStatusFromDB( semTableValues.get("columnName"),sCookieID,semTableValues.get("columnValue"));

		} catch (Throwable t) {
			BeautyLeadPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for testing the lead page of Beauty
	 *       This is dependent on landing Page test method.
	 */
	@Test(groups = {"p1" }, priority = 3, dependsOnMethods = "testHousejoyBeautyLandingPage", dataProviderClass = HousejoyBeautyPageData.class, dataProvider = "BeautyLeadBookingPage_10")
	public void testHousejoyBeautyBookPage(Map<String,String> LeadBookingPageValues) throws Throwable {
		try {

			//BeautyLeadBookPage.validateThankUHeader(LeadBookingPageValues.get("verifyPage"));
			BeautyLeadBookPage.selectSubServiceButtonForMobile();
			BeautyLeadBookPage.selectDateFromDatePickerForMobile();
			BeautyLeadBookPage.selectTime(LeadBookingPageValues.get("bookingTime"));
			BeautyLeadBookPage.EnterLocation(LeadBookingPageValues.get("location"));
			BeautyLeadBookPage.setText_CustomerAddress(LeadBookingPageValues.get("locationAddress"));
			BeautyLeadBookPage.setText_CustomerEmail(LeadBookingPageValues.get("custEmail"));
			BeautyLeadBookPage.setText_CustPinCode(LeadBookingPageValues.get("pinCode"));
			BeautyLeadBookPage.setText_CouponCode(LeadBookingPageValues.get("couponCode"));
			BeautyLeadBookPage.click_ApplyCouponBtn();
			BeautyLeadBookPage.validateAppliedCoupon(LeadBookingPageValues.get("CouponText"));
			BeautyLeadBookPage.click_BookNowBtn();
		} catch (Throwable t) {
			BeautyLeadBookPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for testing the Thank you page of Beauty.
	 *       This is dependent on Lead Page test method.
	 *       Here we are verifying all the data displayed on the screen with DB Values.
	 */
	@Test(groups = { "p1" }, priority = 5, dependsOnMethods = "testHousejoyBeautyBookPage", dataProviderClass = HousejoyBeautyPageData.class, dataProvider = "VerifyBeautyThankUPage")
	public void testVerifyHousejoyBeautyThankUPageData(String verifyPage) throws Throwable {
		try {

			BeautyThankUPage.validateThankUHeader(verifyPage);
			BeautyThankUPage.validatePageValues();

		} catch (Throwable t) {
			BeautyThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for verifying the values of the Lead Master table in the DB.
	 */
	@Test(groups = { "p1" }, priority = 4, dependsOnMethods = "testHousejoyBeautyBookPage", dataProviderClass = HousejoyBeautyPageData.class, dataProvider = "leadTableValues_10")
	public void testLeadMasterTableValidation(String serviceType, String cityName, String leadStatus, String emailID, String mobileNumber, String userName) throws Throwable {
		try {

			BeautyThankUPage.validateLeadMasterTableValues(sCookieID,serviceType,cityName,leadStatus,emailID,mobileNumber,userName);

		} catch (Throwable t) {
			BeautyThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
}
