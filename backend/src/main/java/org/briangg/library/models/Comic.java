package org.briangg.library.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity													// Entity classes become tables in relational databases
@Table(name = "comics")									// Use table 'comics' for end of convertion
public class Comic implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	String name;
	String genre;
	int panels;
	Timestamp updated;
	String description;
	String webpage;
	
	public Comic() {}
	public Comic(long id, String name, String genre, int panels, Timestamp updated, String description, String webpage) {
		super();
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.panels = panels;
		this.updated = updated;
		this.description = description;
		this.webpage = webpage;
	}
	
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getGenre() { return genre; }
	public void setGenre(String genre) { this.genre = genre; }
	
	public int getPanels() { return panels; }
	public void setPanels(int panels) { this.panels = panels; }
	
	public Timestamp getUpdated() { return updated; }
	public void setUpdated(Timestamp updated) { this.updated = updated; }
	
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	public String getWebpage() { return webpage;}
	public void setWebpage(String webpage) { this.webpage = webpage; }
	
}