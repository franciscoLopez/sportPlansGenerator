package selenium;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginSportCentreTest {

	private WebDriver browser;

	@Before
	public void setup() {
		browser = new FirefoxDriver();
		browser.get("http://localhost:8080/SportPlansGenerator/");
	}

	@Test
	public void Test1() {
		// Will throw exception if elements not found
		browser.findElement(By.id("usernameInput")).sendKeys("centro2sevilla");
		browser.findElement(By.id("passwordInput")).sendKeys("prueba");

		browser.findElement(By.id("submit")).click();
		
		assertEquals("Centro Deportivo", browser.getTitle());		
	}

	@After
	public void tearDown() {
		browser.close();
	}
}
