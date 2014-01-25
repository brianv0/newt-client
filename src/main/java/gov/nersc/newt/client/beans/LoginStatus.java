package gov.nersc.newt.client.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tonyj
 */
@XmlRootElement
public class LoginStatus {
    private String username;
    private int sessionLifetime;
    private boolean loggedIn;
    private String newtSessionID;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @JsonProperty("session_lifetime")
    public int getSessionLifetime() {
        return sessionLifetime;
    }

    public void setSessionLifetime(int sessionLifetime) {
        this.sessionLifetime = sessionLifetime;
    }

    @JsonProperty("auth")
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    @JsonProperty("newt_sessionid")
    public String getNewtSessionID(){
        return this.newtSessionID;
    }
    
    public void setNewtSessionID(String id){
        this.newtSessionID = id;
    }

    @Override
    public String toString() {
        return "LoginStatus{" + "username=" + username + ", sessionLifetime=" + sessionLifetime 
                + ", loggedIn=" + loggedIn + '}';
    }
}
