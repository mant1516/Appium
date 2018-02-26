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
public class HousejoyMobileRepairData {

    // Args: URL, TITLE OF PAGE
    @DataProvider(name = "MobileRepairLandingValues")
    public static Object[][] entryPage(){
    	HashMap<String, String> map = new HashMap<String, String>();

		map.put("QA_Url", "http://qasem.housejoy.in/promotions/mobile-repair?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
     	map.put("Prod_Url", "http://mobile.mobilejoy.in/promotions/mobile-repair?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
		map.put("cityName", "Bangalore");
		map.put("custName", "Auto MobileRepair");
		map.put("custMobileNum", "1234512345");
		map.put("custEmail", "test123@housejoy.in");

		return new Object[][] { { map } };
		
    }
    
    @DataProvider(name = "MobileRepairLandingValues_1")
    public static Object[][] entryPage_1(){
    	HashMap<String, String> map = new HashMap<String, String>();

		map.put("QA_Url", "http://qasem.housejoy.in/promotions/mobile-repair/1?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
     	map.put("Prod_Url", "http://mobile.mobilejoy.in/promotions/mobile-repair/1?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
		map.put("cityName", "Bangalore");
		map.put("custName", "Auto MobileRepair");
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
 	
    @DataProvider(name = "MobileRepairLeadPage")
    public static Object[][] leadRequest() {
    	HashMap<String, String> map = new HashMap<String, String>();
    	
    	map.put("verifyPage", "Thank you for choosing us.\nPlease fill the form to schedule a pickup.");
    	map.put("verifyPage_1", "Thanks for Being Awesome!\n\nYou are just one step away from fixing your mobile devices");
    	map.put("verifyMobilePage", "You are just one step away from fixing your mobile devices");
     	map.put("bookingTime", "1");
		map.put("location", "Domlur");
		map.put("locationAddress", "Domlur Defence Colony");
		map.put("custEmail", "test123@housejoy.in");
		map.put("mobileBrand", "LG");
		map.put("mobileModel", "Nexus4");
		map.put("specialInstruction", "Mobile Screen is damaged");
		map.put("pinCode", "560071");
		map.put("couponCode", "autotest1");
		map.put("CouponText", "coupon applied");

		return new Object[][] { { map } };
	}

    @DataProvider(name = "MobileRepairThankUPage")
    public static Object[][] verifyLeadRequestData() {
        
        return new Object[][] {
        	{"Thank you for choosing Housejoy. Please find the job details below. We will share the service provider details soon.", 
            },

        };
    }
    
 	@DataProvider(name = "leadTableValues")
    public static Object[][] LeadTableValues(){
        return new Object[][] {
                {"Mobile Repair",
                 "Bangalore",
                 "Booked",
                 "test123@housejoy.in",
                 "1234512345",
                 "Auto MobileRepair" },

		};
    }
 	
 	@DataProvider(name = "leadTableValues_1")
    public static Object[][] LeadTableValues_1(){
        return new Object[][] {
                {"Mobile Repair",
                 "Bangalore",
                 "Booked",
                 "",
                 "1234512345",
                 "Auto MobileRepair" },

		};
    }
    
}
