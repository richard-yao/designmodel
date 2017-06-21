package com.tvunetworks.richardyao.handler;

import java.util.Date;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Oct 21, 2016 5:41:57 PM
 */
public class PlayingGameListener {

	public PlayingGameListener() {
		System.out.println("我正在玩游戏 开始时间" + new Date());
	}

	public void stopPlayingGame(Date date) {
		System.out.println("老师来了，快回到座位上，结束时间" + date);
	}

}
