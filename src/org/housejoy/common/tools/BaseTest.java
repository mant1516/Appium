package org.housejoy.common.tools;


import org.testng.log4testng.Logger;

public class BaseTest {
	
	/*
	 * To access all the generic methods 
	 */
	protected CommonTools commonTools = null;
	private final StringBuilder verificationErrors = new StringBuilder();
	protected Logger log = Logger.getLogger(getClass());
	
	public BaseTest(){
		commonTools= new CommonTools();
	}
	
	/*public BaseTest(ApplicationLink applicationLink){
		commonTools= new CommonTools(applicationLink);
	}*/

	public static boolean exceptionoccur = true;
	
	/*@AfterMethod
	@AfterClass
	public void tearDown() throws Throwable {		
		log.info("TestCaseName::" + getClass().getName());
		//portalUtil.closeWebDriver();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {			
			Assert.fail(verificationErrorString);
		}
		String status;
		if(!exceptionoccur)
		{
			status = "FAILED";
			exceptionoccur=true;
		}
		else
		{
			status="PASSED";
		}
		CommonTools.Logs(getClass().getSimpleName()+","+getClass().getMethodName(), status);
	}*/
}
