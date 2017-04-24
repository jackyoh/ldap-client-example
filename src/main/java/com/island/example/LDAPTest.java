package com.island.example;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;


public class LDAPTest {
    public static void main(String[] args) throws NamingException {
    	  String ldapURL = "ldap://192.168.1.223";
        String dn = "uid=user2,ou=Users,dc=openstack,dc=org";
        String bindUser = "user2";
        String bindPassword = "123456";
        String searchBase = "ou=Users,dc=openstack,dc=org";
        String searchFilter = "uid=" + bindUser;
        
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapURL);
        env.put(Context.SECURITY_PRINCIPAL, dn);
        env.put(Context.SECURITY_CREDENTIALS, bindPassword);

        InitialDirContext context = new InitialDirContext(env);
        
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<?> results = context.search(searchBase, searchFilter, controls);

        while(results.hasMore()){
          	SearchResult result = (SearchResult) results.next();
        	   System.out.println(result.getNameInNamespace());
          	NamingEnumeration<?> attributes = result.getAttributes().getAll();
          	while(attributes.hasMore()){
          		System.out.println(attributes.next());
          	    }
          }
       context.close();
    }
}
