package com.tvunetworks.richardyao.model;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Oct 17, 2016 3:08:01 PM
 */
public class MainTest {

	public static void main(String[] args) {
		usePerson();
		//useProxy();

	}
	
	public static void usePerson() {
		Person person = new Person("richardyao");
		
		BigTrouser bt = new BigTrouser();
		TShirts ts = new TShirts();
		
		bt.decorate(person);
		ts.decorate(bt);
		
		ts.show();
	}
	
	public static void useProxy() {
		ProxyModel model = new ProxyModel();
		PrintSomething ps = new PrintSomethingImpl();
		model.setPrintSomething(ps);
		System.out.println(model.returnStr());
	}

}
