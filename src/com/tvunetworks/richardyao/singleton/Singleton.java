package com.tvunetworks.richardyao.singleton;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 16, 2016 5:19:20 PM
 */
public class Singleton {
	
	private static class SingletonHander {
		private static final Singleton INSTANCE = new Singleton();
	}
	
	private Singleton() {
		
	}
	
	public static final Singleton getInstance() {
		return SingletonHander.INSTANCE;
	}

}
