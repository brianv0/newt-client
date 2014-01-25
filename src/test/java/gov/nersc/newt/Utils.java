
package gov.nersc.newt;

/**
 *
 * @author bvan
 */
public class Utils {

    /**
     * Retrieve a client based on declared java properties
     * @return 
     */
    public static NewtClient getClient(){
        String username = System.getProperty( "nersc.newt.user" );
        String password = System.getProperty( "nersc.newt.password" );
        NewtClient nc = new NewtClient();
        nc.login( username, password );
        return nc;
    }
}
