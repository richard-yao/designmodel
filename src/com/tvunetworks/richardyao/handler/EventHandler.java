package com.tvunetworks.richardyao.handler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Oct 21, 2016 5:31:49 PM
 */
public class EventHandler {
	
	private List<Event> workOrder;
	
	public EventHandler() {
		workOrder = new ArrayList<Event>();
	}
	
	public void addEvent(Object object, String methodName, Object...args) {
		workOrder.add(new Event(object, methodName, args));
	}
	
	public void notifyAction() throws Exception {
		for (Event e : workOrder) {
			e.invoke();
		}
	}

}
