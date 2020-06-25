package com.excilys;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

@Ignore
public class Selenium {
	protected static WebDriver driver;
	static int name;

	@BeforeClass
	public static void setupTest() {
		// eze
		System.setProperty("webdriver.gecko.driver", "/opt/WebDriver/bin/geckodriver");
		// driver = new ChromeDriver();
		driver = new FirefoxDriver();
	}

	@Before
	public void beforeland() {

	}

	@Test
	public void T01_getURLExample() {

		driver.get("http://localhost:8080/Computer-db/dashboard");

		// Check title is correct
		assertEquals(driver.getTitle(), "Computer Database");
	}

	@Test
	public void T02_Test_AddComputer_Check_no_Name() {

		driver.get("http://localhost:8080/Computer-db/addComputer");

		WebElement error = driver.findElement(By.id("error"));
		WebElement computerName = driver.findElement(By.id("computerName"));
		WebElement introduced = driver.findElement(By.id("introduced"));
		WebElement discontinued = driver.findElement(By.id("discontinued"));
		WebElement company = driver.findElement(By.id("companyId"));
		WebElement validate = driver.findElement(By.id("Add"));
		validate.click();
		assertEquals(error.getText(), "name of the Computer was not inserted");
		// computerName.sendKeys("Gavin");

	}

	@Test
	public void T04_Test_AddComputer_Check_introduced_superior_to_discontinued() throws InterruptedException {

		driver.get("http://localhost:8080/Computer-db/addComputer");

		WebElement computerName = driver.findElement(By.id("computerName"));
		WebElement introduced = driver.findElement(By.id("introduced"));
		WebElement discontinued = driver.findElement(By.id("discontinued"));
		WebElement company = driver.findElement(By.id("companyId"));
		WebElement validate = driver.findElement(By.id("Add"));
		computerName.sendKeys("Political lemon");
		discontinued.clear();
		discontinued.sendKeys("20-Aug-1985");
		introduced.clear();
		introduced.sendKeys("21-Aug-1985");

		validate.click();
		WebElement error = driver.findElement(By.id("error"));
		assertEquals(error.getText(), "Indicates a dangerous or potentially negative action.");

	}

	// Error to correct
	@Test
	public void T03_Test_AddComputer_Check_introduced_but_not_discontinued() throws InterruptedException {

		driver.get("http://localhost:8080/Computer-db/addComputer");

		WebElement computerName = driver.findElement(By.id("computerName"));
		WebElement introduced = driver.findElement(By.id("introduced"));
		WebElement discontinued = driver.findElement(By.id("discontinued"));
		WebElement company = driver.findElement(By.id("companyId"));
		WebElement validate = driver.findElement(By.id("Add"));
		computerName.sendKeys("Political lemon");
		discontinued.clear();
		discontinued.sendKeys("20-Aug-1985");
		validate.click();

		WebElement error = driver.findElement(By.id("error"));
		assertEquals(error.getText(), "Indicates a dangerous or potentially negative action.");

	}

	@Test
	public void T05_Add_first_computer() throws InterruptedException {

		driver.get("http://localhost:8080/Computer-db/addComputer");

		WebElement computerName = driver.findElement(By.id("computerName"));
		WebElement introduced = driver.findElement(By.id("introduced"));
		WebElement discontinued = driver.findElement(By.id("discontinued"));
		WebElement company = driver.findElement(By.id("companyId"));
		WebElement validate = driver.findElement(By.id("Add"));
		computerName.sendKeys("Political lemon");
		discontinued.clear();
		discontinued.sendKeys("20-02-1985");
		validate.click();

		WebElement error = driver.findElement(By.id("error"));
		assertEquals(error.getText(), "Indicates a dangerous or potentially negative action.");

	}

}
