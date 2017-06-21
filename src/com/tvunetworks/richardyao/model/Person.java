package com.tvunetworks.richardyao.model;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Oct 17, 2016 2:59:22 PM
 */
public class Person {
	
	private String name;
	
	public Person(){
		
	}
	
	public Person(String name) {
		this.name = name;
	}
	
	public void show() {
		System.out.println("My name is "+name);
	}

}
