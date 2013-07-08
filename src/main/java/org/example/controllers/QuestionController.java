package org.example.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.example.models.Question;
import org.example.models.QuestionSet;
import org.example.models.User;
import org.hibernate.Session;
import org.jboss.resteasy.annotations.Form;

import uk.ac.cam.cl.dtg.univdate.HibernateSessionRequestFilter;

import com.google.common.collect.ImmutableMap;
import com.googlecode.htmleasy.RedirectException;
import com.googlecode.htmleasy.ViewWith;

@Path("/question")
public class QuestionController {

	@Context
	HttpServletRequest servletRequest;
	
	@GET
	@Path("/")
	@ViewWith("/soy/questions.allquestions")
	public Map<String, List> allQuestions() {
		//Session session = SessionFactoryManager.getInstance().openSession();
		Session session = HibernateSessionRequestFilter.openSession(servletRequest);
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
		//Session session = SessionFactoryManager.getInstance().openSession();
		Session session = HibernateSessionRequestFilter.openSession(servletRequest);
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
		//Session session = SessionFactoryManager.getInstance().openSession();
		Session session = HibernateSessionRequestFilter.openSession(servletRequest);
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
		//Session session = SessionFactoryManager.getInstance().openSession();
		Session session = HibernateSessionRequestFilter.openSession(servletRequest);
		session.beginTransaction();
		QuestionSet s = new QuestionSet();
		session.save(s);
		session.getTransaction().commit();
		session.close();
		return new Question("Content", s, null);
	}
	
	@GET
	@Path("/add/{parentID}")
	@ViewWith("/soy/edit.question")
	public Question addQuestion(@PathParam("parentID") int parentID) {
		QuestionSet parentSet = QuestionSet.fromString(parentID+"");
		User author = parentSet.getOwner();
		return new Question("Content", parentSet, author);
	}

	@POST
	@Path("/{questionID}")
	@ViewWith("/soy/view.question")
	public void saveQuestion(@Form Question q) {
		//Session session = SessionFactoryManager.getInstance().openSession();
		Session session = HibernateSessionRequestFilter.openSession(servletRequest);
		session.beginTransaction();
		session.saveOrUpdate(q);
		session.getTransaction().commit();
		session.close();
		throw new RedirectException("/set/" + q.getParentSet().getId());
	}
	
	@GET
	@Path("/{id}/fork")
	@ViewWith("/soy/edit.fork.question")
	public Map forkQuestion(@PathParam("id") int id) {
		//Session session = SessionFactoryManager.getInstance().openSession();
		Session session = HibernateSessionRequestFilter.openSession(servletRequest);
		session.beginTransaction();
		List sets = session.createQuery("from QuestionSet")
			.list();
		session.getTransaction().commit();
		session.close();
		return ImmutableMap.of(
				"id", id,
				"sets", sets);
	}
	
	@POST @GET
	@Path("/{id}/fork/save")
	public void forkQuestion(@PathParam("id") int id, @Form QuestionSet set) {
		//Session session = SessionFactoryManager.getInstance().openSession();
		Session session = HibernateSessionRequestFilter.openSession(servletRequest);
		session.beginTransaction();
		Question old = (Question) session.createQuery(
				"from Question where id=?")
				.setInteger(0, id)
				.uniqueResult();
		session.save(new Question(old.getContent(), set, old.getAuthor()));
		session.getTransaction().commit();
		session.close();
		throw new RedirectException("/set/"+set.getId());
	}
}