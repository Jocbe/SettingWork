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
			throw new RedirectException("/set/e404");
		} else {
			return ImmutableMap.of(
					"name", questionSet.getName(),
					"questions", questions,
					"id", questionSet.getId());
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
}
