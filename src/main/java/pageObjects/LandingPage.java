package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.Base;

public class LandingPage {

	public WebDriver driver;

	By signin = By.cssSelector("a[href*='sign_in']");
	By Featured = By.cssSelector("div[class='text-center'] h2");
	By course = By.xpath("(//a[normalize-space()='Courses'])[1]");

	public LandingPage(WebDriver driver2) {
		this.driver = driver2;
	}

	public LoginPage getLogin() throws IOException {
		// When login button navigate the test to another page(login page) that next page class should handle as below
		driver.findElement(signin).click();
		LoginPage lo= new LoginPage(driver);
		return lo;
		
	}

	public WebElement getFeaturedCourse() throws IOException {
		// driver = initializeDriver();
		return driver.findElement(Featured);

	}

	public WebElement getCourse() throws IOException {
		// driver = initializeDriver();
		return driver.findElement(course);
	}
}
