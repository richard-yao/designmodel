package com.tvunetworks.richardyao.visitor;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 28, 2016 4:46:25 PM
 */
public class ConcreteElm1 extends Element {

	@Override
	void accept(Visitor visitor) {
		visitor.visitConcreteElm1(this);
	}
	
	public void printOutCustomize() {
		System.out.println("what's your name??");
	}

}
