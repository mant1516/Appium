package mobileWeb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.housejoy.common.tools.CommonTools;
import org.housejoy.data.HousejoyBeautyPageData;
import org.housejoy.pom.beauty.BeautyLandingPage_10;
import org.housejoy.pom.beauty.BeautyLeadBookingPage;
import org.housejoy.pom.beauty.BeautyThankUPage;
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
 * @Desc  To test if SEM page of Beauty is working fine with a usecase.
 * 
 */
public class Appium_HousejoyBeautyPageTest_9 {
	
	public WebDriver driver;
	BeautyLandingPage_10 BeautyLeadPage;
	BeautyLeadBookingPage BeautyLeadBookPage;
	BeautyThankUPage BeautyThankUPage;
	private static String sCookieID;
	
	
	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws Exception 
	 * @Desc Initialize the browser and initialize the Elements of Beauty pages.
	 */
	
		
		@BeforeClass(alwaysRun = true)
		public void setUp() throws FileNotFoundException, IOException{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("device", CommonTools.getProperty("device"));
			capabilities.setCapability("deviceName", CommonTools.getProperty("deviceName"));
			capabilities.setCapability("platformName", CommonTools.getProperty("platformName"));
			capabilities.setCapability(CapabilityType.BROWSER_NAME, CommonTools.getProperty("BROWSER_NAME")); //Name of mobile web browser to automate. Should be an empty string if automating an app instead.
			capabilities.setCapability(CapabilityType.VERSION, CommonTools.getProperty("Android_Version"));
			//capabilities.setCapability(CapabilityType.PLATFORM, "Android");
			//capabilities.setCapability("udid", "192.168.1.4:5555"); 
			/*capabilities.setCapability("appPackage", "com.opera.mini.android");
			capabilities.setCapability("appActivity", "com.opera.mini.android.Browser");*/
			capabilities.setCapability("appPackage", CommonTools.getProperty("appPackage")); //For Chrome Browser
			capabilities.setCapability("appActivity", CommonTools.getProperty("appActivity")); //For Chrome Browser
			 /*capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
			   capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome"); 
			   capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Nexus 4");
			   capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"5.1.1");*/
			driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		
		
		/*// set up appium and tell from where it can install the apk file from
		// computer to device
		File appDir = new File("D:\\Apks\\");
		File app = new File(appDir, "com.android.chrome.apk");
		// Very important properties you need for Appium to work, change as per
		// SDK and device name
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("device", "Android");
		capabilities.setCapability("deviceName", "Nexus 4");
		capabilities.setCapability("platformName", "Android");
		// You need to have this sdk installed for Appium to work
		capabilities.setCapability("platformVersion", "5.1.1");
		capabilities.setCapability("app", app.getAbsolutePath());
		// The URL where the hub will start
		this.driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);*/
		//this.driver = WebDriverProcessor.selectBrowser();
		
		BeautyLeadPage = PageFactory.initElements(driver, BeautyLandingPage_10.class);
		BeautyLeadBookPage = PageFactory.initElements(driver, BeautyLeadBookingPage.class);
		BeautyThankUPage = PageFactory.initElements(driver, BeautyThankUPage.class);
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
	@Test(groups = { "p1" }, dataProviderClass = HousejoyBeautyPageData.class, dataProvider = "BeautyLandingPageValues_9", priority = 1)
	public void testHousejoyBeautyLandingPage(Map<String,String> landingPageValues) throws Throwable {
		try {
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("QaSemPages")) {
				//BeautyLeadPage.loadMobilePage(landingPageValues.get("QA_Url"));
				BeautyLeadPage.loadMobilePage("http://qasem.housejoy.in/promotions/beauty/10?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
			}
			if (CommonTools.getProperty("SEMSuiteName").contentEquals("ProductionSemPages")) {
				BeautyLeadPage.loadMobilePage(landingPageValues.get("Prod_Url"));
			}
			Cookie cookie = driver.manage().getCookieNamed("cookieid");
			sCookieID = cookie.getValue();
			BeautyLeadPage.selectCity(landingPageValues.get("cityName"));
			BeautyLeadPage.setText_CustomerName(landingPageValues.get("custName"));
			BeautyLeadPage.setText_CustomerMobileNum(landingPageValues.get("custMobileNum"));
			Thread.sleep(2000);
			BeautyLeadPage.click_SubmitBtn();
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

			//BeautyLeadBookPage.validateThankUHeader(verifyPage);
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
