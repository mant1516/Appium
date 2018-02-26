package testNG;

import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.BeforeClass;

public class SampleTestNG {
  @Test
  public void f() {
  }
  
  public static void main(String args[]) {
      try {
    	  SelectDate();
    	/*  SimpleDateFormat format = new SimpleDateFormat("EE");
          Date curDate = new Date(); 
          
          Calendar c = Calendar.getInstance();
          c.setTime(curDate);
          System.out.println(curDate);
          
          if(format.format(curDate)!="Sat"){
          	c.add(Calendar.DATE, 8);
          }
          else{
          	c.add(Calendar.DATE, 9);
          }
          Date newDate = c.getTime();
          
          //int Date=newDate.getDate(); '2015-11-02 10:00:00'
          
          String sDate=Integer.toString(newDate.getDate());
          System.out.println((newDate));
         System.out.println(format.format(newDate));
         
         String dateStr = "2015-11-02 10:00:00";
         
         DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Date d2 = df2.parse(dateStr);
         SimpleDateFormat  format1= new SimpleDateFormat("MMM-dd-yyyy");
         System.out.println((format1.format(d2)));
         String dbDate=format1.format(d2).toString()+" 10:00 AM - 11:00 AM";
         System.out.println(dbDate);*/
      }
      
      catch (Exception e) {
         System.out.println("Got an exception!");
      }
   }
  
  public static void SelectDate() {
		/*// Format to get the day of the date
		SimpleDateFormat format = new SimpleDateFormat("EE");
		// Date curDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.WEEK_OF_YEAR, 1);
		//calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date increasedDate = calendar.getTime();
		System.out.println(increasedDate);
		if (format.format(increasedDate).equals("Sun")) {
			calendar.add(Calendar.DATE, 1);
		}

		Date newDate = calendar.getTime();

		// int Date=newDate.getDate();

		String sDate = Integer.toString(newDate.getDate());*/
		
		
		
		SimpleDateFormat format = new SimpleDateFormat("EE");
		Date curDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate);
		System.out.println(curDate);
		//calendar.add(Calendar.MONTH, 1);
		//calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.add(Calendar.DATE, 11);
		
		Date increasedDate = calendar.getTime();

		if (format.format(increasedDate).equals("Sun")) {
			calendar.add(Calendar.DATE, 1);
			increasedDate = calendar.getTime();
		}

		 

		// int Date=newDate.getDate();

		String sDate = Integer.toString(increasedDate.getDate());
		System.out.println(increasedDate);
  }
  
  
  @BeforeClass
  public void beforeClass() {
  }

}
