package code;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import data.Data;

public class Signup {
	public static Properties p;

	public static void openSignUp(WebDriver wd) throws Exception {
		wd.get(p.getProperty("SIGNUP_PAGE"));
		wd.manage().window().maximize();

	}

	public static void backToSignUp(WebDriver wd) {
		if (!wd.getCurrentUrl().equals(p.getProperty("SIGNUP_PAGE"))) {
			wd.navigate().to(p.getProperty("SIGNUP_PAGE"));
		}

	}

	public static void signUpUsers(WebDriver wd) {

		for (int i = 1; i < Data.rowNumber(); i++) {
			if (!Login.logOut(wd)) {
				Login.logOut(wd);
			}
			signUp(wd, i);
		}
		Login.logOut(wd);
	}

	public static void inputUserName(WebDriver driver) {
		WebElement el = driver.findElement(By.xpath(p.getProperty("USERNAMES")));
		el.click();
		Random random = new Random();
		int randomInt = random.nextInt(1000);
		el.sendKeys("articoka" + randomInt);
	}

	public static void inputEmail(WebDriver driver) {
		WebElement el = driver.findElement(By.xpath(p.getProperty("EMAIL")));
		el.click();
		Random random = new Random();
		int randomInt = random.nextInt(1000);
		el.sendKeys("sanalica" + randomInt + "@outlook27.com");
	}

	public static void signUp1(WebDriver wd) {
		wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		inputUserName(wd);
		inputEmail(wd);
		inputPass(wd, "123456Aa");
		confirm(wd, "123456Aa");
		about(wd);
		submit(wd);
	}

	public static void signUp(WebDriver wd, int i) {
		wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		inputUserName(wd, Data.getDataAt(i, 0));
		inputEmail(wd, Data.getDataAt(i, 1));
		inputPass(wd, Data.getDataAt(i, 2));
		confirm(wd, Data.getDataAt(i, 2));
		about(wd);
		submit(wd);
	}

	public static void about(WebDriver wd) {
		wd.findElement(By.xpath(p.getProperty("ABOUT"))).click();
	}

	public static void inputUserName(WebDriver wd, String user) {
		wd.findElement(By.xpath(p.getProperty("USERNAMES"))).sendKeys(user);

	}

	public static void confirm(WebDriver wd, String pass) {
		wd.findElement(By.xpath(p.getProperty("CONFIRM_PASSWORD"))).sendKeys(pass);

	}

	public static void inputEmail(WebDriver wd, String email) {
		wd.findElement(By.xpath(p.getProperty("EMAIL"))).sendKeys(email);

	}

	public static void inputPass(WebDriver wd, String password) {
		wd.findElement(By.xpath(p.getProperty("PASSWORD"))).sendKeys(password);

	}

	public static void submit(WebDriver wd) {
		wd.findElement(By.xpath(p.getProperty("DIGIN"))).click();
	}

}
