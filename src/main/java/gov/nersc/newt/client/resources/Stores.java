
package gov.nersc.newt.client.resources;

import gov.nersc.newt.client.beans.StoreResponse;
import java.util.List;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * A client resource representing the "store" resource.
 * @author bvan
 */
public class Stores {
    WebTarget target;
    public Stores(WebTarget baseTarget){
        this.target = baseTarget.path( "store");
    }
    
    public List<String> getStores(){
        return target.request().get( new GenericType<List<String>>(){} );
    }
        
    public StoreResponse createStore(String storeName){
        return target.path( storeName ).request().post( null, StoreResponse.class );
    }
    
    public StoreResponse deleteStore(String storeName){
        return target.path( storeName ).request().delete( StoreResponse.class );
    }
}
