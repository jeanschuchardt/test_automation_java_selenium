package com.curso.test;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.curso.inter.NegativeInterface;
import com.curso.inter.PositiveInterface;

public class CPFCNPJTest {
	public WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"/home/antonio/dev/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);		
		driver.get("https://www.geradordecpf.org/");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	@Category(PositiveInterface.class)
	public void testValidaCPFComMascara() throws InterruptedException {
		WebElement checkBoxPonto = driver.findElement(By.id("cbPontos"));
		checkBoxPonto.click();
		
		WebElement btnGerar = driver.findElement(By.id("btn-gerar-cpf"));
		btnGerar.click();
		
		WebElement textFieldCpf = driver.findElement(By.id("numero"));
		String cpfGerado = textFieldCpf.getAttribute("value");
		
		System.out.println(cpfGerado);
		
		assertTrue("Deveria ter validado a expressão com mascara", 
				cpfGerado.matches("^[0-9]{11}$|^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$"));
	}
	
	@Test
	@Category(NegativeInterface.class)
	public void testValidaCPFSemMarcara() {
		WebElement btnGerar = driver.findElement(By.id("btn-gerar-cpf"));
		btnGerar.click();
		
		WebElement textFieldCpf = driver.findElement(By.id("numero"));
		String cpfGerado = textFieldCpf.getAttribute("value");
		
		System.out.println(cpfGerado);
		
		assertTrue("Deveria ter validado a expressão sem mascara", 
				cpfGerado.matches("^[0-9]{11}$|^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$"));
		
	}
}
