package org.example.controllers;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.example.LDAPProvider;
import org.example.models.LDAPUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.googlecode.htmleasy.ViewWith;

@Path("/ldap")
public class LDAPController {
	
	private static Logger log = LoggerFactory.getLogger(LDAPController.class); 
	
	@GET @Path("/{uID}")
	@ViewWith("/soy/view.ldap")
	public Map viewLDAP(@PathParam("uID") String uID) {
		LDAPUser result = LDAPProvider.getUserInfo(uID);
		//System.out.println("######################  " + result.getId());
		return ImmutableMap.of("userID", result.getId(), "name", result.getDisplayName(), "email", result.getEmail());
	}
}
