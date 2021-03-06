package testNG;

import java.util.Map;

import org.housejoy.common.tools.BasePage;
import org.housejoy.common.tools.CommonTools;
import org.housejoy.common.tools.WebDriverProcessor;
import org.housejoy.data.HousejoyComputerRepairData;
import org.housejoy.pom.computerrepair.ComputerRepairLandingPage_laptopRepair;
import org.housejoy.pom.computerrepair.ComputerRepairLeadBookingPage;
import org.housejoy.pom.computerrepair.ComputerRepairThankUPage;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 
 * @author Kishlay Kumar
 * @Desc  To test if SEM page of Computer Repair is working fine with a usecase.
 * 
 */
public class HousejoyComputerRepairPageTest_laptopRepair {

	public WebDriver driver;
	BasePage MainPage;
	ComputerRepairLandingPage_laptopRepair CompRepairLandingPage;
	ComputerRepairLeadBookingPage CompRepairLeadPage;
	ComputerRepairThankUPage CompRepairThankUPage;
	private static String sCookieID;

	/**
	 * @Desc Initialize the browser and initialize the Elements of Computer Repair pages.
	 */
	@BeforeClass(alwaysRun = true)
	public void setup() {
		this.driver = WebDriverProcessor.selectBrowser();
		CompRepairLandingPage = PageFactory.initElements(driver, ComputerRepairLandingPage_laptopRepair.class);
		CompRepairLeadPage = PageFactory.initElements(driver, ComputerRepairLeadBookingPage.class);
		CompRepairThankUPage = PageFactory.initElements(driver, ComputerRepairThankUPage.class);
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
	@Test(groups = { "p1" }, priority = 1, dataProviderClass = HousejoyComputerRepairData.class, dataProvider = "ComputerRepairLandingValues_laptopRepair")
	public void testComputerRepairLandingPage(Map<String,String> landingPageValues) throws Throwable {
		try {
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("QaSemPages")) {
				CompRepairLandingPage.loadPage(landingPageValues.get("QA_Url"));
			}
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("ProductionSemPages")) {
				CompRepairLandingPage.loadPage(landingPageValues.get("Prod_Url"));
			}
			Cookie cookie = driver.manage().getCookieNamed("cookieid");
			sCookieID = cookie.getValue();
			CompRepairLandingPage.selectCity(landingPageValues.get("cityName"));
			CompRepairLandingPage.setText_CustomerName(landingPageValues.get("custName"));
			CompRepairLandingPage.setText_CustomerMobileNum(landingPageValues.get("custMobileNum"));
			CompRepairLandingPage.setText_CustomerEmail(landingPageValues.get("custEmail"));
			CompRepairLandingPage.click_BookLaptopRepair();

		} catch (Throwable t) {
			CompRepairLandingPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for verifying the parameters of the URL in the SEM table in the DB and checking the LeadStatus .
	 */  
	@Test(groups = { "p1" }, priority = 2, dependsOnMethods = "testComputerRepairLandingPage", dataProviderClass = HousejoyComputerRepairData.class, dataProvider = "SemTableValues")
	public void testHousejoyPlumbingSemTrackingTable(Map<String,String> semTableValues) throws Throwable {
		try {
			
			CompRepairLandingPage.validateSemTableValues(sCookieID,semTableValues);
			//PlumbingMainPage.validateLeadStatusFromDB( semTableValues.get("columnName"),sCookieID,semTableValues.get("columnValue"));

		} catch (Throwable t) {
			CompRepairLandingPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for testing the lead page of Computer Repair.
	 *       This is dependent on landing Page test method.
	 */
	@Test(groups = { "p1" }, priority = 3, dependsOnMethods = "testComputerRepairLandingPage", dataProviderClass = HousejoyComputerRepairData.class, dataProvider = "ComputerRepairLeadPage")
	public void testComputerRepairBookingPage(Map<String,String> LeadBookingPageValues) throws Throwable {
		try {

			CompRepairLeadPage.validateThankUHeader(LeadBookingPageValues.get("verifyPage"));
			CompRepairLeadPage.selectSubServiceButton();
			CompRepairLeadPage.selectDateFromDatePicker();
			CompRepairLeadPage.selectTime(LeadBookingPageValues.get("bookingTime"));
			CompRepairLeadPage.EnterLocation(LeadBookingPageValues.get("location"));
			CompRepairLeadPage.setText_CustomerAddress(LeadBookingPageValues.get("locationAddress"));
			CompRepairLeadPage.setText_CustPinCode(LeadBookingPageValues.get("pinCode"));
			CompRepairLeadPage.setText_SpecialInstructions(LeadBookingPageValues.get("specialInstruction"));
			CompRepairLeadPage.setText_CouponCode(LeadBookingPageValues.get("couponCode"));
			CompRepairLeadPage.click_ApplyCouponBtn();
			CompRepairLeadPage.validateAppliedCoupon(LeadBookingPageValues.get("CouponText"));
			CompRepairLeadPage.click_SendPlumberBtn();

		} catch (Throwable t) {
			CompRepairLeadPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for testing the Thank you page of Computer Repair.
	 *       This is dependent on Lead Page test method.
	 *       Here we are verifying all the data displayed on the screen with DB Values.
	 */
	@Test(groups = { "p1" }, priority = 5, dependsOnMethods = "testComputerRepairBookingPage", dataProviderClass = HousejoyComputerRepairData.class, dataProvider = "ComputerRepairThankUPage")
	public void testVerifyComputerReairThankUPageData(String verifyPage) throws Throwable {
		try {

			CompRepairThankUPage.validateThankUHeader(verifyPage);
			CompRepairThankUPage.validatePageValues();

		} catch (Throwable t) {
			CompRepairThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for verifying the values of the Lead Master table in the DB.
	 */
	@Test(groups = { "p1" }, priority = 4, dependsOnMethods = "testComputerRepairBookingPage", dataProviderClass = HousejoyComputerRepairData.class, dataProvider = "leadTableValues_laptopRepair")
	public void testLeadMasterTableValidation(String serviceType, String cityName, String leadStatus, String emailID, String mobileNumber, String userName) throws Throwable {
		try {

			CompRepairThankUPage.validateLeadMasterTableValues(sCookieID,serviceType,cityName,leadStatus,emailID,mobileNumber,userName);

		} catch (Throwable t) {
			CompRepairThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
}
