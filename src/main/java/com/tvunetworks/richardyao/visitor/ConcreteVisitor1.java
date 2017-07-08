package com.tvunetworks.richardyao.visitor;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 28, 2016 4:47:32 PM
 */
public class ConcreteVisitor1 extends Visitor {

	@Override
	void visitConcreteElm1(ConcreteElm1 elm1) {
		System.out.println(elm1.getClass().getName() + " is accessed by " + this.getClass().getName());
	}

	@Override
	void visitConcreteElm2(ConcreteElm2 elm2) {
		System.out.println(elm2.getClass().getName() + " is accessed by " + this.getClass().getName());
	}

}
