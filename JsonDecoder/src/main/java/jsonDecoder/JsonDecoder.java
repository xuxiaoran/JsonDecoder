package jsonDecoder;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
//author: Xiaoran Xu
import java.util.Set;

import org.json.*;

public class JsonDecoder {
		
		public JsonDecoder(String filename) throws FileNotFoundException {
			try {
				//change filename below
				
				ClassLoader cl = this.getClass().getClassLoader();
				updateFromJson(cl.getResourceAsStream(filename));
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
			}
		}
	
	    private HashMap<String, String> updateFromJsonHelper(JSONObject jsonConfig, String prefix) {
	        Set<String> keys = jsonConfig.keySet();
	        HashMap<String, String> temp = new HashMap<String, String>();

	        if (prefix != null && !prefix.equals("")) {
	            prefix += ".";
	        }

	        for (String key : keys) {
	            Object subObj = jsonConfig.get(key);

	            if (subObj instanceof JSONObject) {
	                JSONObject sub = (JSONObject) subObj;
	                HashMap<String, String> sublevel = updateFromJsonHelper(sub, key);
	                Set<String> subKeys = sublevel.keySet();
	                for(String subKey: subKeys) {
	                    temp.put(prefix+subKey, sublevel.get(subKey));
	                }
	            }
	            else {
	                temp.put(prefix+key, jsonConfig.get(key).toString());
	            }
	        }
	        
	        return temp;
	    }

	    public void updateFromJson(InputStream stream) throws FileNotFoundException {
	        JSONTokener jsonTokener = new JSONTokener(stream);
	        JSONObject jsonConfig = new JSONObject(jsonTokener);
	        HashMap<String, String> newConfigs = updateFromJsonHelper(jsonConfig, "");

	        newConfigs.forEach((k,v) -> System.out.println("key:" + k + " value: " + v));
	    }
}
