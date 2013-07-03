package org.example.models;

import java.util.Set;
import java.util.HashSet;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;


@Entity
@Table( name = "USERS" )
public class User {
	private String name;
	/*private Set<QuestionSet> questionSets = new HashSet<QuestionSet>();
	private Set<Question> questions = new HashSet<Question>();*/
	private String id;
	
	public User() {}
	
	public User(String n, String i) {
		name = n;
		id = i;
	}
	
	
	@Id
	public String getId() {return id;}
	
	/*@OneToMany(mappedBy = "owner")
	public Set<QuestionSet> getQuestionSets() {return questionSets;}*/
	
	public String getName() {return name;}
	
	/*@OneToMany(mappedBy = "author")
	public Set<Question> getQuestions() {return questions;}*/
	
	
	/*public void addQuestion(Question q) {
		questions.add(q);
	}
	public void deleteQuestion(int i) {
		questions.remove(i);
	}
	public void addQuestionSet(QuestionSet qS) {
		questionSets.add(qS);
	}
	public void deleteQuestionSet(int i) {
		questionSets.remove(i);
	}*/
	
	public void setId(String i) {id = i;}
	public void setName(String n) {name = n;}
	//public void setQuestionSets(Set<QuestionSet> qS) {questionSets = qS;}
	//public void setQuestions(Set<Question> q) {questions = q;}
	
}
