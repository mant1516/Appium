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
import org.housejoy.data.HousejoyFitnessPageData;
import org.housejoy.data.HousejoyPlumbingPageData;
import org.housejoy.pom.beauty.BeautyLandingPage_1;
import org.housejoy.pom.beauty.BeautyLeadBookingPage;
import org.housejoy.pom.beauty.BeautyThankUPage;
import org.housejoy.pom.electrical.ElectricalLandingPage;
import org.housejoy.pom.electrical.ElectricalLeadBookingPage;
import org.housejoy.pom.electrical.ElectricalThankUPage;
import org.housejoy.pom.fitness.FitnessLandingPage;
import org.housejoy.pom.fitness.FitnessLeadBookingPage;
import org.housejoy.pom.fitness.FitnessThankUPage;
import org.housejoy.pom.plumbing.HousejoyPlumbingPage;
import org.housejoy.pom.plumbing.PlumbingServiceRequestPage;
import org.housejoy.pom.plumbing.VerifyPlumbingDetails;
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
public class Mobile_HousejoyFitnessPageTest_1 {
	
	public WebDriver driver;
	FitnessLandingPage FitnessLeadPage;
	FitnessLeadBookingPage FitnessLeadBookPage;
	FitnessThankUPage FitnessThankUPage;
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

			FitnessLeadPage = PageFactory.initElements(driver, FitnessLandingPage.class);
			FitnessLeadBookPage = PageFactory.initElements(driver, FitnessLeadBookingPage.class);
			FitnessThankUPage = PageFactory.initElements(driver, FitnessThankUPage.class);
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
	@Test(groups = { "p1" }, dataProviderClass = HousejoyFitnessPageData.class, dataProvider = "FitnessLandingPageValues_1", priority = 1)
	public void testHousejoyFitnessLandingPage(Map<String,String> landingPageValues) throws Throwable {
		try {
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("QaSemPages")) {
				FitnessLeadPage.loadMobilePage(landingPageValues.get("QA_Url"));
			}
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("ProductionSemPages")) {
				FitnessLeadPage.loadMobilePage(landingPageValues.get("Prod_Url"));
			}
			Cookie cookie = driver.manage().getCookieNamed("cookieid");
			sCookieID = cookie.getValue();
			FitnessLeadPage.click_BookNowBtn();
			FitnessLeadPage.selectCity(landingPageValues.get("cityName"));
			FitnessLeadPage.setText_CustomerName(landingPageValues.get("custName"));
			FitnessLeadPage.setText_CustomerMobileNum(landingPageValues.get("custMobileNum"));
			FitnessLeadPage.setText_CustomerEmail(landingPageValues.get("custEmail"));
			FitnessLeadPage.click_SubmitBtnMobile();
		} catch (Throwable t) {
			FitnessLeadPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	/**
	 * @Desc Test method is used for verifying the parameters of the URL in the SEM table in the DB and checking the LeadStatus .
	 */  
	@Test(groups = { "p1" }, priority = 2,dependsOnMethods = "testHousejoyFitnessLandingPage", dataProviderClass = HousejoyFitnessPageData.class, dataProvider = "SemTableValues")
	public void testHousejoySemTrackingTable(Map<String,String> semTableValues) throws Throwable {
		try {
			
			FitnessLeadPage.validateSemTableValues(sCookieID,semTableValues);
			//PlumbingMainPage.validateLeadStatusFromDB( semTableValues.get("columnName"),sCookieID,semTableValues.get("columnValue"));

		} catch (Throwable t) {
			FitnessLeadPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for testing the lead page of Beauty
	 *       This is dependent on landing Page test method.
	 */
	@Test(groups = {"p1" }, priority = 3, dependsOnMethods = "testHousejoyFitnessLandingPage", dataProviderClass = HousejoyFitnessPageData.class, dataProvider = "FitnessBookingPageValues")
	public void testHousejoyFitnessBookPage(Map<String,String> LeadBookingPageValues) throws Throwable {
		try {
			
			FitnessLeadBookPage.selectSubService(LeadBookingPageValues.get("subService"));
			FitnessLeadBookPage.selectFitnessGoal(LeadBookingPageValues.get("fitnessGoal"));
			FitnessLeadBookPage.selectAge(LeadBookingPageValues.get("age"));
			FitnessLeadBookPage.selectDateFromDatePickerForMobile();
			FitnessLeadBookPage.selectTime(LeadBookingPageValues.get("bookingTime"));
			FitnessLeadBookPage.EnterLocation(LeadBookingPageValues.get("location"));
			FitnessLeadBookPage.setText_CustomerAddress(LeadBookingPageValues.get("locationAddress"));
			FitnessLeadBookPage.setText_CustPinCode(LeadBookingPageValues.get("pinCode"));
			FitnessLeadBookPage.setText_Details(LeadBookingPageValues.get("specialComments"));
			FitnessLeadBookPage.click_ConfirmBookNowBtnMobile();
		} catch (Throwable t) {
			FitnessLeadBookPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for testing the Thank you page of Beauty.
	 *       This is dependent on Lead Page test method.
	 *       Here we are verifying all the data displayed on the screen with DB Values.
	 */
	@Test(groups = { "p1" }, priority = 5, dependsOnMethods = "testHousejoyFitnessBookPage", dataProviderClass = HousejoyFitnessPageData.class, dataProvider = "VerifyFitnessThankUPage")
	public void testVerifyHousejoyFitnessThankUPageData(String verifyPage) throws Throwable {
		try {

			FitnessThankUPage.validateThankUHeader(verifyPage);
			FitnessThankUPage.validatePageValues();

		} catch (Throwable t) {
			FitnessThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
	
	
	/**
	 * @Desc Test method is used for verifying the values of the Lead Master table in the DB.
	 */
	@Test(groups = { "p1" }, priority = 4, dependsOnMethods = "testHousejoyFitnessBookPage", dataProviderClass = HousejoyFitnessPageData.class, dataProvider = "leadTableValues")
	public void testLeadMasterTableValidation(String serviceType, String cityName, String leadStatus, String emailID, String mobileNumber, String userName) throws Throwable {
		try {

			FitnessThankUPage.validateLeadMasterTableValues(sCookieID,serviceType,cityName,leadStatus,emailID,mobileNumber,userName);

		} catch (Throwable t) {
			FitnessThankUPage.takeScreenShot(CommonTools.getProperty("screenshot") + getClass().getSimpleName() + ".png");
			t.getStackTrace();
			throw t;
		}
	}
}
