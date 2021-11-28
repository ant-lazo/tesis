package com.bolsadeideas.springboot.app;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.xpath;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PruebaSeleniumIngresarFactura {

private WebDriver driver;
	
	By registerLinkLocator = By.linkText("Iniciar sesion");
	By classLocator = By.className("card-header");
	By usernameLocator = By.id("username");
	By passwordLocator = By.id("password");
	By iniciarsesionLocator = By.xpath("//input[@class='btn btn-lg btn-success btn-block']");
	By crearfacturaLocator = By.xpath("//a[@href='/factura/form/10']");
	By descripcionLocator = By.id("descripcion");
	By observacionLocator = By.id("observacion");
	By verificarcrearfacturaLocator = By.xpath("//div[@class='card-header text-success']");
	By buscarproductoLocator = By.id("buscar_producto");
	By registrarfacturaLocator = By.xpath("//input[@type='submit']");
	//By item7Locator = By.xpath("//div[@id='ui-id-7']");
	//By item7Locator = By.id("ui-id-7");
	//By item7Locator = By.cssSelector("div[id='ui-id-7']");
	By item1Locator = By.cssSelector("div[class='ui-menu-item-wrapper']");
	
	@Test
	public void ingresarFactura() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Desktop\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/");
		
		driver.findElement(registerLinkLocator).click();
		Thread.sleep(2000);
		if(driver.findElement(classLocator).isDisplayed()) {
			driver.findElement(usernameLocator).sendKeys("admin");
			driver.findElement(passwordLocator).sendKeys("12345");
			Thread.sleep(1000);
			driver.findElement(iniciarsesionLocator).click();
		}else {
			System.out.println("Error");
		}
		Thread.sleep(2000);
		driver.findElement(crearfacturaLocator).click();
		Thread.sleep(2000);
		
		if(driver.findElement(verificarcrearfacturaLocator).isDisplayed()){
			driver.findElement(descripcionLocator).sendKeys("compra de electrodomenticos");
			driver.findElement(observacionLocator).sendKeys("ninguna");
			driver.findElement(buscarproductoLocator).sendKeys("Camara digital Sony");
			Thread.sleep(4000);
			Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
					.withTimeout(50, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);
			
			WebElement link = fwait.until(new Function<WebDriver,WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(item1Locator);
				}
			});
			assertTrue(driver.findElement(item1Locator).isDisplayed());
			driver.findElement(item1Locator).click();
			
			driver.findElement(buscarproductoLocator).clear();
			driver.findElement(buscarproductoLocator).sendKeys("Refrigeradora Samsung");
			Thread.sleep(4000);
			Wait<WebDriver> fwait2 = new FluentWait<WebDriver>(driver)
					.withTimeout(50, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);
			
			WebElement link2 = fwait2.until(new Function<WebDriver,WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(item1Locator);
				}
			});
			assertTrue(driver.findElement(item1Locator).isDisplayed());
			driver.findElement(item1Locator).click();
			
			driver.findElement(registrarfacturaLocator).click();
			
		}else {
			System.out.println("Error");
		}
		
	}
	
	/*@Test
	public void buscarProducto() {
		By item7Locator = By.id("ui-id-7");
		driver.findElement(item7Locator).click();
	}*/
}
