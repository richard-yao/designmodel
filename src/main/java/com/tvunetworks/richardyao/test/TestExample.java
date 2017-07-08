package com.tvunetworks.richardyao.test;
/**
* @author RichardYao richardyao@tvunetworks.com
* @date 2017年7月7日 下午3:49:04
*/
public class TestExample extends Test {

	public void initB() {
		System.out.println("InitB method");
	}
	
	public void initA() {
		System.out.println("InitA method");
	}
	
	public static void main(String[] args) {
		TestExample example = new TestExample();
		try {
			example.doSomething();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
