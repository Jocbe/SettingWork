package org.example;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.googlecode.htmleasy.ViewWith;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

import org.hibernate.Session;

@Path("/qsets")
public class QuestionSetController {

	@GET @Path("/{qsID}")
	@ViewWith("/soy/questions.questionsetview")
	public Map showQuestionSet(@PathParam("qsID") int qsID) {
		
		User u = new User("Bob", "bbb123");
		QuestionSet q = new QuestionSet(u);
		q.setName("Bob's first question set");
		
		
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		session.saveOrUpdate(u);
		session.saveOrUpdate(q);
		session.getTransaction().commit();
		session.close();
		
		
		session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		List questions = session.createQuery(
				"from Question where parentSet = ?")
				.setInteger(0, qsID).list();
		session.getTransaction().commit();
		session.close();
		
		/*session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		String name = ((QuestionSet)session.createQuery(
				"from QuestionSet where parentSet = ?")
				.setInteger(0, qsID)
				.uniqueResult())
				.getName();
		session.getTransaction().commit();
		session.close();*/
		
		String name = "n";
		return ImmutableMap.of("name", name, "questions", questions);
		
	}
}
