package com.tvunetworks.richardyao.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 18, 2016 3:47:32 PM
 */
public class TestMain {

	public static void main(String[] args) {
		String string = "richard123";
		customePattern(string);
	}
	
	public static void testCommand() {
		Receiver receiver = new Receiver();
		Command command = new ConcreteCommand(receiver);
		Invoker invoker = new Invoker();
		invoker.setCommand(command);
		invoker.executeCommand();
	}
	
	public static void customePattern(String str) {
		String regex = "(\\D*)(\\d+)(.*)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if(matcher.find()) {
			for(int i=0;i<matcher.groupCount();i++) {
				System.out.println(matcher.group(i));
			}
		}
	}
}
