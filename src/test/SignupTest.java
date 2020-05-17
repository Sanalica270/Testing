package test;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import code.Login;
import code.Signup;
import data.Data;

public class SignupTest {
	SoftAssert sa;
	WebDriver wd;
	Properties p;

	@BeforeClass
	public void before() throws Exception {
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Data.readText();
		Data.getFile(Data.usersPath);
		Data.getSheet(0);

		wd = new OperaDriver();
		sa = new SoftAssert();
		Signup.openSignUp(wd);

	}

	@Test
	public void test1() throws Exception {
		

		Signup.signUp1(wd);

		sa.assertEquals(wd.getCurrentUrl(), p.getProperty("HOME"));

		sa.assertAll();
	}

	@Test
	public void testSignUp() {

		Signup.backToSignUp(wd);

		for (int i = 1; i < Data.rowNumber(); i++) {
			if (!Login.logOut(wd)) {
				Login.logOut(wd);
			}
			Signup.signUp(wd, i);
			sa.assertEquals(wd.getCurrentUrl(), p.getProperty("HOME"));
		}

		sa.assertAll();
	}

	@Test
	public void test2() {
		Signup.backToSignUp(wd);
		if (!Login.logOut(wd)) {
			Login.logOut(wd);
		}
		Signup.inputEmail(wd);
		Signup.inputPass(wd, "123456Aa");
		Signup.confirm(wd, "123456Aa");
		Signup.about(wd);
		Signup.submit(wd);
		sa.assertNotEquals(wd.getCurrentUrl(), p.getProperty("HOME"));
		sa.assertAll();

	}

	@Test
	public void test3() {
		Signup.backToSignUp(wd);
		if (!Login.logOut(wd)) {
			Login.logOut(wd);
		}
		Signup.inputUserName(wd);

		Signup.inputPass(wd, "123456Aa");
		Signup.confirm(wd, "123456Aa");
		Signup.about(wd);
		Signup.submit(wd);
		sa.assertNotEquals(wd.getCurrentUrl(), p.getProperty("HOME"));
		sa.assertAll();

	}

	@Test
	public void test4() {
		Signup.backToSignUp(wd);
		if (!Login.logOut(wd)) {
			Login.logOut(wd);
		}
		Signup.inputUserName(wd);
		Signup.inputEmail(wd);

		Signup.confirm(wd, "123456Aa");
		Signup.about(wd);
		Signup.submit(wd);
		sa.assertNotEquals(wd.getCurrentUrl(), p.getProperty("HOME"));
		sa.assertAll();

	}

	@Test
	public void test5() {
		Signup.backToSignUp(wd);
		if (!Login.logOut(wd)) {
			Login.logOut(wd);
		}
		Signup.inputUserName(wd);
		Signup.inputEmail(wd);
		Signup.inputPass(wd, "123456Aa");

		Signup.about(wd);
		Signup.submit(wd);
		sa.assertNotEquals(wd.getCurrentUrl(), p.getProperty("HOME"));
		sa.assertAll();

	}

	@Test
	public void test6() {
		Signup.backToSignUp(wd);
		if (!Login.logOut(wd)) {
			Login.logOut(wd);
		}
		Signup.inputUserName(wd);
		Signup.inputEmail(wd);
		Signup.inputPass(wd, "123456Aa");
		Signup.confirm(wd, "123456Aa");

		Signup.submit(wd);
		sa.assertNotEquals(wd.getCurrentUrl(), p.getProperty("HOME"));
		sa.assertAll();

	}
	@AfterClass
	public void after() {
		Login.logOut(wd);
		wd.quit();
	}

}
