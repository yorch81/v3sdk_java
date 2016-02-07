package net.yorch;

import junit.framework.TestCase;

import org.json.JSONObject;

public class V3SDKTest extends TestCase {
	
	public V3SDKTest() {
		V3SDK.getInstance("https://v3-yorch.rhcloud.com/", "lYltuNtYYbYRFC7QWwHn9b5aH2UJMk1234567890");
    }
    
    public void test_findObject() {
    	V3SDK v3 = V3SDK.getInstance();
    	
    	JSONObject json = new JSONObject();
        json.put("field1", "value");
        json.put("field2", 666);
        
        json = v3.newObject("demo", json);
        
        String id = V3SDK.getId(json);
        
        json = v3.findObject("demo", id);
        
        String findId = V3SDK.getId(json);
        
        assertEquals(id, findId);
    }
    
    public void test_query() {
    	V3SDK v3 = V3SDK.getInstance();
    	
    	JSONObject json = new JSONObject();
        json.put("field1", "value");
        json.put("field2", 666);
                
        v3.newObject("demo", json);
        
        JSONObject result = v3.query("demo", json);
                
    	assertTrue(result.length() > 0);
    }
    
    public void test_newObject() {
    	V3SDK v3 = V3SDK.getInstance();
    	
    	JSONObject json = new JSONObject();
        json.put("field1", "value");
        json.put("field2", 666);
        
        json = v3.newObject("demo", json);
        
    	assertEquals(json.get("field2"), 666);
    }
    
    public void test_updateObject() {
    	V3SDK v3 = V3SDK.getInstance();
    	
    	JSONObject json = new JSONObject();
        json.put("field1", "value");
        json.put("field2", 666);
        
        json = v3.newObject("demo", json);
        String id = V3SDK.getId(json);
        
        json = new JSONObject();
        json.put("field2", 777);
        
        v3.updateObject("demo", id, json);
        
        json = v3.findObject("demo", id);
        
        int result = json.getInt("field2");
        
        assertEquals(777, result);
    }
    
    public void test_deleteObject() {
    	V3SDK v3 = V3SDK.getInstance();
    	
    	JSONObject json = new JSONObject();
        json.put("field1", "value");
        json.put("field2", 666);
        
        json = v3.newObject("demo", json);
        
        String id = V3SDK.getId(json);
        
        boolean result = v3.deleteObject("demo", id);
        
        assertTrue(result);
    }
    
}
