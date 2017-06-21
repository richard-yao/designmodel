package com.tvunetworks.richardyao.another;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Oct 21, 2016 4:04:52 PM
 */
public class PersonDirector {
	
	private PersonBuilder pb;
	
	public PersonDirector(PersonBuilder pb) {
		this.pb = pb;
	}
	
	public void createPerson() {
		pb.buildHead();
		pb.buildBody();
	}

}
