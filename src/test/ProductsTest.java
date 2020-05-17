package test;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import code.Home;
import code.Login;
import code.Products;
import data.Data;

public class ProductsTest {

	WebDriver wd;
	SoftAssert sa;
	Properties p;

	@BeforeClass
	public void before() throws Exception {
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Data.readText();
		Data.getFile(Data.productsPath);
		Data.getSheet(0);

		wd = new OperaDriver();
		sa = new SoftAssert();
		Login.openLogin(wd);
		wd.manage().window().maximize();
		Login.login(wd, "SunnyDay", "123456Aa");

	}

	@Test
	public void testAdding() {

		for (int i = 0; i < 5; i++) {
			Products.add(wd, i);
			sa.assertTrue(wd.findElement(By.linkText("Update successful")).isDisplayed());

		}
		sa.assertAll();
	}

	@Test
	public void testPrice() {
		Home.goToHome(wd);

		Products.increasePrice(wd);
		sa.assertTrue(wd.findElement(By.linkText("All updates succeeded")).isDisplayed());
		
		List<WebElement> prices = wd.findElements(By.xpath(p.getProperty("PRICES")));
		for (int i = 0; i < 5; i++) {
			sa.assertEquals(Double.valueOf(prices.get(i).getAttribute("value")), Double.valueOf(Data.getDataAt(i, 2))+100);
		}

		sa.assertAll();
	}

	@AfterClass
	public void after() {
		Login.logOut(wd);
		wd.quit();
	}

}
