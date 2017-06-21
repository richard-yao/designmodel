package com.tvunetworks.richardyao.model;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Oct 17, 2016 3:03:30 PM
 */
public class Finery extends Person {
	
	Person component;
	
	public void decorate(Person component) {
		this.component = component;
	}

	@Override
	public void show() {
		if(component != null) {
			component.show();
		}
	}
}
