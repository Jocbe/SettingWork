package org.example.controllers;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.example.SessionFactoryManager;
import org.example.models.Question;
import org.hibernate.Session;
import org.jboss.resteasy.annotations.Form;

import com.google.common.collect.ImmutableMap;
import com.googlecode.htmleasy.RedirectException;
import com.googlecode.htmleasy.ViewWith;

@Path("/question")
public class QuestionController {

	@GET
	@Path("/")
	@ViewWith("/soy/questions.allquestions")
	public Map<String, List> allQuestions() {
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		List result = session.createQuery("from Question").list();
		session.getTransaction().commit();
		session.close();
		return ImmutableMap.of("questions", result);
	}

	@GET
	@Path("/e404")
	@ViewWith("/soy/main.e404")
	public Map noSuchQuestion() {
		return ImmutableMap.of();
	}

	@GET
	@Path("/{questionID}")
	@ViewWith("/soy/view.question")
	public Question showQuestion(@PathParam("questionID") int questionID) {
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		Question result = (Question) session
				.createQuery("from Question where id = ?")
				.setInteger(0, questionID).uniqueResult();

		session.getTransaction().commit();
		session.close();
		if (result == null) {
			throw new RedirectException("/question/e404");
		} else {
			return result;
		}
	}

	@GET
	@Path("/{questionID}/edit")
	@ViewWith("/soy/edit.question")
	public Question editQuestion(@PathParam("questionID") int questionID) {
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		Question result = (Question) session
				.createQuery("from Question where id = ?")
				.setInteger(0, questionID).uniqueResult();

		session.getTransaction().commit();
		session.close();

		if (result == null) {
			throw new RedirectException("/question/e404");
		} else {
			return result;
		}
	}

	@GET
	@Path("/add")
	@ViewWith("/soy/edit.question")
	public Question addQuestion() {
		return new Question("Content", null, null);
	}

	@POST
	@Path("/{questionID}")
	@ViewWith("/soy/view.question")
	public void saveQuestion(@Form Question q) {
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		session.saveOrUpdate(q);
		session.getTransaction().commit();
		session.close();
		throw new RedirectException("/question/" + q.getId());
	}
}