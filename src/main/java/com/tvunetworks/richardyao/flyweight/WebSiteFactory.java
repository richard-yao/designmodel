package com.tvunetworks.richardyao.flyweight;

import java.util.HashMap;
import java.util.Map;


/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 22, 2016 3:04:53 PM
 */
public class WebSiteFactory {
	
	
	private Map<String, ConcreteWebsite> mapAll = new HashMap<String, ConcreteWebsite>();
	
	public WebSite getWebSiteCategory(String key) {
		if(!mapAll.containsKey(key)) {
			mapAll.put(key, new ConcreteWebsite(key));
		}
		return mapAll.get(key);
	}
	
	public int webSiteCount() {
		return mapAll.size();
	}
}
