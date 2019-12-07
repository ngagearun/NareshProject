package com.testng;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ReportAggregates;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentAventReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import org.testng.annotations.DataProvider;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class DataProvider {
	WebDriver driver;
	String browser="chrome";
	
	
	
	ExtentHtmlReporter html;
	ExtentReports extent;
	ExtentTest test;
	@BeforeTest
	public void launchsetup(){
		
		html=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\ExtentReportResults.html");
		 extent = new ExtentReports();
		extent.attachReporter(html);
		html.config().setAutoCreateRelativePathMedia(true);
		html.config().setDocumentTitle("Extent Report Demo");
		html.config().setReportName("Test Report");		
        html.config().setTheme(Theme.STANDARD);
        html.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		if(browser.equalsIgnoreCase("firefox")){
			  System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "D:\\logs.txt");
				System.setProperty("webdriver.gecko.driver", "C:/Users/Lenovo/Downloads/geckodriver.exe");
				driver=new FirefoxDriver();
			}else if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", "C:/Users/Lenovo/Downloads/chromedriver.exe");
				driver=new ChromeDriver();
			}	
		
		driver.navigate().to("https://www.mortgagecalculator.org/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterTest
	public void closeapp(){
		driver.close();
		extent.flush();
		

	}
	
	
	@Test(dataProvider="calculate")
	public void calculateloaninterest(String homevalue,String downpayment,String loanamount,String interesrate){
		
		driver.findElement(By.id("homeval")).clear();
		driver.findElement(By.id("homeval")).sendKeys(homevalue);

		driver.findElement(By.id("downpayment")).clear();
		driver.findElement(By.id("downpayment")).sendKeys(downpayment);

		driver.findElement(By.id("loanamt")).clear();
		driver.findElement(By.id("loanamt")).sendKeys(loanamount);

		driver.findElement(By.id("intrstsrate")).clear();
		driver.findElement(By.id("intrstsrate")).sendKeys(interesrate);
		
		driver.findElement(By.name("cal")).click();
		test=extent.createTest("Test");
         Assert.assertTrue(true);
		
	}
	
	 @AfterMethod
	    public void getResult(ITestResult result) {
	        if(result.getStatus() == ITestResult.FAILURE) {
	            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
	            test.fail(result.getThrowable());
	        }
	        else if(result.getStatus() == ITestResult.SUCCESS) {
	            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
	        }
	        else {
	            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
	            test.skip(result.getThrowable());
	        }
	    }
	
	@org.testng.annotations.DataProvider(name="calculate")
	public String[][] getValues(){
		
		String str[][]={ 
				
						 {"10000","20000","34005","4567"},
				         {"20000","43000","5678","4532"},
				         {"43555","43244","5445","43554"}
		               };
		
		
		return str;
		
		
		
	}
}
