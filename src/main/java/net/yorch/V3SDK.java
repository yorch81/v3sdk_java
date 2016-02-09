package net.yorch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * V3SDK<br>
 * 
 * V3SDK V3ctor WareHouse JAVA SDK<br><br>
 * 
 * Copyright 2016 Jorge Alberto Ponce Turrubiates
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @version    1.0.0, 2016-06-02
 * @author     <a href="mailto:the.yorch@gmail.com">Jorge Alberto Ponce Turrubiates</a>
 */
public class V3SDK {

	/**
	 * Instance Handler
	 */
	private static V3SDK INSTANCE = null;
	
	/**
	 * V3ctor WareHouse URL
	 */
	private String _url = "";
	
	/**
	 * V3ctor WareHouse Key
	 */
	private String _key = "";
	
	/**
	 * Private Constructor
	 * 
	 * @param url String V3ctor WareHouse URL
	 * @param key String V3ctor WareHouse Key
	 */
	private V3SDK (String url, String key) {
		this._url = url;
		this._key = key;
	}
	
	/**
	 * Singleton Implementation
	 * 
	 * @param url String V3ctor WareHouse URL
	 * @param key String V3ctor WareHouse Key
	 * @return V3SDK
	 */
	public static V3SDK getInstance (String url, String key) {
		if (INSTANCE == null)
			INSTANCE = new V3SDK(url, key);
		
		return INSTANCE;
	}

	/**
	 * Get Instance
	 * 
	 * @return V3SDK
	 */
	public static V3SDK getInstance () {
		if (INSTANCE == null)
			INSTANCE = new V3SDK("", "");
		
		return INSTANCE;
	}
	
	/**
	 * Get URL
	 * 
	 * @return String V3ctor WareHouse URL
	 */
	public String getURL() {
		return this._url;
	}
	
	/**
	 * Get Key
	 * 
	 * @return String V3ctor WareHouse Key
	 */
	public String getKey() {
		return this._key;
	}
	
	/**
	 * Gets the Id of Object
	 * 
	 * @param v3Object V3WH Object
	 * @return String
	 */
	public static String getId(JSONObject v3Object) {
		String id = "";
		
		try{
			JSONObject r = (JSONObject) v3Object.get("_id");
	        
	        id = r.getString("$id");
		}
		catch(Exception e){
			id = "";
		}
		
		return id;
	}
	
	/**
	 * Finds Object by Id
	 * 
	 * @param entity String Entity Name
	 * @param _id    String Identifier
	 * @return JSONObject
	 */
	public JSONObject findObject(String entity, String _id) {
		HttpClient httpclient = HttpClientBuilder.create().build();
        
        JSONObject retValue = new JSONObject();
        
        String url = this._url + entity + "/" + _id + "?auth=" + this._key;
       
        try{
            HttpGet httpget = new HttpGet(url);
            HttpResponse httpResponse = httpclient.execute(httpget);

            BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            int status = httpResponse.getStatusLine().getStatusCode();
            StringBuffer response = new StringBuffer();
            
            if (status == 200) {
            	String line = "";
           
                while ((line = rd.readLine()) != null)
                    response.append(line);
            }
            
            retValue =  new JSONObject(response.toString());
        }
        catch (IOException|JSONException e){
            retValue = new JSONObject();
        }
        
        httpclient = null;
        
		return retValue;
	}
	
	/**
	 * Execute Query with jsonQuery Pattern
	 * 
	 * @param entity    String     Entity Name
	 * @param jsonQuery JSONObject Query Pattern
	 * @return JSONObject
	 */
	public JSONObject query(String entity, JSONObject jsonQuery) {
		HttpClient httpclient = HttpClientBuilder.create().build();
        
        JSONObject retValue = new JSONObject();
        
        String url = this._url + "query/" + entity + "?auth=" + this._key;
       
        try{
        	HttpPost httppost = new HttpPost(url);
        	StringEntity entJson = new StringEntity(jsonQuery.toString());
        	
        	httppost.setEntity(entJson);
        	
            HttpResponse httpResponse = httpclient.execute(httppost);

            BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            int status = httpResponse.getStatusLine().getStatusCode();
            StringBuffer response = new StringBuffer();
            
            if (status == 200) {
            	String line = "";
           
                while ((line = rd.readLine()) != null)
                    response.append(line);
            }
                        
            retValue =  new JSONObject(response.toString());
        }
        catch (IOException|JSONException e){
            retValue = new JSONObject();
        }
        
        httpclient = null;
        
		return retValue;
	}
	
