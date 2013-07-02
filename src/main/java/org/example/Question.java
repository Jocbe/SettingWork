package org.example;

import java.util.List;
import java.util.LinkedList;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.FetchType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "QUESTIONS" )
public class Question {
	
	private QuestionSet parentSet;
	private String content;
	private User author;
	private int id;
	
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
