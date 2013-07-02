package org.example;

import java.util.List;
import java.util.LinkedList;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
@Table( name = "QUESTIONSETS" )
public class QuestionSet {
	private List<Question> questions;
	private String owner;
	private int id;
	private String name;
	
	public QuestionSet() {}
	public QuestionSet(String o) {
		owner = o;
		questions = new LinkedList<Question>();
	}
	public QuestionSet(String o, String n, List<Question> q)
		owner = o;
		questions = q;
		name = n;
	}	
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	public int getId() {return id;}
	
	@ManyToOne
	public String getOwner() {return owner;}
	
	@OneToMany(mappedBy="parentSet");
	public List<Question> getQuestions() {return questions;}
	
	public String getName() {return name;}
	
	public void addQuestion(Question q) {
		questions.add(q);
	}
	public void deleteQuestion(int i) {
		questions.remove(i);
	}
	public void setName(String n) {
		name = n;
	}
}
