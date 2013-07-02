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
		session.saveOrUpdate(new Question("question 1?", q, u));
		session.saveOrUpdate(new Question("How are you today?", q, u));
		session.saveOrUpdate(new Question("This is a question.", q, u));
		session.getTransaction().commit();
		session.close();
		
		
		session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		List questions = session.createQuery(
				"from Question where parentSet = ?")
				.setInteger(0, qsID).list();
		QuestionSet questionSet = ((QuestionSet)session.createQuery(
				"from QuestionSet where id = ?")
				.setInteger(0, qsID)
				.uniqueResult());
		session.getTransaction().commit();
		session.close();
		
		if (questionSet == null) {
			return ImmutableMap.of("name", "### Invalid question set ID! ###", "questions", null);
		}
		
		String name = questionSet.getName();
		return ImmutableMap.of("name", name, "questions", questions);
		
	}
}
