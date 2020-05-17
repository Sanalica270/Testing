package test;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import code.Login;
import data.Data;

public class LoginTest {
	Properties p;
	WebDriver wd;
	SoftAssert sa;
	

	@BeforeClass
	public void before() throws Exception {
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Data.readText();
		Data.getFile(Data.usersPath);
		Data.getSheet(0);

		wd = new OperaDriver();
		sa = new SoftAssert();
		Login.openLogin(wd);
	}

	@Test
	public void testLoginUsers() {

		

		for (int i = 1; i < Data.rowNumber(); i++) {
			Login.login(wd, i);

			sa.assertTrue((wd.findElement(By.xpath(p.getProperty("ACCOUNT")))).isDisplayed());
			Login.logOut(wd);
		}
		sa.assertAll();

	}

	@Test
	public void testLogin1() {

		if (wd.getCurrentUrl() != p.getProperty("LOGIN_PAGE"))
			Login.backToLogin(wd);
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Login.login(wd, "SunnyDay", "123456Aa");

		sa.assertTrue((wd.findElement(By.xpath(p.getProperty("ACCOUNT")))).isDisplayed());
		Login.logOut(wd);

		sa.assertAll();

	}

	@Test
	public void testLoginNew() {

		if (wd.getCurrentUrl() != p.getProperty("LOGIN_PAGE"))
			Login.backToLogin(wd);
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Login.login(wd, "SomeoneNew", "Abc12345");

		sa.assertFalse((wd.findElement(By.xpath(p.getProperty("ACCOUNT")))).isDisplayed());
		if (!Login.logOut(wd)) {
			Login.logOut(wd);
		}

		sa.assertAll();

	}
	
	@AfterClass
	public void after() {
		Login.logOut(wd);
		wd.quit();
	}

}
