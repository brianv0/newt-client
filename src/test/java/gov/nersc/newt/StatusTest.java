
package gov.nersc.newt;

import java.io.IOException;
import junit.framework.TestCase;

/**
 *
 * @author bvan
 */
public class StatusTest extends TestCase {
    NewtClient newt = Utils.getClient();
    
    public StatusTest(){ }
    
    public void testPrintMOTD() throws IOException{
        System.out.println( newt.messageOfTheDay() );
    }
    
    public void testStatus(){
        assertTrue("Should have found more than 4 systems", newt.status().size() > 4);
        try {
            newt.status("cori");
        } catch (Exception e){
            fail("Should have found status for cori");
        }
    }
}
