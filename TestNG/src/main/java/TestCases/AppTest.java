package TestCases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.Base;
import pages.BoardPage;
import pages.LandingPage;
import pages.LoginPage;
import Utilities.readJson;


public class AppTest extends Base
{	
	static LoginPage Loginpage   = null;
	static LandingPage Landingpage   = null;
	static BoardPage Boardpage = null;

	public static String id_Card = null;
	public static String id_Board = null;
	public static String id_list = null;

	public static  WebDriver driver= null;
	public static SoftAssert softAssertion= new SoftAssert();
	//public static  RemoteWebDriver driver = null;

	@Parameters({ "browser" })
	@BeforeTest
	public static void setUp(String browser) throws MalformedURLException 
	{
		System.out.println("Starting..... ");	
	if (browser.equals("Chrome") ){
			System.out.println("Open Local Chrome");		
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("incognito");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(options); 
		}
		driver.manage().window().maximize();
		System.out.println("Open URL");
		driver.get("https://trello.com/login");
		 Loginpage   = new LoginPage(driver);
		 Landingpage   = new LandingPage(driver);
		 Boardpage   = new BoardPage(driver);

		 

	}

	@Test(priority = 1)
	public void Login() throws UnirestException, InterruptedException, FileNotFoundException, IOException, ParseException
	{
		System.out.println("Login");
		Loginpage.EnterEmail();
		Loginpage.EnterPassword();
			}



	@Test(priority = 2)
	public void CreateBoard_api() throws UnirestException, FileNotFoundException, IOException, ParseException
	{
		System.out.println("Create Board");

		// This code sample uses the  'Unirest' library:
		// http://unirest.io/java.html
		HttpResponse<JsonNode> response = Unirest.post("https://api.trello.com/1/boards/")
				.queryString("key", readJson.ReadJson("key"))
				.queryString("token", readJson.ReadJson("token"))
				.queryString("name", "automatedBoard")
				.asJson();
		System.out.println(response.getBody());
		int statusCode = response.getStatus();	            
		System.out.println(statusCode);

		JsonNode json = response.getBody();

		id_Board = json.getObject().getString("id");

		System.out.println(id_Board);

		// Assert on status code
		assert id_Board != null : "ID is null in the response";
		assert statusCode == 200 : "Create Board Expected status code 200, but got " + statusCode;


	}

	@Test(priority = 3)
	public void GetList_api() throws UnirestException, FileNotFoundException, IOException, ParseException
	{
		System.out.println("Get Board : "+id_Board+" List");

		// This code sample uses the  'Unirest' library:
		// http://unirest.io/java.html
		HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/boards/"+id_Board+"/lists")
				.header("Accept", "application/json")
				.queryString("key", readJson.ReadJson("key"))
				.queryString("token", readJson.ReadJson("token"))
				.asJson();

		System.out.println(response.getBody());
		int statusCode = response.getStatus();
		System.out.println(statusCode);

		JSONArray lists = response.getBody().getArray();

		JSONObject list = lists.getJSONObject(0);
		id_list = list.getString("id");

		System.out.println(id_list);	

		// Assert on status code
		assert statusCode == 200 : "Get List at the Board Expected status code 200, but got " + statusCode;

	}

	@Test (priority = 4)
	public void CreateCard_api() throws UnirestException, FileNotFoundException, IOException, ParseException
	{
		System.out.println("Create Card");
		HttpResponse<JsonNode> response = Unirest.post("https://api.trello.com/1/cards")
				.header("Accept", "application/json")
				.queryString("idList", id_list)
				.queryString("key", readJson.ReadJson("key"))
				.queryString("token", readJson.ReadJson("token"))
				.queryString("name", "APIautomatedcard")
				.asJson();

		System.out.println(response.getBody());

		JsonNode json = response.getBody();
		id_Card = json.getObject().getString("id");
		System.out.println(id_Card);

		int statusCode = response.getStatus();
		System.out.println(statusCode);

		// Assert on status code
		assert statusCode == 200 : "Create Card Expected status code 200, but got " + statusCode;
		assert id_Card != null : "ID is null in the response";



	}

	@Test (priority = 5)
	public void moveCard() throws UnirestException, FileNotFoundException, IOException, ParseException, InterruptedException
	{
		System.out.println("Move the card to the next list");

		Landingpage.OpenBoard();
		Boardpage.MoveCard();	
		
	}
	@Test (priority = 7)
	public void AddCard() throws UnirestException, FileNotFoundException, IOException, ParseException, InterruptedException
	{

		Boardpage.AddCard();
	}


}
