package gov.nersc.newt.client.beans;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author tonyj
 */
public class FileDateDeserializer extends JsonDeserializer<Date>
{
    @Override
    public Date deserialize(JsonParser jsonparser,
            DeserializationContext deserializationcontext) 
            throws IOException, JsonProcessingException {

        SimpleDateFormat format = new SimpleDateFormat("MMM dd HH:mm");
        String date = jsonparser.getText();
        try {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            c.setTime(format.parse(date));
            c.set(Calendar.YEAR,year);
            return c.getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

}