
package gov.nersc.newt.client.resources;

import gov.nersc.newt.client.beans.SystemStatus;
import java.util.List;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * A client resource representing status resources ("status" and "motd")
 * @author bvan
 */
public class Status {
    WebTarget target;
    
    public Status(WebTarget baseTarget){
        this.target = baseTarget.path( "status");
    }

    public String messageOfTheDay() {
        return target.path( "motd" ).request().get( String.class );
    }

    public List<SystemStatus> status() {
        return target.request().get(new GenericType<List<SystemStatus>>() {});
    }

    public SystemStatus status(String system) {
        return target.path(system).request().get(SystemStatus.class);
    }
}
