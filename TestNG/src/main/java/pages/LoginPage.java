package pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.readJson;

public class LoginPage extends Base  {
	WebDriver driver;

	public LoginPage(WebDriver Driver){
		this.driver = Driver;

	}
	public void EnterEmail() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		implicitlyWait(100,driver);
		WebElement email  = driver.findElement(By.xpath("//input[@id='user']"));
		WebElement CountinueButton  = driver.findElement(By.xpath("//input[@type='submit']"));
		email.sendKeys(readJson.ReadJson("email"));
		CountinueButton.click();		
		implicitlyWait(1000,driver);

		
		
	}
	public void EnterPassword() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='password']")));

		WebElement password  = driver.findElement(By.xpath("//input[@id='password']")); 
		System.out.println(readJson.ReadJson("password"));
		password.sendKeys(readJson.ReadJson("password"));
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#login-submit")));

		WebElement loginbutton  = driver.findElement(By.cssSelector("#login-submit")); 
		loginbutton.click();

		}catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#login")));

			WebElement loginbutton  = driver.findElement(By.cssSelector("#login")); 
			loginbutton.click();

		}
		implicitlyWait(10000,driver);


		
		
	}
}
