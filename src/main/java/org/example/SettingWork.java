package org.example;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

import org.example.controllers.MainController;
import org.example.controllers.QuestionController;
import org.example.controllers.QuestionSetController;
import org.example.controllers.UserEditController;
import org.example.controllers.UserQuestionsViewController;
import org.example.controllers.UserSetsViewController;
import org.example.controllers.UserSummaryController;

import com.googlecode.htmleasy.HtmleasyProviders;

public class SettingWork extends Application {

	public Set<Class<?>> getClasses() {
		//SessionFactoryManager.getInstance();
		Set<Class<?>> myServices = new HashSet<Class<?>>();

		// Add my own JAX-RS annotated classes
		myServices.add(QuestionController.class);
		myServices.add(UserSetsViewController.class);
		myServices.add(QuestionSetController.class);
		myServices.add(MainController.class);
		myServices.add(UserSummaryController.class);
		myServices.add(UserQuestionsViewController.class);
		myServices.add(UserEditController.class);
		
		// Add Htmleasy Providers
		myServices.addAll(HtmleasyProviders.getClasses());

		return myServices;
	}
}
