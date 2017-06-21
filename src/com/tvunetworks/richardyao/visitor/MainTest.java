package com.tvunetworks.richardyao.visitor;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 28, 2016 4:58:22 PM
 */
public class MainTest {
	
	public static void main(String[] args) {
		ObjectStructure object = new ObjectStructure();
		object.attach(new ConcreteElm1());
		object.attach(new ConcreteElm2());
		
		ConcreteVisitor1 visitor1 = new ConcreteVisitor1();
		ConcreteVisitor2 visitor2 = new ConcreteVisitor2();
		
		object.accept(visitor1);
		object.accept(visitor2);
	}

}
