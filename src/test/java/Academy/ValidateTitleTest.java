package Academy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;

public class ValidateTitleTest extends Base {
	public WebDriver driver; 
	public static Logger log= LogManager.getLogger(Base.class.getName());
	@BeforeTest
	public void initialize() throws IOException {
		 driver=initializeDriver();	
		 log.info("Driver is initialized");
		 driver.get(prop.getProperty("url"));	
		 log.info("Navigate to Home page");
	}
	@Test
	public void titleNavigation() throws IOException {


		LandingPage l = new LandingPage(driver);
		l.getFeaturedCourse().getText();
		Assert.assertEquals(l.getFeaturedCourse().getText(), "FEATURED COURSES123");
		log.info("Successfullu navigated text message");

	}
	@AfterTest
	public void close() {
		driver.quit();
	}
}