package gov.nersc.newt.client.beans;

/**
 *
 * @author bvan
 */
public class OutputResponse extends GenericResponse {
    private String output;
    
    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "Output{" + "status="+getStatus()+", error="+getError()+", output=" + output + '}';
    }
    
}
