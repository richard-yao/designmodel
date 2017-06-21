package com.tvunetworks.richardyao.threadpool;

import java.util.LinkedList;
import java.util.List;

/**
 * 该类final声明, 禁止继承
 * @author RichardYao richardyao@tvunetworks.com
 * @date Dec 12, 2016 4:12:56 PM
 */
public final class ThreadPool {

	//线程池中的线程的默认数量
	private static int workNum = 5;
	//工作线程
	private WorkThread[] workThreads;
	//处理完成的任务
	private static volatile int finishedTask = 1;
	//任务队列, 线程不安全
	private List<Runnable> taskQueue = new LinkedList<Runnable>();
	private static ThreadPool threadPool;
	
	private ThreadPool() {
		this(5);
	}
	
	private ThreadPool(int workNum) {
		ThreadPool.workNum = workNum;
		workThreads = new WorkThread[workNum];
		for(int i = 0;i<workNum;i++) {
			workThreads[i] = new WorkThread();
			workThreads[i].start();
		}
	}
	
	public static ThreadPool getThreadPool() {
		return getThreadPool(ThreadPool.workNum);
	}
	
	//创建一个指定线程数量的线程池
	public static ThreadPool getThreadPool(int workNum) {
		if(workNum <= 0) {
			workNum = ThreadPool.workNum;
		}
		if(threadPool == null) {
			threadPool = new ThreadPool(workNum);
		}
		return threadPool;
	}
	
	//将任务加入队列
	public void execute(Runnable task) {
		synchronized (taskQueue) {
			taskQueue.add(task);
			taskQueue.notify();
		}
	}
	
	//将多个任务同时添加到队列中
	public void execute(Runnable[] tasks) {
		synchronized (taskQueue) {
			for(Runnable temp:tasks) {
				taskQueue.add(temp);
			}
			taskQueue.notify();
		}
	}
	
	//将多个任务同时添加到队列中
	public void execute(List<Runnable> tasks) {
		synchronized (taskQueue) {
			for(Runnable temp:tasks) {
				taskQueue.add(temp);
			}
			taskQueue.notify();
		}
	}
	
	//销毁线程池
	public void destory() {
		//任务没有执行完
		while(!taskQueue.isEmpty()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int i=0;i<workNum;i++) {
			workThreads[i].stopWork();
			workThreads[i] = null;//销毁任务
		}
		threadPool = null;
		taskQueue.clear();
	}
	
	//返回工作线程个数
	public int getWorkThreadNumber() {
		return workNum;
	}
	
	//返回已完成任务个数
	public int getFinishedTaskNumber() {
		return finishedTask;
	}
	
	//返回等待任务数量
	public int getWaitTaskNumber() {
		return taskQueue.size();
	}
	
	@Override
	public String toString() {
		return "WorkThread number:" + getWorkThreadNumber() + "  finished task number:" + getFinishedTaskNumber() + "  wait task number:" + getWaitTaskNumber();
	}
	
	//内部类, 工作线程
	private class WorkThread extends Thread {
		
		private boolean isRunning = true;
		
		@Override
		public void run() {
			Runnable r = null;
			while(isRunning) {
				synchronized (taskQueue) {
					while(isRunning && taskQueue.isEmpty()) {
						try {
							taskQueue.wait(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					if(!taskQueue.isEmpty()) {
						r = taskQueue.remove(0);//取出第一个任务执行
					}
					if(r != null) {
						r.run();
					}
					finishedTask++;
					r = null;
				}
			}
		}
		
		public void stopWork() {
			isRunning = false;
		}
	}
	
}
