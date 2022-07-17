package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	public WebDriver driver;

	By emai = By.id("user_email");
	By password = By.id("user_password");
	By login= By.name("commit");

	public LoginPage(WebDriver driver2) {
		this.driver = driver2;
	}

	public WebElement getEmail() {
		return driver.findElement(emai);
	}

	public WebElement getPassword() {
		return driver.findElement(password);
	}
	public WebElement getLogin() {
		return driver.findElement(login);
	}
}
