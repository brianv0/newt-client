
package gov.nersc.newt.client.resources;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bvan
 */
public class ResourceUtils {
    public static <T> T ensureOk(Response r, Class<T> type){
        if(r.getStatus() == 200){
            return r.readEntity( type );
        }
        throw new WebApplicationException(r);
    }
    
    public static <T> T ensureOk(Response r, GenericType<T> type){
        if(r.getStatus() == 200){
            return r.readEntity( type );
        }
        throw new WebApplicationException(r);
    }
}
