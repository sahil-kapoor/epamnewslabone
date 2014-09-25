package by.epam.news.util;

import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.Calendar;
import java.util.Date;  
  
import org.apache.commons.beanutils.Converter;  
  
public class DateConvert implements Converter {  
      
    private final static String pattern = "mm/dd/yyyy";  
    
    @Override
    public Object convert(@SuppressWarnings("rawtypes") Class type, Object value) {   
          
        if(value == null){  
            return (null);  
           
        }  
        
        if (value.getClass() == java.sql.Date.class){
        	Calendar calendar = Calendar.getInstance();
    		calendar.setTime((java.sql.Date)value);
    		return calendar.getTime();
        }
          
        Date dateObj = null;  
        if(value.getClass() == java.lang.String.class){   
            try{             	
                return new SimpleDateFormat(pattern).parse((String)value);
            }catch(ParseException pe){  
            	pe.printStackTrace();
                return (null);  
            }  
        }  
          
        return dateObj;  
    }  
  
}  