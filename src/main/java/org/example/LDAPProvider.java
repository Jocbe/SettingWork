package org.example;

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
		//env.put(Context.SECURITY_AUTHENTICATION, "simple");
		//env.put(Context.SECURITY_CREDENTIALS, "");
		//env.put(Context.SECURITY_PRINCIPAL, "");

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
        result.setId(crsid);
        result.setDisplayName(a.get("displayName").get().toString());
        result.setEmail(a.get("mail").get().toString());
        //result.setInstitutions(toArray(a.get("ou")));
        //result.setRoles(toArray(a.get("title")));
        if(a.get("misAffiliation").get().toString().equals("staff")) {
        	result.setStudent(false);
        }
        } catch (NamingException e) {
			log.error(e.getMessage());
			return null;
		}
        return result;
	}
	
	private static String[] toArray(Attribute a) throws NamingException {
		NamingEnumeration enumResults = a.getAll();
		List<String> listResults = new LinkedList<String>();
		while(enumResults.hasMore()){
			listResults.add(enumResults.next().toString());
		}
		return (String[]) listResults.toArray();
	}
}
