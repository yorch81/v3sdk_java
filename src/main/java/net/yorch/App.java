package net.yorch;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        System.out.println( "Hello V3ctor WareHouse SDK !!!" );
        
        V3SDK v3 = V3SDK.getInstance("https://v3-yorch.rhcloud.com/", "lYltuNtYYbYRFC7QWwHn9b5aH2UJMk1234567890");
        
        /*JSONObject json = v3.findObject("demo", "56b68f3082c7d55154ecd61b");
                
        json = new JSONObject();
        json.put("field1", "myvalue");
        json.put("jorge", "value1");
        json.put("field", 666);
        
        v3.newObject("demo", json);
        v3.newObject("demo", json);
        v3.newObject("demo", json);
        v3.newObject("demo", json);
        
        v3.updateObject("demo", "56b68f2b82c7d55154ecd61a", json);
        
        v3.deleteObject("demo", "56b6876882c7d55154ecd613");
        
        json = v3.newObject("demo", json);*/
        
        JSONObject jsonQuery = new JSONObject();
        jsonQuery.put("field1", "myvalue");
        jsonQuery.put("jorge", "value1");
        jsonQuery.put("field", 666);
        
        jsonQuery = v3.query("demo", jsonQuery);
        
        Iterator<String> iter = jsonQuery.keys();
        
        while (iter.hasNext()) {
        	String key = iter.next();
            
        	try {
        		JSONObject value = (JSONObject) jsonQuery.get(key);
            	
        		System.out.println(value.toString());
            } catch (JSONException e) {
                // Something went wrong!
            }
        }
    }
}
