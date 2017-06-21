package com.tvunetworks.richardyao.singleton;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 16, 2016 10:30:21 AM
 */
public class SingleInstance {
	
	private volatile static SingleInstance singleInstance = null;
	
	private SingleInstance() {
		
	}
	
	/**
	 * 懒汉式单例模式，双重锁定
	 * @return
	 */
	public static SingleInstance getInstance() {
		if(singleInstance == null) {
			synchronized (SingleInstance.class) {//这里的锁的含义是当多线程执行到这里时，只允许获得锁的线程继续执行，所以可以有多个线程先后继续执行下去
				if(singleInstance == null) {//因此这里需要两重判断singleInstance是否为null，只有为null时才实例化
					singleInstance = new SingleInstance();
				}
			}
		}
		return singleInstance;
	}
	
	public static void printResult() {
		System.out.println("Use singleton design model!");
	}

}

class SingleInstanceAnother {
	
	private static SingleInstanceAnother another = null;
	
	static {
		another = new SingleInstanceAnother();
	}
	
	private SingleInstanceAnother() {
		
	}
	
	public static SingleInstanceAnother getInstance() {
		return another;
	}
}
