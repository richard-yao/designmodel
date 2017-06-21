package com.tvunetworks.richardyao.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Jan 16, 2017 2:31:51 PM
 */
public class TestMultiThreads implements Runnable {
	
	private static int i;
	
	private static volatile Integer vi = 0;
	
	private static AtomicInteger ai = new AtomicInteger();
	
	private static Integer si = 0;
	
	private static int ri;
	
	private static AtomicInteger flag = new AtomicInteger();
	
	private static Lock lock = new ReentrantLock();
	
	@Override
	public void run() {
		for(int k=0;k<200000;k++){
			i++;
			synchronized (TestMultiThreads.class) {
				vi++;
			}
			ai.incrementAndGet();
			synchronized(si){
				si++;
			}
			lock.lock();
			try{
				ri++;//由于lock对象本身不是static导致在多线程时实例化多个, 加锁就毫无意义了
			}finally{
				lock.unlock();
			}
			
		}
		flag.incrementAndGet();
	}
	
	public static void main(String[] args) throws InterruptedException{
		TestMultiThreads t1 = new TestMultiThreads();
		TestMultiThreads t2 = new TestMultiThreads();
		ExecutorService exec1 = Executors.newCachedThreadPool();
		ExecutorService exec2 = Executors.newCachedThreadPool();
		exec1.execute(t1);
		exec2.execute(t2);
		while(true){
			if(flag.intValue()==2){
				System.out.println("i>>>>>"+i);
				System.out.println("vi>>>>>"+vi);
				System.out.println("ai>>>>>"+ai);
				System.out.println("si>>>>>"+si);	
				System.out.println("ri>>>>>"+ri);	
				break;
			}
			Thread.sleep(50);
		}
	}

}
