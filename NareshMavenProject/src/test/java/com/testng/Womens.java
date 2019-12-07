package com.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Womens extends LaunchSetUp {

	
	@BeforeClass(groups={"regression","sanity"})
	public void clickonwomentab(){
		
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[1]/a"))).build().perform();
	}
	
	
	@Test(priority=1,groups="regression")
	public void clicktshirts() throws InterruptedException{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[1]/ul/li[1]/ul/li[1]/a")).click();
		driver.navigate().back();
		
	}
	
	@Test(priority=2,groups="sanity")
	public void casualdress() throws InterruptedException{
		Thread.sleep(3000);
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[1]/a"))).build().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[1]/ul/li[2]/ul/li[1]/a")).click();
		driver.navigate().back();
		
		
	}
}
