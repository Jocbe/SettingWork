package org.example;

import java.util.List;
import java.util.LinkedList;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;


@Entity
@Table( name = "USERS" )
public class User {
	private String name;
	private List<QuestionSet> questionSets;
	private String id;
	
	public User() {}
	
	public User(String n, String i) {
		name = n;
		id = i;
		questionSets = new LinkedList<QuestionSet>();
	}
	
	@Id
	public String getID() {return id;}
	
	@OneToMany(mappedBy = "owner")
	public List<QuestionSet> getQuestionSets() {
		return questionSets;
	}
	
	public String getName() {return name;}
	
	
}
