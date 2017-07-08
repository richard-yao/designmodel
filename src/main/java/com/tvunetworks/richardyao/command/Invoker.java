package com.tvunetworks.richardyao.command;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 18, 2016 3:46:18 PM
 */
public class Invoker {
	
	private Command command;
	
	public void setCommand(Command command) {
		this.command = command;
	}
	
	public void executeCommand() {
		command.execute();
	}

}
