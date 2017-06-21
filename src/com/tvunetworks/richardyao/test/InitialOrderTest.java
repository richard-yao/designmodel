package com.tvunetworks.richardyao.test;
/**
* @author RichardYao richardyao@tvunetworks.com
* @date 2017年6月21日 下午1:23:46
*/
public class InitialOrderTest {

	public static String staticField = "静态变量";
	public String field = "变量";
	
	static {
		System.out.println(staticField);
		System.out.println("静态初始化块");
	}
	
	{
		System.out.println(field);
		System.out.println("初始化块");
	}
	
	public InitialOrderTest() {
		System.out.println("构造器");
	}
	
	public static void main(String[] args) {
		new InitialOrderTest();
	}

}
