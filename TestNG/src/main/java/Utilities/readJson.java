package Utilities;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class readJson {
	public static String ReadJson(String key) throws FileNotFoundException, IOException, ParseException  {

		JSONParser parser = new JSONParser();

			// Read the JSON file and parse it
			String path=System.getProperty("user.dir") + "\\src\\main\\java\\Utilities\\testdata.json" ;
			Object obj = parser.parse(new FileReader(path));

			// Convert the parsed object to a JSONObject
			JSONObject jsonObject = (JSONObject) obj;

			String Value = (String) jsonObject.get(key);
			System.out.println(key +" : "+Value); 


		
		return Value;


	}}
