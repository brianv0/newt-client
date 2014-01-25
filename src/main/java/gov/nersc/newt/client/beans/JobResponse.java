package gov.nersc.newt.client.beans;

/**
 *
 * @author tonyj
 */
public class JobResponse {
    private String status;
    private String error;
    private String jobid;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    @Override
    public String toString() {
        return "Job{" + "status=" + status + ", error=" + error + ", jobid=" + jobid + '}';
    }
    
}
