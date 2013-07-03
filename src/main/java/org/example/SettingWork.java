package org.example;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.example.controllers.MainController;
import org.example.controllers.QuestionController;
import org.example.controllers.QuestionSetController;
import org.example.controllers.UserController;

import com.googlecode.htmleasy.HtmleasyProviders;

public class SettingWork extends Application {

	public Set<Class<?>> getClasses() {
		SessionFactoryManager.getInstance();
		Set<Class<?>> myServices = new HashSet<Class<?>>();

		// Add my own JAX-RS annotated classes
		myServices.add(QuestionController.class);
		myServices.add(UserController.class);
		myServices.add(QuestionSetController.class);
		myServices.add(MainController.class);

		// Add Htmleasy Providers
		myServices.addAll(HtmleasyProviders.getClasses());

		return myServices;
	}
}
