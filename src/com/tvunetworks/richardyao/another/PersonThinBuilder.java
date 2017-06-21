package com.tvunetworks.richardyao.another;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Oct 21, 2016 4:03:09 PM
 */
public class PersonThinBuilder extends PersonBuilder {

	@Override
	void buildHead() {
		System.out.println("Thin head");
	}

	@Override
	void buildBody() {
		System.out.println("Thin body");
	}

}
