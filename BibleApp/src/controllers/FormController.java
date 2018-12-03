package controllers;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import business.BibleBusinessInterface;

@ManagedBean
@ViewScoped
public class FormController {
	
	@Inject
	BibleBusinessInterface service;
	
	public void onClick() throws IOException {
		service.readVerse("?q=John+3:16");
	}
	
	public BibleBusinessInterface getService() {
		return service;
	}

}
