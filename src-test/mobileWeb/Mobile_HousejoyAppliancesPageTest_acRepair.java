package mobileWeb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.housejoy.common.tools.CommonTools;
import org.housejoy.common.tools.WebDriverProcessor;
import org.housejoy.data.HousejoyAppliancesPageData;
import org.housejoy.data.HousejoyMobileRepairData;
import org.housejoy.pom.appliances.AppliancesLandingPage_1;
import org.housejoy.pom.appliances.AppliancesLandingPage_acRepair;
import org.housejoy.pom.appliances.AppliancesLeadBookingPage;
import org.housejoy.pom.appliances.AppliancesThankUPage;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 
 * @author Kishlay Kumar
 * @Desc  To test if SEM page of Mobile Repair is working fine with a usecase.
 * 
 */
public class Mobile_HousejoyAppliancesPageTest_acRepair {

	public WebDriver driver;
	AppliancesLandingPage_acRepair ApplianceLandingPage;
	AppliancesLeadBookingPage ApplianceLeadBookPage;
	AppliancesThankUPage ApplianceThankUPage;
	private static String sCookieID;

	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @Desc Initialize the browser and initialize the Elements of Appliance pages.
	 */
	@BeforeClass(alwaysRun = true)
	public void setup() throws FileNotFoundException, IOException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("device", CommonTools.getProperty("device"));
		capabilities.setCapability("deviceName", CommonTools.getProperty("deviceName"));
		capabilities.setCapability("platformName", CommonTools.getProperty("platformName"));
		capabilities.setCapability(CapabilityType.BROWSER_NAME, CommonTools.getProperty("BROWSER_NAME")); //Name of mobile web browser to automate. Should be an empty string if automating an app instead.
		capabilities.setCapability(CapabilityType.VERSION, CommonTools.getProperty("Android_Version"));
		capabilities.setCapability("appPackage", CommonTools.getProperty("appPackage")); //For Chrome Browser
		capabilities.setCapability("appActivity", CommonTools.getProperty("appActivity")); //For Chrome Browser
		 
		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		ApplianceLandingPage = PageFactory.initElements(driver, AppliancesLandingPage_acRepair.class);
		ApplianceLeadBookPage = PageFactory.initElements(driver, AppliancesLeadBookingPage.class);
		ApplianceThankUPage = PageFactory.initElements(driver, AppliancesThankUPage.class);
	}

	/**
	 * @Desc Close the Driver After the testcase has run.
	 */
	@AfterClass(alwaysRun = true)
	public void teardown() {
		this.driver.quit();
	}     
	    
	/**
	 * @Desc Test method is used for testing the landing page of ApplianceLandingPages_1.
	 */
	@Test(groups = { "p1" }, priority = 1, dataProviderClass = HousejoyAppliancesPageData.class, dataProvider = "ApplianceLandingValues_acRepair")
	public void testApplianceLandingPage(Map<String,String> landingPageValues) throws Throwable {
		try {
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("QaSemPages")) {
				ApplianceLandingPage.loadMobilePage(landingPageValues.get("QA_Url"));
			}
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("ProductionSemPages")) {
				ApplianceLandingPage.loadMobilePage(landingPageValues.get("Prod_Url"));
			}
			Cookie cookie = driver.manage().getCookieNamed("cookieid");
			sCookieID = cookie.getValue();
			ApplianceLandingPage.selectCity(landingPageValues.get("cityName"));
			ApplianceLandingPage.setText_CustomerName(landingPageValues.get("custName"));
			ApplianceLandingPage.setText_CustomerMobileNum(landingPageValues.get("custMobileNum"));
			ApplianceLandingPage.setText_CustomerEmail(landingPageValues.get("custEmail"));
			ApplianceLandingPage.click_SubmitBtnMobile();

		} catch (Throwable t) {
			ApplianceLandingPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for verifying the parameters of the URL in the SEM table in the DB and checking the LeadStatus .
	 */  
	@Test(groups = { "p1" }, priority = 2, dependsOnMethods = "testApplianceLandingPage", dataProviderClass = HousejoyAppliancesPageData.class, dataProvider = "SemTableValues")
	public void testHousejoySemTrackingTable(Map<String,String> semTableValues) throws Throwable {
		try {
			
			ApplianceLandingPage.validateSemTableValues(sCookieID,semTableValues);
			//PlumbingMainPage.validateLeadStatusFromDB( semTableValues.get("columnName"),sCookieID,semTableValues.get("columnValue"));

		} catch (Throwable t) {
			ApplianceLandingPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for testing the lead page of Mobile Repair.
	 *       This is dependent on landing Page test method.
	 */
	@Test(groups = { "p1" }, priority = 3, dependsOnMethods = "testApplianceLandingPage", dataProviderClass = HousejoyAppliancesPageData.class, dataProvider = "ApplianceLeadBookPage")
	public void testApplianceBookingPage(Map<String,String> LeadBookingPageValues) throws Throwable {
		try {

			ApplianceLeadBookPage.validateThankUHeader(LeadBookingPageValues.get("verifyMobilePage"));
			ApplianceLeadBookPage.selectSubServiceButtonForMobile();
			ApplianceLeadBookPage.selectDateFromDatePickerForMobile();
			ApplianceLeadBookPage.selectTime(LeadBookingPageValues.get("bookingTime"));
			ApplianceLeadBookPage.EnterLocation(LeadBookingPageValues.get("location"));
			ApplianceLeadBookPage.setText_CustomerAddress(LeadBookingPageValues.get("locationAddress"));
			ApplianceLeadBookPage.setText_CustPinCode(LeadBookingPageValues.get("pinCode"));
			ApplianceLeadBookPage.setText_SpecialInstructions(LeadBookingPageValues.get("specialInstruction"));
			ApplianceLeadBookPage.setText_CouponCode(LeadBookingPageValues.get("couponCode"));
			ApplianceLeadBookPage.click_ApplyCouponBtn();
			ApplianceLeadBookPage.validateAppliedCoupon(LeadBookingPageValues.get("CouponText"));
			ApplianceLeadBookPage.click_BookNowBtn();

		} catch (Throwable t) {
			ApplianceLeadBookPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for testing the Thank you page of Appliances.
	 *       This is dependent on Lead Page test method.
	 *       Here we are verifying all the data displayed on the screen with DB Values.
	 */
	@Test(groups = { "p1" }, priority = 5, dependsOnMethods = "testApplianceBookingPage", dataProviderClass = HousejoyAppliancesPageData.class, dataProvider = "ApplianceThankUPage")
	public void testVerifyApplianceThankUPageData(String verifyPage) throws Throwable {
		try {

			ApplianceThankUPage.validateThankUHeader(verifyPage);
			ApplianceThankUPage.validatePageValues();

		} catch (Throwable t) {
			ApplianceThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for verifying the values of the Lead Master table in the DB.
	 */
	@Test(groups = { "p1" }, priority = 4, dependsOnMethods = "testApplianceBookingPage", dataProviderClass = HousejoyAppliancesPageData.class, dataProvider = "leadTableValues_acRepair")
	public void testLeadMasterTableValidation(String serviceType, String cityName, String leadStatus, String emailID, String mobileNumber, String userName) throws Throwable {
		try {

			ApplianceThankUPage.validateLeadMasterTableValues(sCookieID,serviceType,cityName,leadStatus,emailID,mobileNumber,userName);

		} catch (Throwable t) {
			ApplianceThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
}
