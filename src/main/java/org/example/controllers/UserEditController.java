package org.example.controllers;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.example.SessionFactoryManager;
import org.example.models.User;
import org.hibernate.Session;
import org.jboss.resteasy.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.googlecode.htmleasy.RedirectException;
import com.googlecode.htmleasy.ViewWith;

@Path("/user")
public class UserEditController {
	
	//private static Logger log = LoggerFactory. //LoggerFactory.getLogger(UserEditController.class);
	
	
	@GET @Path("/")
	@ViewWith("/soy/view.user.listall")
	public Map viewEntireUserList() {
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) session.createQuery("from User").list();
		
		session.getTransaction().commit();
		session.close();
		
		return ImmutableMap.of("users", users);
	}
	
	@GET @Path("/add")
	@ViewWith("/soy/edit.adduser")
	public Map viewAddUser() {
		return ImmutableMap.of();
	}
	
	@POST @Path("/add")
	@ViewWith("/soy/view.listall")
	public void addUser(@Form User u) {
		Logger log = LoggerFactory.getLogger(UserEditController.class);
		log.info("Trying to add user " + u.getId() + "...");
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		User existingUser = (User)session.createQuery("from User where id = ?")
			.setString(0, u.getId())
			.uniqueResult();
		session.getTransaction().commit();
		session.close();
		// If user already exists do nothing
		// TODO: some kind of error message
		if(existingUser != null) {
			log.error("Couldn't add user " + u.getId() + ". User already exists.");
			return;
		}
		
		session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		session.save(u);
		session.getTransaction().commit();
		session.close();
		
		log.info("Successfully added user " + u.getId());
		
		throw new RedirectException("/user");
		
		
	}
	
	@POST @Path("/update")
	public void updateUser(@Form User u) {
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		session.update(u);
		session.getTransaction().commit();
		session.close();
		
		throw new RedirectException("/user");
		
	}
	
	@GET @Path("/edit/{uID}")
	@ViewWith("/soy/edit.user")
	public User viewEditUser(@PathParam("uID") String uID) {
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		User u = (User)session.createQuery("from User where id = ?")
			.setString(0, uID)
			.uniqueResult();
		session.getTransaction().commit();
		session.close();
		
		return u;
	}
	
	@GET @Path("/delete/{uID}")
	public void deleteUser(@PathParam("uID") String uID) {
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		//TODO: find more elegant version for the line following
		session.delete(session.createQuery("from User where id = ?").setString(0, uID).uniqueResult());
		session.getTransaction().commit();
		session.close();
		throw new RedirectException("/user");
	}
}

