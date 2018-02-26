package testNG;

import java.util.Map;

import org.housejoy.common.tools.CommonTools;
import org.housejoy.common.tools.WebDriverProcessor;
import org.housejoy.data.HousejoyComputerRepairData;
import org.housejoy.data.HousejoyMobileRepairData;
import org.housejoy.pom.mobilerepair.MobileRepairLandingPage;
import org.housejoy.pom.mobilerepair.MobileRepairLeadBookingPage;
import org.housejoy.pom.mobilerepair.MobileRepairThankUPage;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
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
public class HousejoyMobileRepairPageTest {

	public WebDriver driver;
	MobileRepairLandingPage MobRepairLandingPage;
	MobileRepairLeadBookingPage MobRepairLeadPage;
	MobileRepairThankUPage MobRepairThankUPage;
	private static String sCookieID;

	/**
	 * @Desc Initialize the browser and initialize the Elements of Mobile Repair pages.
	 */
	@BeforeClass(alwaysRun = true)
	public void setup() {
		this.driver = WebDriverProcessor.selectBrowser();
		MobRepairLandingPage = PageFactory.initElements(driver, MobileRepairLandingPage.class);
		MobRepairLeadPage = PageFactory.initElements(driver, MobileRepairLeadBookingPage.class);
		MobRepairThankUPage = PageFactory.initElements(driver, MobileRepairThankUPage.class);
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
	@Test(groups = { "p1" }, priority = 1, dataProviderClass = HousejoyMobileRepairData.class, dataProvider = "MobileRepairLandingValues")
	public void testMobileRepairLandingPage(Map<String,String> landingPageValues) throws Throwable {
		try {
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("QaSemPages")) {
				MobRepairLandingPage.loadPage(landingPageValues.get("QA_Url"));
			}
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("ProductionSemPages")) {
				MobRepairLandingPage.loadPage(landingPageValues.get("Prod_Url"));
			}
			Cookie cookie = driver.manage().getCookieNamed("cookieid");
			sCookieID = cookie.getValue();
			MobRepairLandingPage.selectCity(landingPageValues.get("cityName"));
			MobRepairLandingPage.setText_CustomerName(landingPageValues.get("custName"));
			MobRepairLandingPage.setText_CustomerMobileNum(landingPageValues.get("custMobileNum"));
			MobRepairLandingPage.setText_CustomerEmail(landingPageValues.get("custEmail"));
			MobRepairLandingPage.click_BookMobileRepair();

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

			MobRepairLeadPage.validateThankUHeader(LeadBookingPageValues.get("verifyPage"));
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("QaSemPages")) {
				MobRepairLeadPage.selectSubServiceButtonQA();
			}
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("ProductionSemPages")) {
				MobRepairLeadPage.selectSubServiceButtonProd();
			}
			//MobRepairLeadPage.selectSubServiceButtonProd();
			MobRepairLeadPage.selectDateFromDatePicker();
			MobRepairLeadPage.selectTime(LeadBookingPageValues.get("bookingTime"));
			MobRepairLeadPage.EnterLocation(LeadBookingPageValues.get("location"));
			MobRepairLeadPage.setText_CustomerAddress(LeadBookingPageValues.get("locationAddress"));
			MobRepairLeadPage.setText_CustPinCode(LeadBookingPageValues.get("pinCode"));
			MobRepairLeadPage.setText_MobileBrand(LeadBookingPageValues.get("mobileBrand"));
			MobRepairLeadPage.setText_MobileModel(LeadBookingPageValues.get("mobileModel"));
			MobRepairLeadPage.setText_SpecialInstructions(LeadBookingPageValues.get("specialInstruction"));			
			MobRepairLeadPage.setText_CouponCode(LeadBookingPageValues.get("couponCode"));
			MobRepairLeadPage.click_ApplyCouponBtn();
			MobRepairLeadPage.validateAppliedCoupon(LeadBookingPageValues.get("CouponText"));
			MobRepairLeadPage.click_ConfirmBookingBtn();

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
	@Test(groups = { "p1" }, priority = 4, dependsOnMethods = "testMobileRepairBookingPage", dataProviderClass = HousejoyMobileRepairData.class, dataProvider = "leadTableValues")
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
