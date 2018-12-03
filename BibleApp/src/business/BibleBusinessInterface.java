package business;

import java.io.IOException;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.BibleVerse;

@Stateless
@Local
public interface BibleBusinessInterface {
	public BibleVerse readVerse(String queryString) throws IOException;
}
