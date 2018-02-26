package org.housejoy.data;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;

/**
 * 
 * @author Kishlay Kumar
 * @Desc  To Provide data for Appliances Page Test Class.
 * 
 */
public class HousejoyAppliancesPageData {
	
	//public static String suiteName;
	
    // Args: URL, TITLE OF PAGE
    @DataProvider(name = "ApplianceLandingValues_Param")
    public static Object[][] entryPage_1(Method m) {
    	
    	if(m.getName().endsWith("Production")){
    		return new Object[][] {
            	
                {"http://appliances.appliancejoy.in/promotions/appliances/1?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore",
                 "Bangalore", 
                 "Auto Appliances",
                 "1234512345",
                 "test123@housejoy.in"},
                
        };
    	}
    	else {
    		return new Object[][] {
            	
                {"http://qasem.housejoy.in/promotions/appliances/1?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore",
                 "Bangalore", 
                 "Auto Appliances",
                 "1234512345",
                 "test123@housejoy.in"},
                
        };
		}
    	
    	/*return new Object[][] {
        	
            {"http://qasem.housejoy.in/promotions/appliances/1?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore",
             "Bangalore", 
             "Auto Appliances",
             "1234512345",
             "test123@housejoy.in"},
            
    };*/
    	}
    @DataProvider(name = "ApplianceLandingValues_1")
	public static Object[][] testWithMap_1() {
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("QA_Url", "http://qasem.housejoy.in/promotions/appliances/1?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
     	map.put("Prod_Url", "http://appliances.appliancejoy.in/promotions/appliances/1?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
		map.put("cityName", "Bangalore");
		map.put("custName", "Auto Appliances");
		map.put("custMobileNum", "1234512345");
		map.put("custEmail", "test123@housejoy.in");

		return new Object[][] { { map } };
    }
    
    @DataProvider(name = "ApplianceLandingValues_2")
   	public static Object[][] testWithMap_2() {
   		HashMap<String, String> map = new HashMap<String, String>();

   		map.put("QA_Url", "http://qasem.housejoy.in/promotions/appliances/2?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
        map.put("Prod_Url", "http://appliances.appliancejoy.in/promotions/appliances/2?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
   		map.put("cityName", "Bangalore");
   		map.put("custName", "Auto Appliances");
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
 	
    @DataProvider(name = "ApplianceLeadBookPage")
    public static Object[][] leadRequest() {
    	HashMap<String, String> map = new HashMap<String, String>();
    	
    	map.put("verifyPage", "Thanks for Being Awesome!\n\nYou are just one step away from fixing your home appliances");
    	map.put("verifyMobilePage", "You are just one step away from fixing your home appliances");
    	map.put("bookingTime", "1");
		map.put("location", "Domlur");
		map.put("locationAddress", "Domlur Defence Colony");
		map.put("custEmail", "test123@housejoy.in");
		map.put("specialInstruction", "AC water leakage and Voltage problem");
		map.put("pinCode", "560071");
		map.put("couponCode", "autotest1");
		map.put("CouponText", "coupon applied");
		
		return new Object[][] { { map } };
        /*return new Object[][] {
                {"Thanks for Being Awesome!\n\nYou are just one step away from fixing your home appliances", 
                 1,
                 "Domlur",
                 "Domlur Defence Colony",
                 "560071",
                 "AC water leakage and Voltage problem",
                 "autotest1",
                 "coupon applied"},

        };*/
    }

    @DataProvider(name = "ApplianceThankUPage")
    public static Object[][] verifyLeadRequestData() {
        
        return new Object[][] {
        	{"Thank you for choosing Housejoy. Please find the job details below. We will share the service provider details soon.", 
            },

        };
    }
    
 	@DataProvider(name = "leadTableValues")
    public static Object[][] LeadTableValues(){
        return new Object[][] {
                {"Appliances",
                 "Bangalore",
                 "Booked",
                 "test123@housejoy.in",
                 "1234512345",
                 "Auto Appliances" },

		};
    }
    
 	@DataProvider(name = "leadTableValues_2")
    public static Object[][] LeadTableValues_2(){
        return new Object[][] {
                {"Appliances",
                 "Bangalore",
                 "Booked",
                 "",
                 "1234512345",
                 "Auto Appliances" },

		};
    }
 	 @DataProvider(name = "ApplianceLandingValues_acRepair")
     public static Object[][] entryPage_acRepair(){

 		HashMap<String, String> map = new HashMap<String, String>();

 		map.put("QA_Url", "http://qasem.housejoy.in/promotions/appliances/ac-repair?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
      	map.put("Prod_Url", "http://appliances.appliancejoy.in/promotions/appliances/ac-repair?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
 		map.put("cityName", "Bangalore");
 		map.put("custName", "Auto Acrepair");
 		map.put("custMobileNum", "1234512345");
 		map.put("custEmail", "test123@housejoy.in");

 		return new Object[][] { { map } };
 		
     }
 	 
 	@DataProvider(name = "leadTableValues_acRepair")
    public static Object[][] LeadTableValues_acRepair(){
        return new Object[][] {
                {"Appliances",
                 "Bangalore",
                 "Booked",
                 "test123@housejoy.in",
                 "1234512345",
                 "Auto Acrepair" },

		};
    }
 	
 	@DataProvider(name = "ApplianceLandingValues")
    public static Object[][] entryPage(){
 		HashMap<String, String> map = new HashMap<String, String>();

 		map.put("QA_Url", "http://qasem.housejoy.in/promotions/appliances?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
      	map.put("Prod_Url", "http://appliances.appliancejoy.in/promotions/appliances?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
 		map.put("cityName", "Bangalore");
 		map.put("custName", "Auto Appliances");
 		map.put("custMobileNum", "1234512345");
 		map.put("custEmail", "test123@housejoy.in");

 		return new Object[][] { { map } };
 	}
	 
	
}
