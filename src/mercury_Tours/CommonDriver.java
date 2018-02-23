package mercury_Tours;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;


public class CommonDriver implements Driver{
	
	private WebDriver driver;
	private int pageloadTimeout;
	private int elementDetectionTimeout;
	private String finalResult;
	//create a resultList that will contain number of test cases
	List<String> resultList=new ArrayList<String>();
	//create an instance of PdfUtilityClass
	PdfUtility pdfUtility=new PdfUtility();
	
	public void setPageloadTimeout(int pageloadTimeout) {
		this.pageloadTimeout = pageloadTimeout;
	}

	public void setElementDetectionTimeout(int elementDetectionTimeout) {
		this.elementDetectionTimeout = elementDetectionTimeout;
	}
	
	public WebDriver getDriver(){
		return driver;
	}

	
	
	public CommonDriver(String browserType) throws Exception{
		
		browserType = browserType.trim();
		
		pageloadTimeout = 30;
		elementDetectionTimeout = 5;
		
		switch (browserType.toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\hp\\workspace\\libs\\chromedriver.exe");
			//Path to your chromedriver.exe
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\hp\\workspace\\libs\\geckodriver.exe");
			
			driver = new ChromeDriver();
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver",
					"C:\\Users\\hp\\workspace\\libs\\IEDriverServer.exe");
			
			driver = new InternetExplorerDriver();
			break;
		default:
			throw new Exception("Invalid Browser Type :: "+ browserType);
		}
	}
	@Override
	public void navigateToFirstUrl(String url) throws Exception {
		
		url = url.trim();
		
		driver.manage().deleteAllCookies();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().pageLoadTimeout(pageloadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(elementDetectionTimeout, TimeUnit.SECONDS);
		
		driver.get(url);
	}
	

	@Override
	public void enterUsername(String locator, String value, String param) {
		if (locator.equals("id")) {
			driver.findElement(By.id(value)).sendKeys(param);
		}
		
		
		else if (locator.equals("name")) {
			driver.findElement(By.name(value)).sendKeys(param);
		}
		finalResult = "Step-Id				Action						Test-Result";
		resultList.add(finalResult);
	}
	@Override
	public void enterPassword(String locator, String value, String param) {
		if (locator.equals("id")) {
			driver.findElement(By.id(value)).sendKeys(param);
		}

		else {
			driver.findElement(By.name(value)).sendKeys(param);
		}
	}
	@Override
	public void submit(String locator, String value) {
		if (locator.equals("id")) {
			driver.findElement(By.id(value)).click();
		}

		else {
			driver.findElement(By.name(value)).click();
		}
	}
	@Override
	public void screenShot() throws IOException, InterruptedException {
	    File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    String filename =  new SimpleDateFormat("yyyyMMddhhmmss'.jpeg'").format(new Date());
	    File dest = new File("C:\\Users\\hp\\Desktop\\Courses\\selenium course\\Certification Project\\ScreenShots\\" + filename);
	    FileUtils.copyFile(scr, dest);
	}
	

	@Override
	public void closeAllBrowsers() throws Exception {
		
		//define a time stamp string to add to the test result 
		 String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		 //add time stamp to the resultList
		 resultList.add("Test Ends: " + timeStamp);
		 //write the test result pdf file with file name TestResult
		  pdfUtility.WriteTestResultToPdfFile("TestResult.pdf", resultList);
		if(driver != null){
			driver.quit();
		}
		
	}
	@Override
	public void ClickOnNext(String locator, String value) {
		if (locator.equals("name"))
		driver.findElement(By.name(value)).click();
		
		
	}
	@Override
	public void FillingEntries(String locator, String value, String param) {
		if (locator.equals("id")) {
			driver.findElement(By.id(value)).sendKeys(param);
		}

		else {
			driver.findElement(By.name(value)).sendKeys(param);
		}
		
	}

	public void validate(int SerialNo, String TestingTopic, String value, String expected) {
		try{
		
		String actual = driver.findElement(By.xpath(value)).getText();
		System.out.println("\nActual : "+actual + "\nExpected : " + expected);
		Assert.assertEquals(actual, expected);
		
		finalResult = SerialNo+" "+TestingTopic+" Pass";
		TestingTopic = "Validating" + TestingTopic ;
		resultList.add(finalResult);
		
		}
		catch (Exception e)
		{
			finalResult = SerialNo+" "+TestingTopic+" Fail";
			resultList.add(finalResult);
		}
		
		
	}

	public void navigateToUrl(String url1) {

		driver.get(url1);
		
	}

	public void gmailLoginAndEmailToStakeHolders(String id, String password) throws IOException, InterruptedException {
		driver.findElement(By.id("identifierId")).sendKeys(id);
		driver.findElement(By.className("CwaK9")).click();
		driver.findElement(By.name("password")).sendKeys(password);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='passwordNext']/content")).click();
		
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(By.className("z0")).click();
		
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@id=':mo']")).sendKeys("stake-holder1@gmail.com,stake-holder2@gmail.com,stake-holder3@gmail.com");
		driver.findElement(By.name("subjectbox")).sendKeys("This Is The Subject Of Email");
		
		driver.findElement(By.xpath("//*[@id=':n9']")).sendKeys("The attach document contains Test Resuls.");
		driver.findElement(By.xpath("//*[@id=':nu']")).click();
		
		
		
		//Which calls the autoit exe file
		Runtime.getRuntime().exec("C:\\Users\\hp\\Desktop\\Courses\\selenium course\\Certification Project\\fileupload.exe");
		
		Thread.sleep(6000);
		driver.findElement(By.xpath("//*[@id=':lx']")).click();
	}

	

	

}
