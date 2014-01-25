
package gov.nersc.newt.client.resources;

import gov.nersc.newt.client.beans.JobResponse;
import gov.nersc.newt.client.beans.QueueStatus;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * The client resource representing the queue resource.
 * @author bvan
 */
public class Queue {
    WebTarget target;
    
    public Queue(WebTarget baseTarget, String machine){
        this.target = baseTarget.path( "queue" ).path( machine );
    }
    
    public JobResponse submitJobScript(String jobScript) {
        Form form = new Form("jobscript", jobScript);
        return target.request().post( 
                Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE), JobResponse.class);
    }
    
    public JobResponse deleteJob(String jobID) {
        return target.path( jobID ).request().delete( JobResponse.class );
    }

    public QueueStatus queryJob(String id) {
        return target.path(id).request().get( QueueStatus.class );
    }
    
    public List<QueueStatus> queryAllJobs() {
        return target.request()
                .get( new GenericType<List<QueueStatus>>() {} );
    }
    
    public List<QueueStatus> queryJobs(String username) {
        return target.queryParam( "user", username ).request()
                .get( new GenericType<List<QueueStatus>>() {} );
    }
}
