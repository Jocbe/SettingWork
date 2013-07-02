package org.example;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.googlecode.htmleasy.ViewWith;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

import org.hibernate.Session;

@Path("/user")
public class UserController {
	
	@GET @Path("/{uID}")
	@ViewWith("/soy/questions.userview")
	public Map showQuestionSets(@PathParam("uID") String uID) {
		Session session = SessionFactoryManager.getInstance().openSession();
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
		
		return ImmutableMap.of("name", user.getName(), "questionsets", questionSets);
		
	}
}
