package org.example;

import java.util.List;
import java.util.LinkedList;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

@Entity
@Table( name = "QUESTIONS" )
public class Question {
	
	private int parentSet;
	private String content;
	private String author;
	
	public Question() {}
	public Question(String c, int pS, String o) {
		content = c;
		parentSet = pS;
		owner = o;
	}
	
	@ManyToOne
	public int getParentSet() {return parentSet;}
	
	@ManyToOne
	public String getAuthor() {return author;}
	
	public String content() {return content;}
	
	
	public void update(String c) {
		content = c;
	}
}
