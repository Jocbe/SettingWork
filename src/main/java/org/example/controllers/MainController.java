package org.example.controllers;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.example.SessionFactoryManager;
import org.example.models.Question;
import org.example.models.QuestionSet;
import org.example.models.User;
import org.hibernate.Session;

import com.google.common.collect.ImmutableMap;
import com.googlecode.htmleasy.RedirectException;
import com.googlecode.htmleasy.ViewWith;

@Path("/")
public class MainController {
	static boolean demoDataAdded = false;
	
	@GET 
	@Path("/")
	@ViewWith("/soy/main.index")
	public Map<String, ?> showIndex() {
		if (demoDataAdded == false)
			addDemoData();
		return ImmutableMap.of();
	}
	
	@GET @Path("/addDemoData")
	public void addDemoData() {
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		User b = new User("Bob", "bbb12");
		User a = new User("Alice", "aaa98");

		QuestionSet p = new QuestionSet(a, "Foundations of Computer Science example sheet");

		Question q1 = new Question("Write a function [square n], that calculates square of n.", p, a);
		Question q2 = new Question("Write a function [factorial n], that calculates factorial of n.", p, a);
		Question q3 = new Question("Write a function [power n k], that calculates k-th power of n.", p, a);

		session.save(a);
		session.save(b);
		session.save(p);
		session.save(q1);
		session.save(q2);
		session.save(q3);

		session.getTransaction().commit();
		session.close();
		demoDataAdded = true;
	}

}


