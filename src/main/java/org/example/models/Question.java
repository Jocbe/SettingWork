package org.example.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.ws.rs.FormParam;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "QUESTIONS" )
public class Question {
	
	@FormParam("parentSet") private QuestionSet parentSet;
	@FormParam("content") private String content;
	@FormParam("author") private User author;
	@FormParam("id") private int id;
	
	public Question() {}
	public Question(String c, QuestionSet pS, User a) {
		content = c;
		parentSet = pS;
		author = a;
	}
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	public int getId() {return id;}
	
	@ManyToOne
	public QuestionSet getParentSet() {return parentSet;}
	
	@ManyToOne
	public User getAuthor() {return author;}
	

	public String getContent() {return content;}
	
	
	public void update(String c) {
		content = c;
	}
	
	public void setId(int i) {id = i;}
	public void setParentSet(QuestionSet pS) {parentSet = pS;}
	public void setContent(String c) {content = c;}
	public void setAuthor(User a) {author = a;}
}
