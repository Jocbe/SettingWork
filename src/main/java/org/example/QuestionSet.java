package org.example;

//import java.util.List;
//import java.util.LinkedList;
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
	//private List<Question> questions;
	private Set<Question> questions = new HashSet<Question>();
	//private String owner;
	private User owner;
	private int id;
	private String name;
	
	
	public QuestionSet() {}
	//public QuestionSet(String o) {
	public QuestionSet(User o) {
		owner = o;
		//questions = new LinkedList<Question>();
	}
	//public QuestionSet(String o, String n, List<Question> q) {
	//public QuestionSet(int o, String n, List<Question> q) {
	public QuestionSet(User o, String n, Set<Question> q) {
		owner = o;
		questions = q;
		name = n;
	}	
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	public int getId() {return id;}
	
	
	@ManyToOne(/*targetEntity = User.class, fetch=FetchType.LAZY*/)
	public User getOwner() {return owner;}
	//public String getOwner() {return owner;}
	
	@OneToMany(mappedBy="parentSet")
	public Set<Question> getQuestions() {return questions;}
	//public List<Question> getQuestions() {return questions;}
	
	public String getName() {return name;}
	
	public void addQuestion(Question q) {
		questions.add(q);
	}
	public void deleteQuestion(int i) {
		questions.remove(i);
	}
	
	
	public void setId(int i) {id = i;}
	public void setQuestions(Set q) {questions = q;}
	public void setOwner(User o) {owner = o;}
	public void setName(String n) {name = n;}
}
