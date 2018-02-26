package org.housejoy.data;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.testng.annotations.DataProvider;

/**
 * 
 * @author Kishlay Kumar
 * @Desc  To Provide data for Computer Repair Test Class.
 * 
 */
public class HousejoyHomeCleaningPageData {

    // Args: URL, TITLE OF PAGE
    @DataProvider(name = "HomeCleaningLandingValues")
    public static Object[][] entryPage(){
    	HashMap<String, String> map = new HashMap<String, String>();

		map.put("QA_Url", "http://qasem.housejoy.in/promotions/home-cleaning?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
     	map.put("Prod_Url", "http://clean.cleanjoy.in/promotions/home-cleaning?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
		map.put("cityName", "Bangalore");
		map.put("custName", "Auto Homecleaning");
		map.put("custMobileNum", "1234512345");
		map.put("custEmail", "test123@housejoy.in");

		return new Object[][] { { map } };
		
    }

    @DataProvider(name = "HomeCleaningLandingValues_1")
    public static Object[][] entryPage_1(){
    	HashMap<String, String> map = new HashMap<String, String>();

		map.put("QA_Url", "http://qasem.housejoy.in/promotions/home-cleaning/1?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
     	map.put("Prod_Url", "http://clean.cleanjoy.in/promotions/home-cleaning/1?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
		map.put("cityName", "Bangalore");
		map.put("custName", "Auto Homecleaning");
		map.put("custMobileNum", "1234512345");
		map.put("custEmail", "test123@housejoy.in");

		return new Object[][] { { map } };
		
    }
    
 // To send Map as an object
 	@DataProvider(name = "SemTableValues")
 	public static Object[][] testWithMap() {
 		Map<String, String> map = addValues();
 		return new Object[][] { { map } };
 	}

 	// Method to create a Map with data
 	private static Map<String, String> addValues() {
 		HashMap<String, String> map = new HashMap<String, String>();

 		map.put("utm_source", "INTERNAL_BOT");
 		map.put("utm_medium", "CPC");
 		map.put("utm_content", "TEST_CONTENT");
 		map.put("utm_campaign", "TESTCAMPAIGN");
 		map.put("account", "TEST_ACCOUNT");
 		map.put("campaign", "TEST_CAMPAIGN");
 		map.put("adgroup", "TEST_ADDGROUP");
 		map.put("city", "bangalore");
 		map.put("columnName", "lead_status");
 		map.put("columnValue", "NEW");

 		return map;
 	}
 	
    @DataProvider(name = "HomeCleaningBookingPage")
    public static Object[][] leadRequest() {
        
    	HashMap<String, String> map = new HashMap<String, String>();

		map.put("verifyPage", "Thanks for Being Awesome!\n\nYou are just one step away from a sparkling clean home!");
		map.put("verifyMobilePage", "You are just one step away from a sparkling clean home!");
	   	map.put("bookingTime", "1");
		map.put("location", "BTM Layout");
		map.put("locationAddress", "BTM Layout 2nd Stage");
		map.put("pinCode", "560068");
		map.put("couponCode", "autotest1");
		map.put("CouponText", "coupon applied");

		return new Object[][] { { map } };
    	
    }

    @DataProvider(name = "HomeCleaningThankUPage")
    public static Object[][] verifyLeadRequestData() {
        
        return new Object[][] {
        	{"Thank you for choosing Housejoy. Please find the job details below. We will share the service provider details soon.", 
            },

        };
    }
    
 	@DataProvider(name = "leadTableValues")
    public static Object[][] LeadTableValues(){
        return new Object[][] {
                {"Home Cleaning",
                 "Bangalore",
                 "Booked",
                 "test123@housejoy.in",
                 "1234512345",
                 "Auto Homecleaning" },

		};
    }
    
}
