package business;

import java.io.IOException;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import database.DataAccessInterface;
import model.BibleVerse;

@Stateless
@Local(BibleBusinessInterface.class)
public class BibleBusinessService implements BibleBusinessInterface {
	
	@Inject
	DataAccessInterface db;
	
	@Override
	public BibleVerse readVerse(String queryString) throws IOException {
		return db.readVerse(queryString);
	}

}
