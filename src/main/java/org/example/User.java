package org.example;

//import java.util.List;
//import java.util.LinkedList;
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
	//private List<QuestionSet> questionSets;
	//private List<Question> questions;
	private Set<QuestionSet> questionSets = new HashSet<QuestionSet>();
	private Set<Question> questions = new HashSet<Question>();
	//private String id;
	private int id;
	
	public User() {}
	
	//public User(String n, String i) {
	public User(String n, int i) {
		name = n;
		id = i;
		//questionSets = new LinkedList<QuestionSet>();
		//questions = new LinkedList<Question>();
	}
	
	
	@Id
	public int getId() {return id;}
	
	@OneToMany(mappedBy = "owner")
	//public List<QuestionSet> getQuestionSets() {return questionSets;}
	public Set<QuestionSet> getQuestionSets() {return questionSets;}
	
	public String getName() {return name;}
	
	@OneToMany(mappedBy = "author")
	//public List<Question> getQuestions() {return questions;}
	public Set<Question> getQuestions() {return questions;}
	
	
	public void addQuestion(Question q) {
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
	}
	
	public void setId(int i) {id = i;}
	public void setName(String n) {name = n;}
	public void setQuestionSets(Set<QuestionSet> qS) {questionSets = qS;}
	public void setQuestions(Set<Question> q) {questions = q;}
	
}