	/**
	 * Insert New JSON Object
	 * 
	 * @param entity   String      Entity Name
	 * @param v3Object JSONObject  JSON Object
	 * @return JSONObject
	 */
	public JSONObject newObject(String entity, JSONObject v3Object) {
		HttpClient httpclient = HttpClientBuilder.create().build();
               
        JSONObject retValue = new JSONObject();
        
        String url = this._url + entity + "?auth=" + this._key;
       
        // Remove _id
        v3Object.remove("_id");
        
        try{
        	HttpPost httppost = new HttpPost(url);
        	StringEntity entJson = new StringEntity(v3Object.toString());
        	
        	httppost.setEntity(entJson);
        	
            HttpResponse httpResponse = httpclient.execute(httppost);

            BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            int status = httpResponse.getStatusLine().getStatusCode();
            StringBuffer response = new StringBuffer();
            
            if (status == 200) {
            	String line = "";
           
                while ((line = rd.readLine()) != null)
                    response.append(line);
            }
            
            retValue =  new JSONObject(response.toString());
        }
        catch (IOException|JSONException e){
            retValue = new JSONObject();
        }
        
        httpclient = null;
        
		return retValue;
	}
	
	/**
	 * Updates an Object by Id
	 * 
	 * @param entity   String Entity Name
	 * @param _id      String Identifier
	 * @param v3Object JSONObject  JSON Object
	 * @return boolean
	 */
	public boolean updateObject(String entity, String _id, JSONObject v3Object) {
		HttpClient httpclient = HttpClientBuilder.create().build();
        
        boolean retValue = false;
        
        String url = this._url + entity + "/" + _id + "?auth=" + this._key;
        String ok = "{\"msg\":\"OK\"}";
        
        // Remove _id
        v3Object.remove("_id");
        
        try{
        	HttpPut httpput = new HttpPut(url);
        	StringEntity entJson = new StringEntity(v3Object.toString());
        	httpput.addHeader("Content-Type", "application/x-www-form-urlencoded");
        	httpput.addHeader("Accept", "application/json");
        	httpput.setEntity(entJson);
        	
            HttpResponse httpResponse = httpclient.execute(httpput);

            BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            int status = httpResponse.getStatusLine().getStatusCode();
            StringBuffer response = new StringBuffer();
            
            if (status == 200) {
            	String line = "";
           
                while ((line = rd.readLine()) != null)
                    response.append(line);
            }
            
            if (response.toString().equals(ok))
            	retValue =  true;
        }
        catch (IOException|JSONException e){
            retValue = false;
        }
        
        httpclient = null;
		
		return retValue;
	}
	
	/**
	 * Deletes Object by Id
	 * 
	 * @param entity String Entity Name
	 * @param _id    String Identifier
	 * @return boolean
	 */
	public boolean deleteObject(String entity, String _id) {
		HttpClient httpclient = HttpClientBuilder.create().build();
        
        boolean retValue = false;
        
        String url = this._url + entity + "/" + _id + "?auth=" + this._key;
        String ok = "{\"msg\":\"OK\"}";
        
        try{
        	HttpDelete httpdel = new HttpDelete(url);
        	
            HttpResponse httpResponse = httpclient.execute(httpdel);

            BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            int status = httpResponse.getStatusLine().getStatusCode();
            StringBuffer response = new StringBuffer();
            
            if (status == 200) {
            	String line = "";
           
                while ((line = rd.readLine()) != null)
                    response.append(line);
            }
            
            if (response.toString().equals(ok))
            	retValue =  true;
        }
        catch (IOException|JSONException e){
            retValue = false;
        }
        
        httpclient = null;
		
		return retValue;
	}
	
	/**
	 * Not permit clone the instance
	 */
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
