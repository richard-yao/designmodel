package com.tvunetworks.richardyao.flyweight;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 23, 2016 5:24:56 PM
 */
public class TestMain {
	
	
	public static void main(String[] args) {
		WebSiteFactory factory = new WebSiteFactory();
		WebSite fx = factory.getWebSiteCategory("richardyao");
		fx.printOut("1111");
		WebSite fx1 = factory.getWebSiteCategory("richardyao");
		fx1.printOut(123);
		WebSite fx2 = factory.getWebSiteCategory("richardyao");
		fx2.printOut(1.01);
		WebSite fx3 = factory.getWebSiteCategory("richardyao");
		fx3.printOut(11111);
		System.out.println(factory.webSiteCount());
	}

}
