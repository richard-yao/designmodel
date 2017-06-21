package com.tvunetworks.richardyao.model;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Oct 17, 2016 3:25:54 PM
 */
public class PrintSomethingImpl implements PrintSomething {

	@Override
	public void printOut() {
		System.out.println("print out");
	}

	@Override
	public void printIn() {
		System.out.println("print in");
	}

}
