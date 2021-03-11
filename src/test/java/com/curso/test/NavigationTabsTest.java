package com.curso.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationTabsTest {
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
	public void testNavigationTabs() throws InterruptedException {
		assertEquals("Treino Automação de Testes", driver.getTitle());
		
		WebElement linkJQuery = driver.findElement(By.linkText("Drag and Drop JQuery"));
		linkJQuery.click();
		
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		
		assertEquals("jQuery UI Droppable - Default functionality", driver.getTitle());
		
		driver.switchTo().window(tabs.get(0));		
		assertEquals("Treino Automação de Testes", driver.getTitle());
		
		WebElement linkGerador = driver.findElement(By.linkText("Gerador de CPF"));
		linkGerador.click();
		
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));
		assertEquals("Gerador de CPF", driver.getTitle());
		
		driver.switchTo().window(tabs.get(1));
		assertEquals("jQuery UI Droppable - Default functionality", driver.getTitle());
		
		driver.switchTo().window(tabs.get(0));
		assertEquals("Treino Automação de Testes", driver.getTitle());
	}
}
