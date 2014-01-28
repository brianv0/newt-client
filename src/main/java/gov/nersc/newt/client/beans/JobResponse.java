package gov.nersc.newt.client.beans;

/**
 *
 * @author tonyj
 */
public class JobResponse extends GenericResponse {
    private String jobid;

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    @Override
    public String toString() {
        return "Job{" + "status="+getStatus()+", error=" + getError() + ", jobid=" + jobid + '}';
    }
    
}
