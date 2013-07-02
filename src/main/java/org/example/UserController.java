package org.example;

import com.googlecode.htmleasy.ViewWith;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import org.hibernate.Session;

@Path("/user")
public class UserController {
	
	@GET @Path("/{uID}")
	@ViewWith("/soy/questions.userview")
	public User showQuestionSets(@PathParam("uID") String uID) {
		Session session = SessionFactoryManager.getInstance().openSession();
		session.beginTransaction();
		User result = (User)session.createQuery(
				"from User where id = ?")
				.setString(0, uID)
				.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return result;
	}
}
