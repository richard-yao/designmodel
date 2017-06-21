package com.tvunetworks.richardyao.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 28, 2016 4:55:53 PM
 */
public class ObjectStructure {
	
	private List<Element> list = new ArrayList<Element>();
	
	public void attach(Element elm) {
		list.add(elm);
	}
	
	public void detach(Element elm) {
		list.remove(elm);
	}
	
	public void accept(Visitor visitor) {
		for(Element elm : list) {
			elm.accept(visitor);
		}
	}

}
