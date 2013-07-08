package org.example.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.example.models.User;
import org.hibernate.Session;

import uk.ac.cam.cl.dtg.univdate.HibernateSessionRequestFilter;

import com.googlecode.htmleasy.ViewWith;

@Path("/user")
public class UserSummaryController {

	@Context
	HttpServletRequest servletRequest;
	
	@GET @Path("/{uID}")
	@ViewWith("/soy/view.user.summary")
	public User showUserSummary(@PathParam("uID") String uID) {
		//Session session = SessionFactoryManager.getInstance().openSession();
		Session session = HibernateSessionRequestFilter.openSession(servletRequest);
		session.beginTransaction();
		User user = (User)session.createQuery("from User where id = ?")
				.setString(0, uID)
				.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return user;
	}
}
