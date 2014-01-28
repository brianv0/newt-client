
package gov.nersc.newt.client.beans;

/**
 *
 * @author bvan
 */
public class GenericResponse {
    private String status;
    private String error;
    public GenericResponse(){}
    
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

}
