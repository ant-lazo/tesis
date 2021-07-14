package com.bolsadeideas.springboot.app;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PruebaSeleniumPaginator {
	
	private WebDriver driver;
	
	By lastLinkLocator = By.linkText("Última");
	By onebackLinkLocator = By.linkText("«");
	
	@Test
	public void testPaginator() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Desktop\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/");
		
		driver.findElement(lastLinkLocator).click();
		Thread.sleep(2000);
		driver.findElement(onebackLinkLocator).click();
		Thread.sleep(1000);
		driver.findElement(onebackLinkLocator).click();
		Thread.sleep(1000);
		driver.findElement(onebackLinkLocator).click();
		Thread.sleep(1000);
		driver.findElement(onebackLinkLocator).click();
		Thread.sleep(1000);
	}
	
}
