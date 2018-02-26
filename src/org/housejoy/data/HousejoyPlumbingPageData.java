package org.housejoy.data;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.testng.annotations.DataProvider;

/**
 * Created by Kishlay on 9/10/2015.
 */
public class HousejoyPlumbingPageData {

    // Args: URL, TITLE OF PAGE
    @DataProvider(name = "pageValues")
    public static Object[][] entryPage(){
    	HashMap<String, String> map = new HashMap<String, String>();

		map.put("QA_Url_6", "http://qasem.housejoy.in/promotions/plumbing/6?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
     	map.put("Prod_Url_6", "http://plumbing.housejoy.in/promotions/plumbing/6/?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
     	map.put("QA_Url_7", "http://qasem.housejoy.in/promotions/plumbing/7?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
     	map.put("Prod_Url_7", "http://plumbing.housejoy.in/promotions/plumbing/7/?utm_source=INTERNAL_BOT&utm_medium=CPC&utm_term=TEST_TERM&utm_content=TEST_CONTENT&utm_campaign=TESTCAMPAIGN&account=TEST_ACCOUNT&campaign=TEST_CAMPAIGN&adgroup=TEST_ADDGROUP&city=Bangalore");
		map.put("cityName", "Bangalore");
		map.put("custName", "Auto Plumbing");
		map.put("custMobileNum", "1234512345");
		map.put("custEmail", "test123@housejoy.in");

		return new Object[][] { { map } };
    }

    @DataProvider(name = "semTableValues")
    public static Object[][] semTrackingTableValues(){
        return new Object[][] {
                {"INTERNAL_BOT",
                 "CPC",
                 "TEST_CONTENT",
                 "TESTCAMPAIGN",
                 "TEST_ACCOUNT",
                 "TEST_CAMPAIGN",
                 "TEST_ADDGROUP",
                 "bangalore"},
                /*{"http://www.google.com", "Google"},
                {"http://www.yahoo.com", "Yahoo"}*/
        };
    }
    
    @DataProvider(name = "plumbingservicereq")
    public static Object[][] leadRequest() {
        
    	HashMap<String, String> map = new HashMap<String, String>();

		map.put("verifyPage", "Thanks for Being Awesome!\n\nYou are just one step away from fixing your plumbing problems");
		map.put("verifyMobilePage", "You are just one step away from fixing your plumbing problems");
	   	map.put("bookingTime", "1");
		map.put("location", "Marathalli");
		map.put("locationAddress", "Marathalli Bridge");
		map.put("custEmail", "test123@housejoy.in");
		map.put("pinCode", "560037");
		map.put("couponCode", "autotest1");
		map.put("CouponText", "coupon applied");

		return new Object[][] { { map } };
    }

    @DataProvider(name = "verifyPlumbingData")
    public static Object[][] verifyLeadRequestData() {
        
        return new Object[][] {
                {"Thank you for choosing Housejoy. Please find the job details below. We will share the service provider details soon.", 
                 },

        };
    }
    
    
    @DataProvider(name = "leadTableValues")
    public static Object[][] LeadTableValues(){
        return new Object[][] {
                {"Plumbing",
                 "Bangalore",
                 "Booked",
                 "test123@housejoy.in",
                 "1234512345",
                 "Auto Plumbing"},
                /*{"http://www.google.com", "Google"},
                {"http://www.yahoo.com", "Yahoo"}*/
        };
    }
    
    @DataProvider(name = "leadTableValues_7")
    public static Object[][] LeadTableValues_7(){
        return new Object[][] {
                {"Plumbing",
                 "Bangalore",
                 "Booked",
                 "",
                 "1234512345",
                 "Auto Plumbing"},
                /*{"http://www.google.com", "Google"},
                {"http://www.yahoo.com", "Yahoo"}*/
        };
    }
    
    
    @DataProvider(name="signup")
    public static Object[][] signup(){
        return new Object[][] {
                {"QA", "Automan", "validEmail@qaautoman.com"},
                {"@#$%@#", "@#!#@$T%%%", "validEmail@qaautoman.com"},
                {"12341", "5234523", "validEmail@qaautoman.com"},
        };
    }
    
    //To send Map as an object 
    @DataProvider(name="testWithMap")
    public static Object[][] testWithMap(){
    Map<String, String> map=addValues();
	return new Object[][] { { map}};
    }

    //Method to create a Map with data
	private static Map<String, String> addValues() {
		HashMap<String,String> map = new HashMap<String, String>(); 
		
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
   /* @DataProvider(name="testWithMap")
    public static Object[][] testWithMap(){
    
	return new Object[][] {addValues()};}

	private static Map<String, String>[] addValues() {
		Map<String,String> map = new HashedMap(); 
		
		map.put("url", "http://plumbing.housejoy.in/promotions/plumbing/6");
		map.put("city", "BLR");
		map.put("url", "http://plumbing.housejoy.in/promotions/plumbing/6");
		
Map<String,String> map2 = new HashedMap(); 
		
		map2.put("url", "http://plumbing.housejoy.in/promotions/plumbing/6");
		map2.put("city", "BLR");
		map2.put("url", "http://plumbing.housejoy.in/promotions/plumbing/6");
		
Map<String, String> map3 = new HashedMap(); 
		
		map3.put("url", "http://plumbing.housejoy.in/promotions/plumbing/6");
		map3.put("city", "BLR");
		map3.put("url", "http://plumbing.housejoy.in/promotions/plumbing/6");
		
		Map[] a = {map,map2,map3};
		return a;
	}*/
	
	
	
    
}
