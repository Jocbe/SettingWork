package org.example.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.example.SessionFactoryManager;
import org.example.models.User;
import org.hibernate.Session;

import com.googlecode.htmleasy.ViewWith;

@Path("/user")
public class UserSummaryController {
	
	@GET @Path("/{uID}")
	@ViewWith("/soy/view.user.summary")
	public User showUserSummary(@PathParam("uID") String uID) {
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		User user = (User)session.createQuery("from User where id = ?")
				.setString(0, uID)
				.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return user;
	}
}
