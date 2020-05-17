package code;

import java.util.Properties;
import org.openqa.selenium.WebDriver;

public class Home {
	public static Properties p;

	public static void openHome(WebDriver wd) {
		wd.get(p.getProperty("HOME"));
		wd.manage().window().maximize();
	}

	public static void goToHome(WebDriver wd) {
		if (!wd.getCurrentUrl().equals(p.getProperty("HOME"))) {
			wd.navigate().to(p.getProperty("HOME"));
		}

	}

}
