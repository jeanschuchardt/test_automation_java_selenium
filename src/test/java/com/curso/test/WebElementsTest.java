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
import org.openqa.selenium.support.ui.Select;

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
	}
	
	@Test
	public void testDropDownSingle() throws InterruptedException {
		WebElement dropDownSingle = driver.findElement(By.name("dropdownlist"));
		
		Select selectSingle = new Select(dropDownSingle);
		
		selectSingle.selectByVisibleText("Item 1");
		selectSingle.selectByVisibleText("Item 2");
		selectSingle.selectByVisibleText("Item 7");
		
		assertEquals("Item 7", selectSingle.getFirstSelectedOption().getText());
	}
	
	@Test
	public void testDropDownMulti() {
		WebElement dropDownMulti = driver.findElement(By.name("multiselectdropdown"));
		
		Select selectMulti = new Select(dropDownMulti);
		
		selectMulti.selectByIndex(4);		
		selectMulti.selectByVisibleText("Item 9");
		selectMulti.selectByIndex(7);
		
		List<WebElement> allSelected = selectMulti.getAllSelectedOptions();
		
		assertEquals(3, allSelected.size());
		
		assertEquals("Item 5", allSelected.get(0).getText());
		assertEquals("Item 8", allSelected.get(1).getText());
		assertEquals("Item 9", allSelected.get(2).getText());
		
		selectMulti.deselectByIndex(7);
		
		allSelected = selectMulti.getAllSelectedOptions();
		
		assertEquals(2, allSelected.size());
		assertEquals("Item 5", allSelected.get(0).getText());
		assertEquals("Item 9", allSelected.get(1).getText());		
	}
	
	@Test
	public void testiFrames() throws InterruptedException {
		
		driver.switchTo().frame("iframe_b");
		
		WebElement btnAceitarCookies = driver.findElement(By.cssSelector("a.cc-btn.cc-ALLOW"));
		btnAceitarCookies.click();
		
		WebElement txtSearch = driver.findElement(By.id("s"));	
		txtSearch.sendKeys("Antonio");
		assertEquals("Antonio", txtSearch.getAttribute("value"));

		driver.switchTo().defaultContent();
		
		Thread.sleep(3000);
		
		driver.switchTo().frame("iframe_d");
		
		Thread.sleep(3000);
		
		WebElement menu = driver.findElement(By.id("dropdownButton"));
		menu.click();
		
		WebElement txtSearchWeb = driver.findElement(By.name("search"));
		txtSearchWeb.sendKeys("Antonio");
		assertEquals("Antonio", txtSearchWeb.getAttribute("value"));
	}
}
