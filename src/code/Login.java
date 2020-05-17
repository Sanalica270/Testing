package code;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import data.Data;

public class Login {
	public static Properties p;


	public static void openLogin(WebDriver wd) {
			
		wd.get(p.getProperty("LOGIN_PAGE"));
	}

	public static void backToLogin(WebDriver wd) {
		if (!wd.getCurrentUrl().equals(p.getProperty("LOGIN_PAGE"))) {
			wd.navigate().to(p.getProperty("LOGIN_PAGE"));
		}

	}

	public static boolean logOut(WebDriver wd) {
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		wd.findElement(By.id(p.getProperty("ACCOUNT"))).click();
		wd.findElement(By.xpath(p.getProperty("LOGOUT"))).click();
		return true;
	}

	public static void login(WebDriver wd, int i) {
		wd.findElement(By.xpath(p.getProperty("USERNAMEL"))).sendKeys(Data.getDataAt(i, 0));
		wd.findElement(By.xpath(p.getProperty("PASSWORD"))).sendKeys(Data.getDataAt(i, 2));
		wd.findElement(By.xpath(p.getProperty("LOGIN"))).click();
	}

	public static void login(WebDriver wd, String user, String pass) {
		wd.findElement(By.xpath(p.getProperty("USERNAMEL"))).sendKeys(user);
		wd.findElement(By.xpath(p.getProperty("PASSWORD"))).sendKeys(pass);
		wd.findElement(By.xpath(p.getProperty("LOGIN"))).click();
	}

	public static void loginUsers(WebDriver wd) {
		for (int i = 1; i < Data.rowNumber(); i++) {
			if (!logOut(wd)) {
				logOut(wd);
			}
			login(wd, i);
		}

	}
}
