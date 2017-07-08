package com.tvunetworks.richardyao.handler;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Oct 21, 2016 5:40:26 PM
 */
public class GoodNotifier extends Notifier {

	@Override
	public void addListener(Object object, String methodName, Object...args) {
		this.getEventHandler().addEvent(object, methodName, args);
	}

	@Override
	public void notifyAction() {
		try {
			this.getEventHandler().notifyAction();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
