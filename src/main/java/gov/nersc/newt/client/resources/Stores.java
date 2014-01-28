
package gov.nersc.newt.client.resources;

import gov.nersc.newt.client.beans.OutputResponse;
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
        
    public OutputResponse createStore(String storeName){
        return target.path( storeName ).request().post( null, OutputResponse.class );
    }
    
    public OutputResponse deleteStore(String storeName){
        return target.path( storeName ).request().delete( OutputResponse.class );
    }
}
