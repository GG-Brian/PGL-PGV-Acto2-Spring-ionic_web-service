package org.briangg.library.controllers;

import java.util.List;
import java.util.Optional;

import org.briangg.library.models.Comic;
import org.briangg.library.services.IComicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@CrossOrigin(origins = "http://localhost:8100")    	// Restrict resources implemented, allow within this connection
@RestController										// Converts RESTful requests' response into JSON or XML data
public class ComicController{
	
	@Autowired										// Perform dependency auto-injection whenever there's important changes inside the class below
	IComicService comicService;
	
	@GetMapping("/comics")							// Get unespecified data from table 'comics' inside connected database (src/main/resources/application.properties)
	List<Comic> getAll(){
		return comicService.getAll();
	}
	
	@GetMapping("/comics/{id}")
	Comic getOne(@PathVariable("id") long id) {		// PathVariable Gets data from URI's end (http://.../user/3/invoices?date=12-05-2013, here it gets user 3's invoiced made at 12-5-2013)
		Optional<Comic> comicData = comicService.getOne(id);// Optional is a container for non-null objects and facilitate code to handle available or not objects
	
		if(comicData.isPresent()) {
			return comicData.get();
		};
		
		return null;
	}
	
	@PostMapping("/comics")							// table modification purposes (except Deletion and update)
	void add(Comic newComic) {
		System.out.println(newComic.getName());
		comicService.add(newComic);
	}
	
	@PostMapping(value="/comics", consumes="application/json")
	void addUsingJson(@RequestBody String comicString) {
		ObjectMapper mapeadorDatos = new ObjectMapper();
		try {
			Comic newComic = mapeadorDatos.readValue(comicString, Comic.class);
			comicService.add(newComic);
		} catch (JsonMappingException errorDuringMapping) {
			errorDuringMapping.printStackTrace();
		} catch (JsonProcessingException errorDuringProcessing) {
			errorDuringProcessing.printStackTrace();
		}
	}
	
	@PutMapping("/comics/{id}")
	void update(@PathVariable("id") long id, Comic comic) {
		System.out.println();
		comicService.update(id, comic);
	}
	
	
	@DeleteMapping("/comics/{id}")
	void delete(@PathVariable("id") long id) {
		System.out.println();
		comicService.delete(id);
	}
}
