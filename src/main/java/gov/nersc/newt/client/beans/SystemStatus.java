package gov.nersc.newt.client.beans;

/**
 *
 * @author tonyj
 */
public class SystemStatus {
    private String status;
    private String system;

    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    @Override
    public String toString() {
        return "SystemStatus{" + "status=" + status + ", system=" + system + '}';
    }
    
}
