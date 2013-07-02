package org.example;

import java.util.List;
import java.util.LinkedList;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
//import javax.persistence.FetchType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "QUESTIONS" )
public class Question {
	
	private int parentSet;
	private String content;
	//private String author;
	private int author;
	private int id;
	
	public Question() {}
	//public Question(String c, int pS, String a) {
	public Question(String c, int pS, int a) {
		content = c;
		parentSet = pS;
		author = a;
	}
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	public int getId() {return id;}
	
	@ManyToOne(/*fetch=FetchType.LAZY*/)
	public int getParentSet() {return parentSet;}
	
	@ManyToOne(/*fetch=FetchType.LAZY*/)
	public int getAuthor() {return author;}
	//public String getAuthor() {return author;}
	
	public String content() {return content;}
	
	
	public void update(String c) {
		content = c;
	}
}
