package com.bolsadeideas.springboot.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



public class PruebaSelenium {

	private WebDriver driver;
	
	By registerLinkLocator = By.linkText("Iniciar sesion");
	By classLocator = By.className("card-header");
	By usernameLocator = By.id("username");
	By passwordLocator = By.id("password");
	By iniciarsesionLocator = By.xpath("//input[@class='btn btn-lg btn-success btn-block']");
	
	/*@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Desktop\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.google.com/");
	}*/
	
	@Test
	public void testGooglePage() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Desktop\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/");
		
		driver.findElement(registerLinkLocator).click();
		Thread.sleep(2000);
		if(driver.findElement(classLocator).isDisplayed()) {
			driver.findElement(usernameLocator).sendKeys("omar");
			driver.findElement(passwordLocator).sendKeys("12345");
			
			driver.findElement(iniciarsesionLocator).click();
		}else {
			System.out.println("Error");
		}
		
		
		/*WebElement searchbox = driver.findElement(By.name("q")); 
		searchbox.clear();
		searchbox.sendKeys("omar el mejor");
		searchbox.submit();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assertEquals("omar el mejor", driver.getTitle());*/
		
	}
	
	/*@After
	public void teardown() {
		driver.quit();
	}*/
}
