package com.tvunetworks.richardyao.threadpool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Dec 13, 2016 2:23:25 PM
 */
public class MainUse {
	
	private static MainUse mainUse = null;
	
	public static Map<String, String> listQueue = new ConcurrentHashMap<String, String>();
	
	private static ThreadPool threadPool = null;
	
	public static volatile int totalUrl = 1;
	
	public static MainUse getInstance(int number) {
		if(mainUse == null) {
			synchronized (MainUse.class) {
				if(mainUse == null) {
					mainUse = new MainUse();
					threadPool = ThreadPool.getThreadPool(number);
				}
			}
		}
		return mainUse;
	}
	
	public void useThreadPool() {
		String[] addressUrl = {"http://www.baidu.com","http://www.google.com","http://tool.chinaz.com/Tools/unixtime.aspx","http://www.admin10000.com/document/5250.html"};
		List<String> arrayList = new ArrayList<String>(Arrays.asList(addressUrl));
		useThreadPoolGetPage(arrayList);
	}
	
	public void executeGetUrl() {
		if(listQueue.size() > 0) {
			List<String> arrayList = new ArrayList<String>();
			Iterator<String> keyList = listQueue.keySet().iterator();
			while(keyList.hasNext()) {
				String key = keyList.next();
				String page = listQueue.get(key);
				analysisHref(page, arrayList);
				if(totalUrl > 100) {
					return;
				}
				for(int i=0;i<arrayList.size();i++) {
					System.out.println(arrayList.get(i));
				}
				//useThreadPoolGetPage(arrayList);
				keyList.remove();
			}
		}
	}
	
	private void analysisHref(String page, List<String> arrayList) {
		if (page != null && page.length() > 0 && page.indexOf("href=") > -1) {
			int start = page.indexOf("href=");
			String tempPage = page.substring(start);
			int end = tempPage.indexOf(">") > -1 ? tempPage.indexOf(">") : tempPage.indexOf(" ") > -1 ? tempPage.indexOf(" ") : tempPage.indexOf("?");
			if(end == -1) {
				return;
			}
			String tempHref = tempPage.substring(5, end);
			if(tempHref.indexOf("http") == -1) {
				return;
			}
			tempHref = tempHref.indexOf("\"") > -1 ? tempHref.replace("\"", "") : tempHref;
			arrayList.add(tempHref);
			if(page.length() > end+1) {
				tempPage = page.substring(end+1);
				analysisHref(tempPage, arrayList);
			}
		}
	}
	
	/**
	 * 将要获取的url路径加入到线程池中执行
	 * @param addressUrl
	 */
	@SuppressWarnings("unchecked")
	public void useThreadPoolGetPage(List<String> addressUrl) {
		List<Runnable> taskList = new ArrayList<Runnable>();
		for (int i = 0; i < addressUrl.size(); i++) {
			taskList.add(new Task(addressUrl.get(i)));
			totalUrl++;
		}
		taskList = (List<Runnable>) getList(taskList);
		threadPool.execute(taskList);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executeGetUrl();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadPool.destory();
		/*System.out.println(threadPool);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(threadPool);
		threadPool.destory();
		taskList.add(new Task());*/
	}
	
	//通过动态代理后生成的List不允许使用add方法
	public List<?> getList(final List<?> list) {
		return (List<?>) Proxy.newProxyInstance(TestThreadPool.class.getClassLoader(), new Class[] { List.class }, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if ("add".equals(method.getName())) {
					throw new UnsupportedOperationException();
				} else {
					return method.invoke(list, args);
				}
			}
		});
	}

}

class Task implements Runnable {

	public String url;
	
	public Task() {
		this("http://www.baidu.com");
	}
	
	public Task(String uri) {
		this.url = uri;
	}
	
	@Override
	public void run() {
		String page = HttpClient.send(url);
		if(page != null) {
			MainUse.listQueue.put(url, page);
		}
	}
}
