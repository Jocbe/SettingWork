package org.example.models;

import java.util.Set;
import java.util.HashSet;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.FetchType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "QUESTIONSETS" )
public class QuestionSet {
	//private Set<Question> questions = new HashSet<Question>();
	private User owner;
	private int id;
	private String name;
	
	
	public QuestionSet() {}
	public QuestionSet(User o) {
		owner = o;
	}
	public QuestionSet(User o, String n, Set<Question> q) {
		owner = o;
		//questions = q;
		name = n;
	}	
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	public int getId() {return id;}
	
	
	@ManyToOne
	public User getOwner() {return owner;}
	
	/*@OneToMany(mappedBy="parentSet")
	public Set<Question> getQuestions() {return questions;}*/
	
	public String getName() {return name;}
	
	/*public void addQuestion(Question q) {
		questions.add(q);
	}
	public void deleteQuestion(int i) {
		questions.remove(i);
	}*/
	
	
	public void setId(int i) {id = i;}
	//public void setQuestions(Set q) {questions = q;}
	public void setOwner(User o) {owner = o;}
	public void setName(String n) {name = n;}
}