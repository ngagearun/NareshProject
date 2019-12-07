package com.testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Dresses extends LaunchSetUp {
	
	
	
	@BeforeClass(groups={"regression","sanity","smoke"})
	public void movingtodresstab(){
		
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[2]/a"))).build().perform();
	}
	
	
	@Test(priority=1,groups="regression")
	public void eveningdress() throws InterruptedException{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[2]/ul/li[2]/a")).click();
		Select obj=new Select(driver.findElement(By.id("selectProductSort")));
		obj.selectByIndex(2);
		driver.navigate().back();
		
	}
	
	@Test(priority=2,groups="sanity")
	public void casualdress() throws InterruptedException{
		Thread.sleep(3000);
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[2]/a"))).build().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[2]/ul/li[1]/a")).click();
		driver.findElement(By.id("layered_id_attribute_group_1")).click();
		driver.findElement(By.id("layered_id_attribute_group_2")).click();
		driver.navigate().back();
		
	}
	
	
	@Test(priority=3,groups="smoke")
	public void summerdresses() throws InterruptedException{
		Thread.sleep(3000);
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[2]/a"))).build().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[2]/ul/li[3]/a")).click();
		Thread.sleep(3000);
	     JavascriptExecutor js =(JavascriptExecutor)driver;
	     js.executeScript("window.scrollBy(0,600)");
	     Thread.sleep(3000);
		
		Actions action1=new Actions(driver);
		action1.moveToElement(driver.findElement(By.xpath("//*[@id='center_column']/ul/li[1]/div/div[1]/div/a[2]/span"))).build().perform();
		WebDriverWait objname=new WebDriverWait(driver,10);
		objname.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='center_column']/ul/li[1]/div/div[2]/div[2]/a[1]/span"))));
		driver.findElement(By.xpath("//*[@id='center_column']/ul/li[1]/div/div[2]/div[2]/a[1]/span")).click();
		driver.navigate().back();
		
	}
	
	
	
	
	

}
