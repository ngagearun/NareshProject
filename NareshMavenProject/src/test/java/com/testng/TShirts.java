package com.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TShirts extends LaunchSetUp {
	
	@Test(groups="regression")
	public void clicktshirtstab() throws InterruptedException{
	
		driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[3]/a")).click();
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
