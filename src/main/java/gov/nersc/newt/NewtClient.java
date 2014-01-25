package gov.nersc.newt;

import gov.nersc.newt.client.beans.JobResponse;
import gov.nersc.newt.client.beans.QueueStatus;
import gov.nersc.newt.client.beans.LoginStatus;
import gov.nersc.newt.client.beans.DirectoryEntry;
import gov.nersc.newt.client.beans.SystemStatus;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.MalformedURLException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import gov.nersc.newt.client.beans.StoreResponse;
import gov.nersc.newt.client.resources.Account;
import gov.nersc.newt.client.resources.Files;
import gov.nersc.newt.client.resources.Queue;
import gov.nersc.newt.client.resources.Status;
import gov.nersc.newt.client.resources.Stores;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 * NewtClient is a combination of all the resources available for NEWT, for convenience.
 * 
 * It is configured to have a default machine, but convenience methods exist for querying 
 * other machines.
 * 
 * @author tonyj, bvan
 */
public class NewtClient {
    static final Logger logger = Logger.getLogger( NewtClient.class.getCanonicalName() );
    private Client client;
    private WebTarget baseTarget;
    private Account auth;
    private Status status;
    private Files files;
    private Queue jobs;
    private Stores stores;
    private String username;
    private String machine;
    
    static {
        logger.setLevel( Level.WARNING ); // Note: INFO or higher will show password in logger.
    }

    public NewtClient(){
        try { init( "https://newt.nersc.gov/newt/", "hopper" ); } catch(MalformedURLException e){}
    }

    public NewtClient(String baseURL) throws MalformedURLException{
        init( baseURL, "hopper" );
    }

    public NewtClient(String baseURL, String machine) throws MalformedURLException {
        init( baseURL, machine );
    }

    private void init(String baseURL, String machine) throws MalformedURLException {
        client = ClientBuilder.newBuilder()
                .register( new JacksonJsonProvider( new ObjectMapper() ) )
                .register( MultiPartFeature.class )
                .register( new LoggingFilter( logger, true ) )
                .build();
        baseTarget = client.target( baseURL );
        CookieHandler.setDefault( new CookieManager() );
        auth = new Account( baseTarget );
        status = new Status( baseTarget );
        this.machine = machine;
        files = new Files( baseTarget, machine );
        jobs = new Queue( baseTarget, machine );
        stores = new Stores(baseTarget);
    }

    public String getMachine(){
        return this.machine;
    }

    public void setMachine(String machine){
        this.machine = machine;
        files = new Files( baseTarget, machine );
        jobs = new Queue( baseTarget, machine );
    }

    public LoginStatus login(String username, String password){
        this.username = username;
        return auth.login( username, password );
    }

    public LoginStatus loginStatus(){
        return auth.loginStatus();
    }
    
    public LoginStatus logout(){
        return auth.logout();
    }

    public String messageOfTheDay(){
        return status.messageOfTheDay();
    }

    public List<SystemStatus> status(){
        return status.status();
    }

    public SystemStatus status(String system) throws IOException{
        return status.status( system );
    }

    public List<DirectoryEntry> ls(String path){
        return files.ls( path );
    }

    public String getFile(String path){
        return files.getFile( path );
    }

    public String putFile(String path, File file){
        return files.putFile( path, file );
    }

    public String putFileFromString(String path, String content){
        return files.putFileFromString( path, content );
    }

    public JobResponse submitJobScript(String jobScript){
        return jobs.submitJobScript( jobScript );
    }

    public QueueStatus queryJob(String id){
        return jobs.queryJob( id );
    }

    public List<QueueStatus> queryAllJobs(){
        return jobs.queryAllJobs();
    }

    public List<QueueStatus> queryJobs(){
        return jobs.queryJobs( username );
    }
    
    public List<String> getStores(){
        return stores.getStores();
    }
    
    public StoreResponse createStore(String storeName){
        return stores.createStore( storeName );
    }
    
    public StoreResponse deleteStore(String storeName){
        return stores.deleteStore( storeName );
    }

    public List<DirectoryEntry> ls(String machine, String path){
        return new Files( baseTarget, machine ).ls( path );
    }

    public String getFile(String machine, String path){
        return new Files( baseTarget, machine ).getFile( path );
    }

    public String putFile(String machine, String path, File file){
        return new Files( baseTarget, machine ).putFile( path, file );
    }

    public String putFile(String machine, String path, String filepath){
        return new Files( baseTarget, machine ).putFile( path, new File(filepath) );
    }

    public String putFileFromString(String machine, String path, String content){
        return new Files( baseTarget, machine ).putFileFromString( path, content );
    }

    public JobResponse submitJobScript(String machine, String jobScript){
        return new Queue( baseTarget, machine ).submitJobScript( jobScript );
    }

    public QueueStatus queryJob(String machine, String id){
        return new Queue( baseTarget, machine ).queryJob( id );
    }

    public List<QueueStatus> queryAllJobs(String machine){
        return new Queue( baseTarget, machine ).queryAllJobs();
    }

    public List<QueueStatus> queryJobs(String machine, String username){
        return new Queue( baseTarget, machine ).queryJobs( username );
    }

    /**
     * Provide the command line arguments as follows:
     * [methodname] [param]*
     * @param argv
     * @throws Throwable 
     */
    public static void main(String[] argv) throws Throwable {
        // Example:
        // Command line: newtc getFile /etc/motd
        // -> argv = new String[]{"getFile", "/etc/motd"};
        // --> nc.getFile("/etc/motd")
        
        String username = System.getProperty( "nersc.newt.user" );
        String password = System.getProperty( "nersc.newt.password" );

        NewtClient nc = new NewtClient();
        System.out.println(nc.login( username, password ));
        
        if(argv.length > 0){
            String methodName = argv[0];
            Class<?> types[] = new Class<?>[argv.length - 1];
            String args[] = new String[argv.length - 1];
            for(int i = 1; i < argv.length; i++){
                types[i-1] = String.class;
                args[i-1] = argv[i];
            }
            Object o = nc.getClass().getDeclaredMethod( methodName, types).invoke( nc, args);
            System.out.println(o.toString());
        }
        System.out.println( nc.logout() );
    }
    
}
