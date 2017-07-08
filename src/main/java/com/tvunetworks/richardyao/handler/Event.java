package com.tvunetworks.richardyao.handler;

import java.lang.reflect.Method;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Oct 21, 2016 5:14:17 PM
 */
public class Event {
	
	//要执行方法的对象
	private Object object;
	//执行的类的方法名
	private String methodName;
	//传入的参数
	private Object[] params;
	//对应的参数类型
	@SuppressWarnings("rawtypes")
	private Class[] paramTypes;

	public Event(Object object, String methodName, Object...params) {
		this.object = object;
		this.methodName = methodName;
		this.params = params;
		generateParamTypes(params);
	}
	
	private void generateParamTypes(Object[] params) {
		this.paramTypes = new Class[params.length];
		for(int i=0;i<params.length;i++) {
			this.paramTypes[i] = params[i].getClass();
		}
	}
	
	public void invoke() throws Exception {
		Method method = object.getClass().getMethod(getMethodName(), getParamTypes());
		if(method == null) {
			return;
		}
		method.invoke(getObject(), getParams());
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	@SuppressWarnings("rawtypes")
	public Class[] getParamTypes() {
		return paramTypes;
	}

	@SuppressWarnings("rawtypes")
	public void setParamTypes(Class[] paramTypes) {
		this.paramTypes = paramTypes;
	}
	
}
