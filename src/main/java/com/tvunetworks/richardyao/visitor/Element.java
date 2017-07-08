package com.tvunetworks.richardyao.visitor;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 28, 2016 4:51:42 PM
 */
public abstract class Element {

	
	abstract void accept(Visitor visitor);
}
