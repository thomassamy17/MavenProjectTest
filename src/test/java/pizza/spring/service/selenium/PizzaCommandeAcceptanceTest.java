package pizza.spring.service.selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PizzaCommandeAcceptanceTest {

	private WebDriver webDriver;

	@Before
	public void createWebDriver() {
		webDriver = new SafariDriver();
	}

	@After
	public void closeWebDriver() {
		webDriver.quit();
	}

	@Test
	public void commanderUnePizza() throws Exception {


		webDriver.navigate().to("http://localhost:8080/pizza-springTestSelenium/");
		WebElement commandeButton = webDriver.findElement(By.linkText("Commander"));
		commandeButton.click();

		Select select=new Select(new WebDriverWait(webDriver, 3).until(ExpectedConditions.presenceOfElementLocated(By.id("pizzaId"))));
		select.selectByIndex(1);

		WebElement name = webDriver.findElement(By.id("nom"));
		name.sendKeys("SAMY");
		WebElement tel = webDriver.findElement(By.id("telephone"));
		tel.sendKeys("0605050505");

		WebElement commande = webDriver.findElement(By.cssSelector("Button"));
		commande.click();

		assertTrue(new WebDriverWait(webDriver, 3).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("recap-commande"))).size() == 1);
	}

	@Test
	public void commanderUnePizzaSansRenseignerLeTelephone() throws Exception {


		webDriver.navigate().to("http://localhost:8080/pizza-springTestSelenium/");
		WebElement commandeButton = webDriver.findElement(By.linkText("Commander"));
		commandeButton.click();

		Select select=new Select(new WebDriverWait(webDriver, 3).until(ExpectedConditions.presenceOfElementLocated(By.id("pizzaId"))));
		select.selectByIndex(1);

		WebElement name = webDriver.findElement(By.id("nom"));
		name.sendKeys("SAMY");

		WebElement commande = webDriver.findElement(By.cssSelector("Button"));
		commande.click();

		assertTrue(new WebDriverWait(webDriver, 3).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("telephone.errors"))).size() == 1);
	}

	@Test
	public void commanderUnePizzaSansChoisirLaPizza() throws Exception {

		webDriver.navigate().to("http://localhost:8080/pizza-springTestSelenium/");
		WebElement commandeButton = webDriver.findElement(By.linkText("Commander"));
		commandeButton.click();

		new WebDriverWait(webDriver, 3).until(ExpectedConditions.presenceOfElementLocated(By.id("pizzaId")));

		WebElement name = webDriver.findElement(By.id("nom"));
		name.sendKeys("SAMY");
		WebElement tel = webDriver.findElement(By.id("telephone"));
		tel.sendKeys("0605050505");

		WebElement commande = webDriver.findElement(By.cssSelector("Button"));
		commande.click();

		assertTrue(new WebDriverWait(webDriver, 3).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("pizzaId.errors"))).size() == 1);
	}
}
