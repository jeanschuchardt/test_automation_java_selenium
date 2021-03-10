package com.curso.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebElementsTest {
	public WebDriver driver; 

	@Before
	public void setUp() throws Exception {
		
		System.setProperty("webdriver.chrome.driver",
				"/home/antonio/dev/drivers/chromedriver");
				//"c:\\drivers\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);		
		driver.get("http://antoniotrindade.com.br/treinoautomacao/elementsweb.html");		
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testValidaTitulo() {
		assertEquals("Selenium WebDriver Test Page", driver.getTitle());	
	}
	
	@Test
	public void testValidaNomeTextField() {
		
		WebElement txtNome = driver.findElement(By.name("txtbox1"));
		
		assertTrue(txtNome.isEnabled());
		
		txtNome.sendKeys("Antônio");
		
		assertEquals("Antônio",	txtNome.getAttribute("value"));
		
	}
	
	@Test
	public void testValidaEnable() {
		WebElement txtNome = driver.findElement(By.name("txtbox1"));
		WebElement txtDisable = driver.findElement(By.name("txtbox2"));
		
		assertTrue(txtNome.isEnabled());
		assertFalse(txtDisable.isEnabled());
	}
	
	@Test
	public void testValidaRadioButton() {
		
		List<WebElement> listRadios = driver.findElements(By.name("radioGroup1"));
		
		for(WebElement radio: listRadios) {
			if (radio.getAttribute("value").equals("Radio Button 3 selecionado")){
				radio.click();
			}
		}
		
		assertFalse(listRadios.get(1).isSelected());
		assertTrue(listRadios.get(2).isSelected());
	}
	
	@Test
	public void testValidaCheckBox() throws InterruptedException {
		List<WebElement> listCheckBox = driver.findElements(By.name("chkbox"));
		
		for (WebElement check: listCheckBox) {
			if ((check.getAttribute("value").equals("Check Box 2 selecionado")) ||
					(check.getAttribute("value").equals("Check Box 3 selecionado"))){
				check.click();
			}
		}
		
		assertTrue(listCheckBox.get(1).isSelected());
		assertTrue(listCheckBox.get(2).isSelected());
		assertFalse(listCheckBox.get(3).isSelected());
		
		Thread.sleep(3000);
	}
	
	
}
