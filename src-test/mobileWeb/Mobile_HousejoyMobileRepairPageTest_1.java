package mobileWeb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Map;

import org.housejoy.common.tools.BasePage;
import org.housejoy.common.tools.CommonTools;
import org.housejoy.common.tools.WebDriverProcessor;
import org.housejoy.data.HousejoyMobileRepairData;
import org.housejoy.pom.laundry.LaundryLandingPage_2;
import org.housejoy.pom.laundry.LaundryLeadBookingPage;
import org.housejoy.pom.laundry.LaundryThankUPage;
import org.housejoy.pom.mobilerepair.MobileRepairLandingPage_1;
import org.housejoy.pom.mobilerepair.MobileRepairLeadBookingPage;
import org.housejoy.pom.mobilerepair.MobileRepairThankUPage;
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
 * @Desc  To test if SEM page of Mobile Repair is working fine with a usecase.
 * 
 */
public class Mobile_HousejoyMobileRepairPageTest_1 {

	public WebDriver driver;
	MobileRepairLandingPage_1 MobRepairLandingPage;
	MobileRepairLeadBookingPage MobRepairLeadPage;
	MobileRepairThankUPage MobRepairThankUPage;
	private static String sCookieID;

	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @Desc Initialize the browser and initialize the Elements of Mobile Repair pages.
	 */
	@BeforeClass(alwaysRun = true)
	public void setup() throws FileNotFoundException, IOException {
		try {

			DesiredCapabilities capabilities = BasePage.getDesiredCapabilitiesforMobile();

			driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

			MobRepairLandingPage = PageFactory.initElements(driver, MobileRepairLandingPage_1.class);
			MobRepairLeadPage = PageFactory.initElements(driver, MobileRepairLeadBookingPage.class);
			MobRepairThankUPage = PageFactory.initElements(driver, MobileRepairThankUPage.class);
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
	 * @Desc Test method is used for testing the landing page of Computer Repair.
	 */
	@Test(groups = { "p1" }, priority = 1, dataProviderClass = HousejoyMobileRepairData.class, dataProvider = "MobileRepairLandingValues_1")
	public void testMobileRepairLandingPage(Map<String,String> landingPageValues) throws Throwable {
		try {
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("QaSemPages")) {
				MobRepairLandingPage.loadMobilePage(landingPageValues.get("QA_Url"));
			}
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("ProductionSemPages")) {
				MobRepairLandingPage.loadMobilePage(landingPageValues.get("Prod_Url"));
			}
			Cookie cookie = driver.manage().getCookieNamed("cookieid");
			sCookieID = cookie.getValue();
			MobRepairLandingPage.selectCity(landingPageValues.get("cityName"));
			MobRepairLandingPage.setText_CustomerName(landingPageValues.get("custName"));
			MobRepairLandingPage.setText_CustomerMobileNum(landingPageValues.get("custMobileNum"));
			MobRepairLandingPage.click_BookNowBtnMobile();

		} catch (Throwable t) {
			MobRepairLandingPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for verifying the parameters of the URL in the SEM table in the DB and checking the LeadStatus .
	 */  
	@Test(groups = { "p1" }, priority = 2, dependsOnMethods = "testMobileRepairLandingPage", dataProviderClass = HousejoyMobileRepairData.class, dataProvider = "SemTableValues")
	public void testHousejoyPlumbingSemTrackingTable(Map<String,String> semTableValues) throws Throwable {
		try {
			
			MobRepairLandingPage.validateSemTableValues(sCookieID,semTableValues);
			//PlumbingMainPage.validateLeadStatusFromDB( semTableValues.get("columnName"),sCookieID,semTableValues.get("columnValue"));

		} catch (Throwable t) {
			MobRepairLandingPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for testing the lead page of Mobile Repair.
	 *       This is dependent on landing Page test method.
	 */
	@Test(groups = { "p1" }, priority = 3, dependsOnMethods = "testMobileRepairLandingPage", dataProviderClass = HousejoyMobileRepairData.class, dataProvider = "MobileRepairLeadPage")
	public void testMobileRepairBookingPage(Map<String,String> LeadBookingPageValues) throws Throwable {
		try {

			MobRepairLeadPage.validateThankUHeader_1(LeadBookingPageValues.get("verifyMobilePage"));
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("QaSemPages")) {
				MobRepairLeadPage.selectSubServiceButtonForQAMobile();
			}
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("ProductionSemPages")) {
				MobRepairLeadPage.selectSubServiceButtonForProdMobile();
			}
			MobRepairLeadPage.selectDateFromDatePickerForMobile();
			MobRepairLeadPage.selectTime(LeadBookingPageValues.get("bookingTime"));
			MobRepairLeadPage.EnterLocation(LeadBookingPageValues.get("location"));
			MobRepairLeadPage.setText_CustomerAddress(LeadBookingPageValues.get("locationAddress"));
			MobRepairLeadPage.setText_CustomerEmailId(LeadBookingPageValues.get("custEmail"));
			MobRepairLeadPage.setText_CustPinCode(LeadBookingPageValues.get("pinCode"));
			MobRepairLeadPage.setText_MobileBrand(LeadBookingPageValues.get("mobileBrand"));
			MobRepairLeadPage.setText_MobileModel(LeadBookingPageValues.get("mobileModel"));
			MobRepairLeadPage.setText_SpecialInstructions_1(LeadBookingPageValues.get("specialInstruction"));			
			MobRepairLeadPage.setText_CouponCode(LeadBookingPageValues.get("couponCode"));
			MobRepairLeadPage.click_ApplyCouponBtn();
			MobRepairLeadPage.validateAppliedCoupon(LeadBookingPageValues.get("CouponText"));
			MobRepairLeadPage.click_ConfirmBookingBtn_1();

		} catch (Throwable t) {
			MobRepairLeadPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for testing the Thank you page of Mobile Repair.
	 *       This is dependent on Lead Page test method.
	 *       Here we are verifying all the data displayed on the screen with DB Values.
	 */
	@Test(groups = { "p1" }, priority = 5, dependsOnMethods = "testMobileRepairBookingPage", dataProviderClass = HousejoyMobileRepairData.class, dataProvider = "MobileRepairThankUPage")
	public void testVerifyComputerReairThankUPageData(String verifyPage) throws Throwable {
		try {

			MobRepairThankUPage.validateThankUHeader(verifyPage);
			MobRepairThankUPage.validatePageValues();

		} catch (Throwable t) {
			MobRepairThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for verifying the values of the Lead Master table in the DB.
	 */
	@Test(groups = { "p1" }, priority = 4, dependsOnMethods = "testMobileRepairBookingPage", dataProviderClass = HousejoyMobileRepairData.class, dataProvider = "leadTableValues_1")
	public void testLeadMasterTableValidation(String serviceType, String cityName, String leadStatus, String emailID, String mobileNumber, String userName) throws Throwable {
		try {

			MobRepairThankUPage.validateLeadMasterTableValues(sCookieID,serviceType,cityName,leadStatus,emailID,mobileNumber,userName);

		} catch (Throwable t) {
			MobRepairThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
}
