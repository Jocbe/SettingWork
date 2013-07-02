package org.example;

import com.googlecode.htmleasy.ViewWith;
import javax.ws.rs.Path;
import javax.ws.rs.GET;

@Path("/question")
public class QuestionController {
	
	private SessionFactory sessionFactory;
	
	public QuestionController() {
		sessionFactory = new Configuration()
			.configure()
			.buildSessionFactory();
	}
	
	@GET
	@Path("/{questionID}")
	@ViewWith("/soy/questions.question")
	public Question showQuestion(@PathParam("questionID") int questionID) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Question result = session.createQuery( 
				"from Question where question.id = ?")
				.setInt(0, questionID)
				.uniqueResult();
		
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
	
}
