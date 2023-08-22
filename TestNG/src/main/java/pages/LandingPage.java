package pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.readJson;

public class LandingPage extends Base  {
	WebDriver driver;

	public LandingPage(WebDriver Driver){
		this.driver = Driver;

	}
	public void OpenBoard() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		WebElement Board  = driver.findElement(By.xpath("//*[contains(text(),'automatedBoard')]"));
		

		Board.click();
		
	}
}
