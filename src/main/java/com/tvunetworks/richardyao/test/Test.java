package com.tvunetworks.richardyao.test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
* @author RichardYao richardyao@tvunetworks.com
* @date 2017年7月6日 下午3:56:48
*/
public abstract class Test {
	
	public final void doSomething() throws Exception {
		Method[] methods = this.getClass().getDeclaredMethods();
		System.out.println("Strat...");
		for(Method method : methods) {
			if(this.checkInitMethod(method)) {
				method.invoke(this);
			}
		}
		System.out.println("End...");
	}
	
	private boolean checkInitMethod(Method method) {
		return method.getName().startsWith("init")
				&& Modifier.isPublic(method.getModifiers()) // public方法
				&& method.getReturnType().equals(Void.TYPE) // 无返回值void方法
				&& !method.isVarArgs() // 无输入参数
				&& !Modifier.isAbstract(method.getModifiers()); // 不是abstract方法
	}
}
