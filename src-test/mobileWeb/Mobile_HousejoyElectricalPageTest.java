package mobileWeb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Map;

import org.housejoy.common.tools.BasePage;
import org.housejoy.common.tools.CommonTools;
import org.housejoy.common.tools.WebDriverProcessor;
import org.housejoy.data.HousejoyElectricalPageData;
import org.housejoy.pom.computerrepair.ComputerRepairLandingPage;
import org.housejoy.pom.computerrepair.ComputerRepairLeadBookingPage;
import org.housejoy.pom.computerrepair.ComputerRepairThankUPage;
import org.housejoy.pom.electrical.ElectricalLandingPage;
import org.housejoy.pom.electrical.ElectricalLeadBookingPage;
import org.housejoy.pom.electrical.ElectricalThankUPage;
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
public class Mobile_HousejoyElectricalPageTest {
	
	public WebDriver driver;
	ElectricalLandingPage ElectricalLeadPage;
	ElectricalLeadBookingPage ElectricalLeadBookPage;
	ElectricalThankUPage ElectricalThankUPage;
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


			ElectricalLeadPage = PageFactory.initElements(driver, ElectricalLandingPage.class);
			ElectricalLeadBookPage = PageFactory.initElements(driver, ElectricalLeadBookingPage.class);
			ElectricalThankUPage = PageFactory.initElements(driver, ElectricalThankUPage.class);
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
	@Test(groups = { "p1" }, dataProviderClass = HousejoyElectricalPageData.class, dataProvider = "ElectricalLandingValues", priority = 1)
	public void testHousejoyElectricalLandingPage(Map<String,String> landingPageValues) throws Throwable {
		try {
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("QaSemPages")) {
				ElectricalLeadPage.loadMobilePage(landingPageValues.get("QA_Url"));
			}
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("ProductionSemPages")) {
				ElectricalLeadPage.loadMobilePage(landingPageValues.get("Prod_Url"));
			}
			Cookie cookie = driver.manage().getCookieNamed("cookieid");
			sCookieID = cookie.getValue();
			ElectricalLeadPage.selectCity(landingPageValues.get("cityName"));
			ElectricalLeadPage.setText_CustomerName(landingPageValues.get("custName"));
			ElectricalLeadPage.setText_CustomerMobileNum(landingPageValues.get("custMobileNum"));
			ElectricalLeadPage.setText_CustomerEmail(landingPageValues.get("custEmail"));
			ElectricalLeadPage.click_SubmitBtnMobile();
		} catch (Throwable t) {
			ElectricalLeadPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for verifying the parameters of the URL in the SEM table in the DB and checking the LeadStatus .
	 */  
	@Test(groups = { "p1" }, priority = 2,dependsOnMethods = "testHousejoyElectricalLandingPage", dataProviderClass = HousejoyElectricalPageData.class, dataProvider = "SemTableValues")
	public void testHousejoySemTrackingTable(Map<String,String> semTableValues) throws Throwable {
		try {
			
			ElectricalLeadPage.validateSemTableValues(sCookieID,semTableValues);
			//PlumbingMainPage.validateLeadStatusFromDB( semTableValues.get("columnName"),sCookieID,semTableValues.get("columnValue"));

		} catch (Throwable t) {
			ElectricalLeadPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for testing the lead page of Beauty
	 *       This is dependent on landing Page test method.
	 */
	@Test(groups = {"p1" }, priority = 3, dependsOnMethods = "testHousejoyElectricalLandingPage", dataProviderClass = HousejoyElectricalPageData.class, dataProvider = "ElectricalBookingPage")
	public void testHousejoyElectricalBookPage(Map<String,String> LeadBookingPageValues) throws Throwable {
		try {

			ElectricalLeadBookPage.validateThankUHeader(LeadBookingPageValues.get("verifyMobilePage"));
			ElectricalLeadBookPage.selectSubServiceButtonForMobile();
			ElectricalLeadBookPage.selectDateFromDatePickerForMobile();
			ElectricalLeadBookPage.selectTime(LeadBookingPageValues.get("bookingTime"));
			ElectricalLeadBookPage.EnterLocation(LeadBookingPageValues.get("location"));
			ElectricalLeadBookPage.setText_CustomerAddress(LeadBookingPageValues.get("locationAddress"));
			ElectricalLeadBookPage.setText_CustPinCode(LeadBookingPageValues.get("pinCode"));
			ElectricalLeadBookPage.setText_CouponCode(LeadBookingPageValues.get("couponCode"));
			ElectricalLeadBookPage.click_ApplyCouponBtn();
			ElectricalLeadBookPage.validateAppliedCoupon(LeadBookingPageValues.get("CouponText"));
			ElectricalLeadBookPage.click_ConfirmBookingBtn();
		} catch (Throwable t) {
			ElectricalLeadBookPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for testing the Thank you page of Beauty.
	 *       This is dependent on Lead Page test method.
	 *       Here we are verifying all the data displayed on the screen with DB Values.
	 */
	@Test(groups = { "p1" }, priority = 5, dependsOnMethods = "testHousejoyElectricalBookPage", dataProviderClass = HousejoyElectricalPageData.class, dataProvider = "ElectricalThankUPage")
	public void testVerifyHousejoyElectricalThankUPageData(String verifyPage) throws Throwable {
		try {

			ElectricalThankUPage.validateThankUHeader(verifyPage);
			ElectricalThankUPage.validatePageValues();

		} catch (Throwable t) {
			ElectricalThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for verifying the values of the Lead Master table in the DB.
	 */
	@Test(groups = { "p1" }, priority = 4, dependsOnMethods = "testHousejoyElectricalBookPage", dataProviderClass = HousejoyElectricalPageData.class, dataProvider = "leadTableValues")
	public void testLeadMasterTableValidation(String serviceType, String cityName, String leadStatus, String emailID, String mobileNumber, String userName) throws Throwable {
		try {

			ElectricalThankUPage.validateLeadMasterTableValues(sCookieID,serviceType,cityName,leadStatus,emailID,mobileNumber,userName);

		} catch (Throwable t) {
			ElectricalThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
}
