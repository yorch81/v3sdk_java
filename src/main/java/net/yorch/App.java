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
        V3SDK v3 = V3SDK.getInstance("https://v3-yorch.rhcloud.com/", "lYltuNtYYbYRFC7QWwHn9b5aH2UJMk1234567890");
                
        JSONObject json = new JSONObject();
        json.put("field1", "myvalue");
        json.put("field2", 666);
        
        JSONObject result = v3.newObject("demo", json);
        
        String id = V3SDK.getId(result);
        
        json = new JSONObject();
        json.put("field3", "myvalue3");
        json.put("field2", 777);
        
        v3.updateObject("demo", id, json);
        
        result = v3.findObject("demo", id);
        
        System.out.println(result.toString());
        
        result = v3.query("demo", json);
        
        Iterator<String> iter = result.keys();
        
        while (iter.hasNext()) {
        	String key = iter.next();
            
        	try {
        		JSONObject value = (JSONObject) result.get(key);
            	
        		System.out.println(value.toString());
            } catch (JSONException e) {
            }
        }
        
        v3.deleteObject("demo", id);
    }
}
