package business;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.wildfly.common.annotation.NotNull;

import model.BibleVerse;

@RequestScoped
@Path("/verses")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class BibleRestService {
	
	@Inject
	BibleBusinessInterface service;
	
	// POST Request to get Bible Verse Object
	@POST
	@Path("/getVerse")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
	public BibleVerse getBibleVerse(@NotNull @FormParam("book") String book,
	@NotNull @FormParam("chapterNum") int chapterNum,
	@NotNull @FormParam("verseNum") int verseNum) throws IOException {
		
		String queryString = "?q=" + book + "+" + chapterNum + ":" + verseNum;
		System.out.println(queryString);
		BibleVerse verse = service.readVerse(queryString);
		
		return verse;
	}
}
