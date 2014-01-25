package gov.nersc.newt.client.resources;

import gov.nersc.newt.client.beans.LoginStatus;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

/**
 * A client resource used for authentication and NIM account queries.
 * @author bvan
 */
public class Account {
    WebTarget target;

    public Account(WebTarget baseTarget){
        target = baseTarget;
    }

    /**
     * Posts authentication information. If authentication is successful, 
     * the session cookie is set. Returns JSON string with authentication status
     * @param username
     * @param password
     * @return login status
     */
    public LoginStatus login(String username, String password){
        Form form = new Form( "username", username ).param( "password", password );
        return target.path( "login" ).request().post(
               Entity.entity( form, MediaType.APPLICATION_FORM_URLENCODED_TYPE ), LoginStatus.class
        );
    }

    /**
     * Get current auth status, username and number of seconds in session
     * @return
     */
    public LoginStatus loginStatus(){
        return target.path( "login" ).request().get( LoginStatus.class );
    }

    /**
     * Log the user out and returns current authentication status
     * @return
     */
    public LoginStatus logout(){
        return target.path( "logout" ).request().get( LoginStatus.class );
    }
    
    /**
     * Get Account information from NIM
     * @param nimResourcePath
     * @return json string
     */
    public String query(String nimResourcePath){
        return target.path( "account" ).path( nimResourcePath ).request().get( String.class );
    }
}
