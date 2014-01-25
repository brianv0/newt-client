package gov.nersc.newt;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;


/**
 *
 * @author bvan
 */
public class FileTest extends TestCase {

    NewtClient newt = Utils.getClient();

    public FileTest(){
        newt = Utils.getClient();
    }
    
    public void testFileUpload() throws UnsupportedEncodingException, URISyntaxException{
        String output;
        
        String test = "This is a string\nThis is only a string";
        newt.putFileFromString("hopper", "/tmp/newtstringtest", test);
        output = newt.getFile( "hopper", "/tmp/newtstringtest");
        assertEquals("Output mismatch", output, test);
        
        URL url = FileTest.class.getResource( "/test.c");
        newt.putFile( "hopper", "/tmp", new File(url.toURI()) );
        output = newt.getFile( "hopper", "/tmp/test.c");
        System.out.print( output.getBytes().length);
    }

}
