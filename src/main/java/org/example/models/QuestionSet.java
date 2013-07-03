package org.example.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.ws.rs.FormParam;

import org.example.SessionFactoryManager;
import org.hibernate.Session;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "QUESTIONSETS" )
public class QuestionSet {
	//private Set<Question> questions = new HashSet<Question>();
	@FormParam("owner") private User owner;
	@FormParam("id") private int id;
	@FormParam("name") private String name;
	
	
	public QuestionSet() {
		name = "Name";
	}
	public QuestionSet(User o) {
		owner = o;
		name = "Name";
	}
	public QuestionSet(User o, String n) {
		owner = o;
		//questions = q;
		name = n;
	}
	
	public static QuestionSet fromString(String sid) {
		int id;
		try {
			id = Integer.parseInt(sid);
		} catch (Exception e) {
			return null;
		}
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		QuestionSet result = (QuestionSet) session.createQuery("from QuestionSet where id = ?")
			.setInteger(0, id)
			.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return result;
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
