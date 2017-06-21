package com.tvunetworks.richardyao.test;
/**
* @author RichardYao richardyao@tvunetworks.com
* @date 2017年6月21日 下午1:36:27
*/
public class InheritTest {

	public static void main(String[] args) {
		System.out.println("main start!");
		new Children();
	}
}

class Parent {
	
	//public static Parent parent = new Parent(); //这里的这个静态对象一开始实例化，需要先初始化变量再初始化构造器
	public static String p_staticField = "父类--静态变量";
	public String p_field = "父类--变量";
	protected int i = 9;
	protected int j = 0;
	
	static {
		System.out.println(p_staticField);
		System.out.println("父类--静态初始化块");
	}
	
	{
		System.out.println(p_field);
		System.out.println("父类--初始化块");
		System.out.println("i=" + i + ", j=" + j);
	}
	
	public Parent() {
		System.out.println("父类--构造器");
		System.out.println("i=" + i + ", j=" + j + ", k=" + k);
		i = -1;
		j = 20;
		k = 30;
	}
	
	protected int k = 10;
}

class Children extends Parent {
	
	public static String c_staticField = "子类--静态变量";
	public String c_field = "子类--变量";
	
	static {
		System.out.println(c_staticField);
		System.out.println("子类--静态初始化块");
	}
	
	{
		System.out.println(c_field);
		System.out.println("子类--初始化块");
	}
	
	public Children() {
		System.out.println("子类--构造器");
		System.out.println("i=" + i + ", j=" + j + ", k=" + k);
	}
}
