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
import com.googlecode.htmleasy.ViewWith;

@Path("/")
public class MainController {
	
	@GET 
	@Path("/")
	@ViewWith("/soy/main.index")
	public Map<String, ?> showIndex() {
		return ImmutableMap.of();
	}
	
	@GET @Path("/addDemoData")
	public void addDemoData() {
		User a = new User("Bob", "bbb123");
		User b = new User("Alice", "aaa456");
		
		QuestionSet p = new QuestionSet(a, "Bobbies first set");
		QuestionSet q = new QuestionSet(b, "Alice is cool");
		QuestionSet r = new QuestionSet(a, "This is his 2nd");
		
		Question q1 = new Question("content question", p, b);
		Question q2 = new Question("How are you", p, a);
		
		
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		session.save(a);
		session.save(b);
		session.save(p);
		session.save(q);
		session.save(r);
		session.save(q1);
		session.save(q2);
		session.getTransaction().commit();
		session.close();
	}

}


