package com.testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class LaunchSetUp {
	
	static WebDriver driver;
	
	@Parameters("browsername")
	@BeforeTest(groups={"regression","sanity","smoke"})
	void launchapp(String browser){
		
		if(browser.equalsIgnoreCase("firefox")){
			  System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "D:\\logs.txt");
				System.setProperty("webdriver.gecko.driver", "C:/Users/Lenovo/Downloads/geckodriver.exe");
				driver=new FirefoxDriver();
			}else if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", "C:/Users/Lenovo/Downloads/chromedriver.exe");
				driver=new ChromeDriver();
			}	
		
		driver.navigate().to("http://automationpractice.com/index.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	
   @AfterTest(groups={"regression","sanity","smoke"})
	
	public void closeapp(){
		driver.quit();
		
	}
	

}
