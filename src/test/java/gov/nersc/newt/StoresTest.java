
package gov.nersc.newt;

import gov.nersc.newt.client.beans.StoreResponse;
import junit.framework.TestCase;

/**
 *
 * @author bvan
 */
public class StoresTest extends TestCase {
    
    NewtClient newt = Utils.getClient();
    String testStore = "newtTestStore";
    
    public StoresTest(){ }
    
    
    public void testCreateStore(){
        int storeCount = newt.getStores().size();
        StoreResponse resp = newt.createStore( "newtTestStore" );
        System.out.println(resp.toString());
        assertEquals(resp.getStatus(), "OK");
        
        assertEquals(storeCount, newt.getStores().size() - 1);
        
        resp = newt.deleteStore( "newtTestStore" );
        System.out.println(resp.toString());
        assertEquals(resp.getStatus(), "OK");
    }

}
