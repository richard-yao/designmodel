package com.tvunetworks.richardyao.command;



/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 18, 2016 3:40:20 PM
 */
public abstract class Command {

	protected Receiver receiver;
	
	public Command(Receiver receiver) {
		this.receiver = receiver;
	}
	
	public abstract void execute();
}
