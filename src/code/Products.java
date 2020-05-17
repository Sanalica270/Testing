package code;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import data.Data;

public class Products {

	public static Properties p;

	public static List<WebElement> prices;

	public static void increasePrice(WebDriver wd) {
		wd.findElement(By.xpath(p.getProperty("PRODUCTS"))).click();
		wd.findElement(By.xpath(p.getProperty("EDIT"))).click();
		prices = wd.findElements(By.xpath(p.getProperty("PRICES")));
		Double price;

		for (int i = 0; i < prices.size(); i++) {

			price = Double.valueOf(prices.get(i).getAttribute("value")) + 100;
			prices.get(i).clear();
			prices.get(i).sendKeys(String.valueOf(price));
		}

		wd.findElement(By.xpath(p.getProperty("SAVE_CHANGES"))).click();
	}

	public static void add(WebDriver wd, int i) {

		wd.findElement(By.xpath(p.getProperty("PRODUCTS"))).click();
		wd.findElement(By.xpath(p.getProperty("ADD"))).click();

		inputID(wd, i);
		inputName(wd, i);
		inputPrice(wd, i);
		save(wd);

	}

	public static void inputID(WebDriver wd, int i) {
		wd.findElement(By.xpath(p.getProperty("PRODUCT_ID"))).sendKeys(Data.getDataAt(i, 0));

	}

	public static void inputName(WebDriver wd, int i) {
		wd.findElement(By.xpath(p.getProperty("NAME"))).sendKeys(Data.getDataAt(i, 1));
	}

	public static void inputPrice(WebDriver wd, int i) {
		wd.findElement(By.xpath(p.getProperty("PRICE"))).sendKeys(Data.getDataAt(i, 2));
	}

	public static void save(WebDriver wd) {
		wd.findElement(By.xpath(p.getProperty("SAVE_CHANGES"))).click();
	}

}
