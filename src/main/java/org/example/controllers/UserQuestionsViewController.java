package org.example.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.example.models.User;
import org.hibernate.Session;

import uk.ac.cam.cl.dtg.univdate.HibernateSessionRequestFilter;

import com.google.common.collect.ImmutableMap;
import com.googlecode.htmleasy.ViewWith;

@Path("/user")
public class UserQuestionsViewController {

	@Context
	HttpServletRequest servletRequest;
	
	@GET @Path("/{uID}/questions")
	@ViewWith("/soy/view.user.questions")
	public Map showQuestions(@PathParam("uID") String uID) {
		//Session session = SessionFactoryManager.getInstance().openSession();
		Session session = HibernateSessionRequestFilter.openSession(servletRequest);
		session.beginTransaction();
		List questions = session.createQuery("from Question where author = ?")
				.setString(0, uID)
				.list();
		String name = ((User)session.createQuery("from User where id = ?")
				.setString(0, uID)
				.uniqueResult())
				.getName();
		session.getTransaction().commit();
		session.close();
		
		return ImmutableMap.of(
				"name", name,
				"questions", questions,
				"id", uID);
	}
}
