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

public class ValidateSourceLinkTest extends Base {
	public WebDriver driver; 
	public static Logger log= LogManager.getLogger(Base.class.getName());
	@BeforeTest
	public void initialize() throws IOException {
		 driver=initializeDriver();		 
		 driver.get(prop.getProperty("url"));	
		 log.info("ValidateSourceLink is initialized");
	}
	@Test
	public void sourceLinkNavigation() throws IOException {


		LandingPage l = new LandingPage(driver);
	
		//validate course link is there
		Boolean crs=l.getCourse().isDisplayed();
		
		Assert.assertTrue(crs);
		log.info("validate course link is there - OK");

	}
	@AfterTest
	public void close() {
		driver.quit();
	}
}