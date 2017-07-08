package com.tvunetworks.richardyao.another;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Oct 21, 2016 4:04:00 PM
 */
public class PersonFatBuilder extends PersonBuilder {

	@Override
	void buildHead() {
		System.out.println("Fat head");
	}

	@Override
	void buildBody() {
		System.out.println("Fat body");
	}

}
