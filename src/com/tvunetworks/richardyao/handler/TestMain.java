package com.tvunetworks.richardyao.handler;

import java.util.Date;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Oct 21, 2016 5:42:47 PM
 */
public class TestMain {
	
	public static void main(String[] args) {
		Notifier notifier = new GoodNotifier();
		PlayingGameListener listener = new PlayingGameListener();
		notifier.addListener(listener, "stopPlayingGame", new Date());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notifier.notifyAction();
		
	}

}
