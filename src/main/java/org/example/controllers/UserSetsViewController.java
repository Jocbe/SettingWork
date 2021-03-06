package org.example.controllers;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.googlecode.htmleasy.ViewWith;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.example.models.User;
import org.hibernate.Session;

import uk.ac.cam.cl.dtg.univdate.HibernateSessionRequestFilter;

@Path("/user")
public class UserSetsViewController {

	@Context
	HttpServletRequest servletRequest;
	
	@GET @Path("/{uID}/sets")
	@ViewWith("/soy/view.user.sets")
	public Map showQuestionSets(@PathParam("uID") String uID) {
		//Session session = SessionFactoryManager.getInstance().openSession();
		Session session = HibernateSessionRequestFilter.openSession(servletRequest);
		session.beginTransaction();
		User user = (User)session.createQuery(
				"from User where id = ?")
				.setString(0, uID)
				.uniqueResult();
		List questionSets = session.createQuery(
				"from QuestionSet where owner = ?")
				.setString(0, uID)
				.list();
		session.getTransaction().commit();
		session.close();
		
		if (user == null) {
			return ImmutableMap.of("name", "USER DOES NOT EXIST", "questionsets", null);
		}
		
		return ImmutableMap.of(
				"name", user.getName(),
				"sets", questionSets,
				"id", user.getId());
		
	}
}
