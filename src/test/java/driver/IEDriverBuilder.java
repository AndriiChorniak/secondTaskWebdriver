package driver;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class IEDriverBuilder {

	public WebDriver initializeWebDriver() {
		File file = new File("D:\\Libraries\\Webdriver\\IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		WebDriver driver = new InternetExplorerDriver();
		return driver;
	}

}
