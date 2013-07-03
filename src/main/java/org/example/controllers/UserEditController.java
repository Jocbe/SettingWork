package org.example.controllers;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.example.SessionFactoryManager;
import org.example.models.User;
import org.hibernate.Session;

import com.google.common.collect.ImmutableMap;
import com.googlecode.htmleasy.ViewWith;

@Path("/user")
public class UserEditController {
	
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
	
	
}

