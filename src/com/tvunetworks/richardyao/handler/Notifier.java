package com.tvunetworks.richardyao.handler;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Oct 21, 2016 5:37:34 PM
 */
public abstract class Notifier {
	
	private EventHandler eventHandler = new EventHandler();

	public EventHandler getEventHandler() {
		return eventHandler;
	}

	public void setEventHandler(EventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}
	
	public abstract void addListener(Object object, String methodName, Object...args);
	
	public abstract void notifyAction();

}
