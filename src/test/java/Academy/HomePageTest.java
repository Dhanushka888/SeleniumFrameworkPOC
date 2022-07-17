package Academy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;

public class HomePageTest extends Base {
	public WebDriver driver; // This initialization important when parallel test running.Base webDriver will
								// initialize parallel and get confused

	public static Logger log = LogManager.getLogger(Base.class.getName());

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Home page initialized");

	}

	@Test(dataProvider = "getData")
	public void homePageNavigation(String userName, int password, String text) throws IOException {

		driver.get(prop.getProperty("url"));

		LandingPage l = new LandingPage(driver);
		LoginPage lo = l.getLogin();
		lo.getEmail().sendKeys(userName);
		lo.getPassword().sendKeys(Integer.toString(password));
		// System.out.println(text);
		log.info(text);
		lo.getLogin().click();

	}

	@DataProvider
	public Object[][] getData() {
		// Object[][] data=new Object[2][3];
		Object[][] data =
				// new Object[][]
				{ { "Resticted.com", 89989, "Resticted user" },
						{ "Not Resticted@gfgf.com", 899898, "Not Resticted user" } };
		return data;
	}

	@AfterTest
	public void close() {
		driver.quit();
	}
}
