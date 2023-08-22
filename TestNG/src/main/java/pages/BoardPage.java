package pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.readJson;

public class BoardPage extends Base  {
	WebDriver driver;

	public BoardPage(WebDriver Driver){
		this.driver = Driver;

	}
	public void MoveCard() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		WebElement Card  = driver.findElement(By.xpath("//*[contains(text(),'APIautomatedcard')]"));

		Card.click();
		WebElement Move  = driver.findElement(By.xpath("//a[@class='button-link js-move-card']"));
		Move.click();
		WebElement lists  = driver.findElement(By.xpath("//select[@class='js-select-list']"));
		lists.click();
		WebElement Doing  = driver.findElement(By.xpath("//option[contains(text(), 'Doing')]"));
		Doing.click();
		WebElement MoveButton  = driver.findElement(By.xpath("//input[@value='Move']"));
		MoveButton.click();
		WebElement CloseButton  = driver.findElement(By.xpath("//a[@aria-label='Close dialog']"));
		CloseButton.click();
	}
	
	
public void AddCard() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		
	System.out.println("Add card from UI");
	WebElement AddCard  = driver.findElement(By.xpath("(//*[contains(text(), 'Add a card')])[1]"));
	AddCard.click();
	WebElement ToDotextarea  = driver.findElement(By.xpath("(//textarea)[2]"));
	ToDotextarea.sendKeys("UIautomationCard");
	
	WebElement AddCard2  = driver.findElement(By.xpath("//input[@value='Add card']"));
	AddCard2.click();	}
}
