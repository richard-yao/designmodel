package com.tvunetworks.richardyao.model;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Oct 17, 2016 3:21:30 PM
 */
public class ProxyModel {
	
	public PrintSomething ps;
	
	public void setPrintSomething(PrintSomething ps) {
		this.ps = ps;
	}
	
	public String returnStr() {
		int value = 1;
		ps.printIn();
		value++;
		ps.printOut();
		value++;
		return String.valueOf(value);
	}

}
