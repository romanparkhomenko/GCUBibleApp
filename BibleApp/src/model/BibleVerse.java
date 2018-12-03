package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONArray;

@ManagedBean
@SessionScoped
@XmlRootElement(name="BibleVerse") 
public class BibleVerse {
	
	// Random properties to test
	private String query;
	private JSONArray parsed;
	private String passages;
	
	//Bible Verse Default Constructor
	public BibleVerse() {
		
	}
	
	// Getters for verse data
	public String getVerseQuery() {return query;}
	public JSONArray getParsedLocation() {return parsed;}
	public String getPassages() {return passages;}
	
	// Setters for verse data
	public void setVerseQuery(String query) {this.query = query;}
	public void setParsedLocation(JSONArray parsed) {this.parsed = parsed;}
	public void setPassages(String passages) {this.passages = passages;}
}
