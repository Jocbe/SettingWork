package org.example.controllers;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.example.SessionFactoryManager;
import org.example.models.Question;
import org.example.models.QuestionSet;
import org.example.models.User;
import org.hibernate.Session;
import org.jboss.resteasy.annotations.Form;

import com.google.common.collect.ImmutableMap;
import com.googlecode.htmleasy.RedirectException;
import com.googlecode.htmleasy.ViewWith;

@Path("/set")
public class QuestionSetController {
	
	@GET @Path("/")
	@ViewWith("/soy/questions.allquestionsets")
	public Map allQuestionSets() {
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		List sets = session.createQuery(
				"from QuestionSet")
				.list();
		session.getTransaction().commit();
		session.close();
		
		return ImmutableMap.of("sets", sets);
	}
	
	@GET @Path("/e404")
	@ViewWith("/soy/main.e404")
	public Map noSuchSet() {
		return ImmutableMap.of("what", "Question set");
	}

	@GET @Path("/{qsID}")
	@ViewWith("/soy/view.set")
	public Map showQuestionSet(@PathParam("qsID") int qsID) {
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		List questions = session.createQuery(
				"from Question where parentSet = ?")
				.setInteger(0, qsID)
				.list();
		
		QuestionSet questionSet = (QuestionSet) session.createQuery(
				"from QuestionSet where id = ?")
				.setInteger(0, qsID)
				.uniqueResult();
		session.getTransaction().commit();
		session.close();
		
		if (questionSet == null) {
			System.out.println("@@@@@@@@@@@@@@@@@@");
			throw new RedirectException("/set/e404");
		} else {
			System.out.println("###################");
			return ImmutableMap.of(
					"name", questionSet.getName(),
					"questions", questions,
					"id", questionSet.getId());
					//"owner", questionSet.getOwner());
		}
	}
	
	@GET @Path("/{id}/edit")
	@ViewWith("/soy/edit.set")
	public QuestionSet editQuestionSet(@PathParam("id") int id) {
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		QuestionSet result = (QuestionSet) session.createQuery(
				"from QuestionSet where id = ?")
				.setInteger(0, id)
				.uniqueResult();
		session.getTransaction().commit();
		session.close();
		
		if (result == null) {
			throw new RedirectException("/set/e404");
		} else {
			return result;
		}
	}
	
	@GET @Path("/add")
	@ViewWith("/soy/edit.set")
	public QuestionSet addQuestionSet() {
		return new QuestionSet();
	}
	
	@POST @Path("/{id}")
	@ViewWith("/soy/view.set")
	public QuestionSet saveQuestionSet(@Form QuestionSet s) {
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		session.saveOrUpdate(s);
		session.getTransaction().commit();
		session.close();
		throw new RedirectException("/set/" + s.getId());
	}
	
	private void addDummyQuestionSet(String name) {
		User u = new User("Bob", "bbb123");
		QuestionSet q = new QuestionSet(u);
		q.setName(name);
		
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		session.saveOrUpdate(u);
		session.saveOrUpdate(q);
		session.saveOrUpdate(new Question(name+"'s question 1", q, u));
		session.saveOrUpdate(new Question(name+"'s question 2", q, u));
		session.saveOrUpdate(new Question(name+"'s question 3", q, u));
		session.getTransaction().commit();
		session.close();
	}
}
