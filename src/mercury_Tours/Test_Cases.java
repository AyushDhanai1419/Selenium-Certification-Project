package mercury_Tours;



import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;



public class Test_Cases{
	

	CommonDriver cmDriver;
	
	

  @BeforeClass (groups = { "Validate" })
  public void beforeClass() throws Exception {
	  
	  
	  	cmDriver = new CommonDriver("chrome");
		
	  	cmDriver.navigateToFirstUrl("http://newtours.demoaut.com");
		
		
		
  }
  //Test Case 1 [Answer To first part]
  @Test(priority = 1 , groups = { "Validate" } )
  public void login() throws IOException, InterruptedException {
	  
	  cmDriver.enterUsername("name", "userName", "mercury");
	  cmDriver.enterPassword("name", "password", "mercury");
	  cmDriver.screenShot();
	  cmDriver.submit("name","login");
	  cmDriver.screenShot();
	  cmDriver.ClickOnNext("name","findFlights");
	  cmDriver.screenShot();
	  cmDriver.ClickOnNext("name","reserveFlights");
	  cmDriver.screenShot();
	  cmDriver.FillingEntries("name","passFirst0","ABC");
	  cmDriver.FillingEntries("name","passLast0","XYZ");
	  cmDriver.FillingEntries("name","creditnumber","12345");
	  cmDriver.screenShot();
	  cmDriver.ClickOnNext("name","buyFlights");
	  cmDriver.screenShot();
	  
	  //Checking Departure Credentials
	  cmDriver.validate(1,"				Checking Booked Status		:    ","//tr[3]//font[2]","Your itinerary has been booked!");
	  
	  
	  
  }
  
  @Test(priority = 10 , groups = { "Validate" })
  public void validateCredentials() throws Exception {
	  
	  
	  //Note : Since ACTUAL test case value might differ with time so please Change It before Use 
	  //Checking Departure Credentials
	  cmDriver.validate(2,"				Departure Credentials		:    ","//tr[5]//tr[3]//font","Acapulco to Acapulco\n2/6/2018 @ 5:03 w/ Blue Skies Airlines 360\nCoach\n$270 each");
	  //Checking Returning Credentials
	  cmDriver.validate(3,"				Returning Credentials		:    ","//tr[5]//tr[5]//font","Acapulco to Acapulco\n2/6/2018 @ 12:23 w/ Blue Skies Airlines 630\nCoach\n$270 each");
	  //Checking Passenger Info
	  cmDriver.validate(4,"				Passenger Info				:    ","//tr[5]//tr[7]//font","1 passenger");
	  //Checking Billed To Info
	  cmDriver.validate(5,"				Billed To Info 				:    ","//tr[5]//tr[9]//font","1325 Borregas Ave.\n\nSunnyvale, CA, 94089");
	  //Checking Delivery Adderess
	  cmDriver.validate(6,"				Delivery Adderess			:    ","//tr[5]//tr[11]//font","1325 Borregas Ave.\n\nSunnyvale, CA, 94089");
	  
	  
	  cmDriver.navigateToUrl("https://gmail.com");
	  cmDriver.gmailLoginAndEmailToStakeHolders("..........Your Gmail ID........","........Your Gmail Password.........");
	  
  }
 
  
  @AfterClass (groups = { "Validate" })
  public void afterClass() throws Exception {
	  Thread.sleep(2000);
	  cmDriver.closeAllBrowsers();
  }

  
  
}
