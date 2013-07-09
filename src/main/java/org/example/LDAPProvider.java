package org.example;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.example.models.LDAPUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LDAPProvider {
	
	private static Logger log = LoggerFactory.getLogger(LDAPProvider.class);
	
	public static LDAPUser getUserInfo(String crsid){
		LDAPUser result = new LDAPUser();
		
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://ldap.lookup.cam.ac.uk:389");

		Attributes a = null;
        try{
	        DirContext ctx = new InitialDirContext(env);
	        SearchControls controls = new SearchControls();
	        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
	        SearchResult searchResult = ctx.search("ou=people,o=University of Cambridge,dc=cam,dc=ac,dc=uk", "(uid="+crsid+")", controls).next();
	        a = searchResult.getAttributes();
		}catch(Exception e){
        	log.error(e.getMessage());
        	return null;
        }
       
        try {
        	log.debug("Trying to obtain data for user " + crsid);
	        result.setId(crsid);
	        log.debug("Trying to obtain displayName for user " + crsid);
	        result.setDisplayName(a.get("displayName").get().toString());
	        log.debug("Trying to obtain email address for user " + crsid);
	        result.setEmail(a.get("mail").get().toString());
	        log.debug("Trying to obtain Institutions for user " + crsid);
	        result.setInstitutions(toList(a.get("ou")));
	        log.debug("Trying to obtain roles for user " + crsid);
	        result.setRoles(toList(a.get("title")));
	        log.debug("Trying to obtain misAffiliation for user " + crsid);
	        try{
	        	if(a.get("misAffiliation").get().toString().equals("staff")) {
	        		result.setStudent(false);
	        	}
	        	log.debug("Successfully got misAffiliation");
	        } catch(NullPointerException e){
	        	log.warn("Couldn't get misAffiliation! Probably returned null. User set to role student by default.");
	        }
	        
	        log.debug("Trying to get the image for user" + crsid);
	        result.setImage(a.get("jpegPhoto").get().toString());
	        System.out.println(a.get("jpegPhoto"));
	        System.out.println(a.get("jpegPhoto").get());
	        System.out.println(a.get("jpegPhoto").get().toString());
        } catch (NamingException e) {
			log.error(e.getMessage());
			return result;
		}
        log.debug("Returning user info for " + crsid);
        return result;
	}
	
	private static List<String> toList(Attribute a) throws NamingException {
		log.debug("toList() called with attribute: " + a);
		if(a == null){
			log.debug("Attribute is null. Returning empty list.");
			return new ArrayList<String>();
		}
			
		NamingEnumeration enumResults = a.getAll();
		List<String> listResults = new ArrayList<String>();
		while(enumResults.hasMore()){
			listResults.add(enumResults.next().toString());
		}
		 
		log.debug("Successfully returning list of results (probably)");
		return listResults;
	}
}
