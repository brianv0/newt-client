package gov.nersc.newt.client.resources;

import gov.nersc.newt.client.beans.DirectoryEntry;
import gov.nersc.newt.client.beans.OutputResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.glassfish.jersey.media.multipart.file.StreamDataBodyPart;

/**
 * The logical client resource representing the files resource
 * @author bvan
 */
public class Files {
    WebTarget target;
    Jobs jobTarget;

    public Files(WebTarget baseTarget, String machine){
        this.target = baseTarget.path( "file" ).path( machine );
        this.jobTarget = new Jobs(baseTarget, machine);
    }

    public List<DirectoryEntry> ls(String path){
        return target.path( path ).request().get( new GenericType<List<DirectoryEntry>>() {} );
    }

    /**
     * Retrieves a file as a String
     * @param path
     * @return 
     */
    public String getFile(String path){
        return target.path( path ).queryParam( "view", "read" ).request().get( String.class );
    }

    /**
     * Uploads a local file
     * @param path Directory where the file will be places
     * @param file The local file to use. The name will be used
     * @return Status of the request
     */
    public String putFile(String path, File file){
        final FileDataBodyPart filePart = new FileDataBodyPart( "file", file );
        final FormDataMultiPart multipart = (FormDataMultiPart) new FormDataMultiPart()
                .bodyPart( filePart );
        return target.path( path ).request()
                .post( Entity.entity( multipart, multipart.getMediaType() ), String.class );
    }

    /**
     * Upload a file from a string object
     * @param path Full path of the file
     * @param content The content of the file
     * @return Status of the request
     */
    public String putFileFromString(String path, String content){
        Path targetPath = Paths.get( path );
        final StreamDataBodyPart filePart;
        try {
            filePart = new StreamDataBodyPart(
                    targetPath.getFileName().toString(), 
                    new ByteArrayInputStream( content.getBytes( "UTF-8" ) ) );
            filePart.setName( "file" );
        } catch(UnsupportedEncodingException ex) {
            throw new RuntimeException( "Bad input for string content", ex );
        }

        final FormDataMultiPart multipart = (FormDataMultiPart) new FormDataMultiPart()
                .bodyPart( filePart );

        return target.path( targetPath.getParent().toString() ).request().
                post( Entity.entity( multipart, multipart.getMediaType() ), String.class );
    }
    
    /**
     * Make directories and all parents
     * @param path
     * @return Response and possible error
     */
    public OutputResponse mkdir(String path){
        String command = "mkdir " + path;
        return jobTarget.runCommand( command );
    }
    
    /**
     * Make directories and all parents
     * @param path
     * @return Response and possible error
     */
    public OutputResponse mkdirs(String path){
        String command = "mkdir -p " + path;
        return jobTarget.runCommand( command);
    }
    
    /**
     * Move files or directories. By default this will not overwrite old files
     * @param oldPath
     * @param newPath
     * @return Response and possible error
     */
    public OutputResponse mv(String oldPath, String newPath){
        String command = String.format("mv -n %s %s", oldPath, newPath);
        return jobTarget.runCommand( command );
    }
    
}
