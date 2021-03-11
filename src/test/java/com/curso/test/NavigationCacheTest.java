package com.curso.test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationCacheTest {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"/home/antonio/dev/drivers/chromedriver");
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);		
		driver.get("http://antoniotrindade.com.br/treinoautomacao");		
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testNavigationCache() {
		
		assertEquals("Treino Automação de Testes", driver.getTitle());
		
		WebElement menuAlert = driver.findElement(By.linkText("Alerts and Confirmations"));
		menuAlert.click();
		
		WebElement menuCalculadora = driver.findElement(By.linkText("Calculadora"));
		menuCalculadora.click();
		
		WebElement menuTable = driver.findElement(By.linkText("Localizar Table"));
		menuTable.click();
		assertEquals("Trabalhando com tables", driver.getTitle());
		
		driver.navigate().back();
		assertEquals("Desafio Automação Cálculos", driver.getTitle());
		
		driver.navigate().back();
		assertEquals("Alerts and Confirmations", driver.getTitle());
		
		driver.navigate().back();
		assertEquals("Treino Automação de Testes", driver.getTitle());

		driver.navigate().forward();
		assertEquals("Alerts and Confirmations", driver.getTitle());
		
		driver.navigate().forward();
		assertEquals("Desafio Automação Cálculos", driver.getTitle());
		
		driver.navigate().forward();
		assertEquals("Trabalhando com tables", driver.getTitle());
	}

}
