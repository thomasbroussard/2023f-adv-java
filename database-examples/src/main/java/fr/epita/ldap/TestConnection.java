package fr.epita.ldap;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.Hashtable;

public class TestConnection {

    public static void main(String[] args) throws NamingException {

        //connexion as admin in order to perform priviledged operations

        //Authentication
        Hashtable<String, String> ldapEnv = new Hashtable<String, String>(11);
        ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

        ldapEnv.put(Context.PROVIDER_URL,  "ldap://localhost:10389");
        ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");

        ldapEnv.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
        ldapEnv.put(Context.SECURITY_CREDENTIALS, "secret");
        DirContext ldapContext = new InitialDirContext(ldapEnv);


        //Search
        SearchControls searchCtls = new SearchControls();

        //Specify the attributes to return
        String[] returnedAtts ={"cn","sn"};
        searchCtls.setReturningAttributes(returnedAtts);

        //Specify the search scope
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        //specify the LDAP search filter
        String searchFilter = "(&(objectClass=inetOrgPerson))";

        //Specify the Base for the search
        String searchBase = "ou=students,ou=internationalPrograms, o=epita, dc=example,dc=com";
        //initialize counter to total the results
        int totalResults = 0;

        // Search for objects using the filter
        NamingEnumeration<SearchResult> answer = ldapContext.search(searchBase, searchFilter, searchCtls);
        answer.asIterator().forEachRemaining(s -> System.out.println(s.getName()));
        //cn=devipriya
        //cn=sebastian



        ldapContext.close();


        //connect as a user:
        String username = "sebastian";
        String password = "test";

        ldapEnv = new Hashtable<String, String>(11);
        ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

        ldapEnv.put(Context.PROVIDER_URL,  "ldap://localhost:10389");
        ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");

        ldapEnv.put(Context.SECURITY_PRINCIPAL, "cn=" + username+ ",ou=students,ou=internationalPrograms,o=epita,dc=example,dc=com");
        ldapEnv.put(Context.SECURITY_CREDENTIALS, password);
        new InitialDirContext(ldapEnv);






    }
}
