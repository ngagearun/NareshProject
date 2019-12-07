package com.testng;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DataProviderWithExcel {
	
	WebDriver driver;
	String browser="firefox";
	
	@BeforeTest
	public void launchsetup(){
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
		
	}
	
	@org.testng.annotations.DataProvider(name="calculate")
	public String[][] getValues() throws EncryptedDocumentException, InvalidFormatException, IOException{
		
		String[][] arr=   readdatafromexcel("C:\\Users\\Lenovo\\Desktop\\arun1.xlsx","MortgageTestdata");
		return arr;
	}

	private String[][] readdatafromexcel(String path, String sheetname) throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		String[][] str=null;
		FileInputStream fi=new FileInputStream(path);
		Workbook wb=WorkbookFactory.create(fi);
		
		Sheet sh=wb.getSheet(sheetname);
		int tot_rows =sh.getLastRowNum();
		int tot_cols =sh.getRow(3).getLastCellNum();
		
		str=new String[tot_rows][tot_cols];
		
		 for(int i=1;i<=tot_rows;i++){			 
			Row ro= sh.getRow(i);			
			for(int j=0;j<tot_cols;j++){				
				Cell ce=ro.getCell(j);					
				str[i-1][j]=ce.getStringCellValue();
				}		
		}
		return str;
		
		
	}

}
