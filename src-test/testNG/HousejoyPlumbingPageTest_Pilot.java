package testNG;

import java.util.Map;

import org.housejoy.common.tools.BasePage;
import org.housejoy.common.tools.CommonTools;
import org.housejoy.common.tools.WebDriverProcessor;
import org.housejoy.data.HousejoyPlumbingPageData;
import org.housejoy.pom.plumbing.HousejoyPlumbingPage;
import org.housejoy.pom.plumbing.PlumbingServiceRequestPage;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HousejoyPlumbingPageTest_Pilot {
	
	public WebDriver driver;
    BasePage MainPage;
    HousejoyPlumbingPage PlumbingMainPage;
    PlumbingServiceRequestPage PlumbingReqFeed;
    
	
	 @BeforeClass(alwaysRun = true)
	    public void setup(){
		 this.driver =  WebDriverProcessor.selectBrowser();
		// MainPage = PageFactory.initElements(driver, HousejoyPlumbingPage.class );
		 PlumbingMainPage = PageFactory.initElements(driver, HousejoyPlumbingPage.class );
		 PlumbingReqFeed = PageFactory.initElements(driver, PlumbingServiceRequestPage.class );
	    }

	    @AfterClass(alwaysRun = true)
	    public void teardown(){
	        this.driver.quit();
	    }
	
	    //to get the testmethod names which has failed
	    @Rule public TestName methodName = new TestName();	    
	    
	@Test(groups={"p1"}, dataProviderClass = HousejoyPlumbingPageData.class, dataProvider = "testWithMap")
	public void testHousejoyPlumbingPage(Map<String,String> a)throws Throwable{
		try {
			
			
			PlumbingMainPage.loadPage(a.get("city"));
			PlumbingMainPage.click_CityName();
			/*PlumbingMainPage.validateSelectedCityName(cityName);
			PlumbingMainPage.setText_CustomerName(custName);
			PlumbingMainPage.setText_CustomerMobileNum(custMobileNum);
			PlumbingMainPage.setText_CustomerEmail(custEmail);*/
			PlumbingMainPage.click_BookPlumber();
			
			//PlumbingMainPage.FillDetailsForPlumbing("kishlay");
			
		
			//PlumbingMainPage.SubmitCustDetails();
			//PlumbingReqFeed.validateThankUHeader("Thanks for Being Awesome!");
			//PlumbingReqFeed.FillPlumbingService("");
		
		
		} catch (Throwable t) {
			PlumbingMainPage.takeScreenShot(CommonTools.getProperty("screenshot")+getClass().getSimpleName()+".png");
			//commonTools.takeScreenShot(CommonTools.getProperties("screenshot")+getClass().getSimpleName() + ".png");
			//log.debug("Error occur on " + getClass().getName(), t);
			throw t;
	}
	
	}
	
	@Test(groups={"p1"},dependsOnMethods="testHousejoyPlumbingPage", dataProviderClass = HousejoyPlumbingPageData.class, dataProvider = "plumbingservicereq")
	public void testHousejoyPlumbingBookPage(String verifyPage,String subService,String bookingTime,String location,String locationAddress,String pinCode)throws Throwable{
		try {
			
			
					
			//PlumbingMainPage.FillDetailsForPlumbing("kishlay");
			//PlumbingMainPage.SubmitCustDetails();
			
			//PlumbingReqFeed.validateThankUHeader("Thanks for Being Awesome!");
			//PlumbingReqFeed.FillPlumbingService("");
			
			PlumbingReqFeed.validateThankUHeader(verifyPage);
			PlumbingReqFeed.selectSubServiceButton();
			PlumbingReqFeed.selectDateFromDatePicker();
			//PlumbingReqFeed.selectTime(bookingTime);
			PlumbingReqFeed.EnterLocation(location);
			PlumbingReqFeed.setText_CustomerAddress(locationAddress);
			PlumbingReqFeed.setText_CustPinCode(pinCode);
			PlumbingReqFeed.click_SendPlumberBtn();
		
		
		} catch (Throwable t) {
			PlumbingMainPage.takeScreenShot(CommonTools.getProperty("screenshot")+getClass().getSimpleName()+".png");
			//commonTools.takeScreenShot(CommonTools.getProperties("screenshot")+getClass().getSimpleName() + ".png");
			//log.debug("Error occur on " + getClass().getName(), t);
			t.getStackTrace();
			throw t;
	}
	
	}
}
