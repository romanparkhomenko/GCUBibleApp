package database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.json.JSONArray;
import org.json.JSONObject;

import model.BibleVerse;


@Stateless
@Local(DataAccessInterface.class)
public class DataAccessService implements DataAccessInterface {
	
	// Declare variables to connect to Crossway ESV API 
	private static final String ESV_URL = "https://api.esv.org/v3/passage/text/";
	private static final String ESV_TOKEN = "192d6a9fd236faacd187112dc640d777a06873a2";
	
	// Function to connect to API and get JSON Response. Returns constructed BibleVerse
	public static BibleVerse getESVAPI(String queryString) throws IOException {
		
	    URL url = new URL(ESV_URL + queryString);
	    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
	    
	    //Request Method and Header
	    urlConnection.setRequestMethod("GET");
	    urlConnection.setRequestProperty("Content-Type", "application/" + "json");
	    urlConnection.setRequestProperty("Authorization", "Token " + ESV_TOKEN);
	    
	    urlConnection.connect();
	    
	    if (urlConnection.getResponseCode() != 200) {
	        throw new RuntimeException("Failed : HTTP error code : "
	                + urlConnection.getResponseCode());
	    }
	    
	    // Read Response into String Builder
	    BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
	            (urlConnection.getInputStream())));
	    StringBuilder builder = new StringBuilder();
	    String line;
	    while ((line = responseBuffer.readLine()) != null) {
	        builder.append(line + "\n");
	    }
	    
	    // Convert StringBuilder to json object.
	    JSONObject json = new JSONObject(builder.toString());
	    
	    // Console log entire json result.
	    System.out.println("Output from Server:\n");
	    System.out.println(json.toString());
	    
	    // Create Bible Verse Object, parse JSON for key/values, set BibleVerse properties
	    BibleVerse verse = new BibleVerse();
	    String query = json.getString("query");
	    JSONArray parsed = json.getJSONArray("parsed");
	    String passages = json.getJSONArray("passages").getString(0);
	    
	    verse.setVerseQuery(query);
	    verse.setParsedLocation(parsed);
	    verse.setPassages(passages);
	    
	    System.out.println(verse.getPassages());
	    
	    // Close API Connection
	    urlConnection.disconnect();
	    
	    //Return BibleVerse Object
	    return verse;
	}
	
	//Read Verse function that returns verse object to manipulate.
	@Override
	public BibleVerse readVerse(String queryString) throws IOException {
		BibleVerse verse = getESVAPI(queryString);
		return verse;
	}
	
}