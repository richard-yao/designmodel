package com.tvunetworks.richardyao.visitor;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 28, 2016 4:46:42 PM
 */
public class ConcreteElm2 extends Element {

	@Override
	void accept(Visitor visitor) {
		visitor.visitConcreteElm2(this);
	}
	
	public void printOutMethod() {
		System.out.println("I have a name!!");
	}

}
