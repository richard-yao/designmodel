package com.tvunetworks.richardyao.command;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 18, 2016 3:43:19 PM
 */
public class ConcreteCommand extends Command {

	public ConcreteCommand(Receiver receiver) {
		super(receiver);
	}

	@Override
	public void execute() {
		receiver.action();
	}

}
