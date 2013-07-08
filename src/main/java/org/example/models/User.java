package org.example.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;

import org.hibernate.Session;

import uk.ac.cam.cl.dtg.univdate.HibernateSessionRequestFilter;


@Entity
@Table( name = "USERS" )
public class User {
	@FormParam("name") private String name;
	/*private Set<QuestionSet> questionSets = new HashSet<QuestionSet>();
	private Set<Question> questions = new HashSet<Question>();*/
	@FormParam("id") private String id;

	@Context
	static HttpServletRequest servletRequest;
	
	public User() {}
	
	public User(String n, String i) {
		name = n;
		id = i;
	}
	
	public static User fromString(String crsid) {
		//Session session = SessionFactoryManager.getInstance().openSession();
		Session session = HibernateSessionRequestFilter.openSession(servletRequest);
		session.beginTransaction();
		User result = (User) session.createQuery("from User where id = ?")
			.setString(0, crsid)
			.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return result;
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
