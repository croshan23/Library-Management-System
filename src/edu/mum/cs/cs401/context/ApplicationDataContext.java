package edu.mum.cs.cs401.context;

import java.util.HashMap;
import java.util.Map;

public class ApplicationDataContext {

	private ApplicationDataContext(){
	}
	
	private static ApplicationDataContext context = new ApplicationDataContext();
	
	public static ApplicationDataContext getInstance() {
		return context;
	}
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	public void put(String key, Object object) {
		map.put(key, object);
	}
	
	public Object get(String key) {
		return map.get(key);
	}
}
