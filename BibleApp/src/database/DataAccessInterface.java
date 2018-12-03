package database;

import java.io.IOException;

import javax.ejb.Local;

import model.BibleVerse;

@Local
public interface DataAccessInterface {
	public BibleVerse readVerse(String queryString) throws IOException;
}
