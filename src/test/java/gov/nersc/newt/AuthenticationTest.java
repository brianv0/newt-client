package gov.nersc.newt;

import java.io.IOException;
import junit.framework.TestCase;

/**
 *
 * @author bvan
 */
public class AuthenticationTest extends TestCase {
    NewtClient newt;
    String username;
    String password;

    public AuthenticationTest(){
        newt = new NewtClient();
        this.username = System.getProperty( "nersc.newt.user" );
        this.password = System.getProperty( "nersc.newt.password" );
    }

    public void testLogin() throws IOException{
        assertTrue( "Login didn't work", newt.login( username, password ).isLoggedIn() );
        assertTrue( "Should still be logged in", newt.loginStatus().isLoggedIn() );
    }

    public void testLogout(){
        assertFalse( "Logout didn't work", newt.logout().isLoggedIn() );
        assertFalse( "Should still be logged out", newt.loginStatus().isLoggedIn() );
    }

}
