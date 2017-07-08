package com.tvunetworks.richardyao.flyweight;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 22, 2016 3:03:18 PM
 */
public class ConcreteWebsite extends WebSite {
	
	private String name;
	public ConcreteWebsite(String name) {
		this.name = name;
	}
	
	@Override
	public void printOut(Object obj) {
		System.out.println(obj.getClass().getName());
	}

	public void printOut() {
		System.out.println("print out the your: "+name);
	}

}
