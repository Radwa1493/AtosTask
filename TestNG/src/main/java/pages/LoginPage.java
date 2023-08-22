package pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
		implicitlyWait(100,driver);
		Thread.sleep(1000);

		
		
	}
	public void EnterPassword() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		WebElement password  = driver.findElement(By.xpath("//input[@id='password']")); 
		password.sendKeys(readJson.ReadJson("password"));
		Thread.sleep(4000);
		try {
		WebElement loginbutton  = driver.findElement(By.cssSelector("#login-submit")); 
		loginbutton.click();

		}catch (Exception e) {
			WebElement loginbutton  = driver.findElement(By.cssSelector("#login")); 
			loginbutton.click();

		}
		implicitlyWait(1000,driver);


		
		
	}
}
