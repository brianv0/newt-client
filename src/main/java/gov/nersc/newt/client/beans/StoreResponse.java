package gov.nersc.newt.client.beans;

/**
 *
 * @author bvan
 */
public class StoreResponse {
    private String status;
    private String output;
    private String error;

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

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "Store{" + "status=" + status + ", error=" + error + ", output=" + output + '}';
    }
    
}
