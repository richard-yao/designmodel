package com.tvunetworks.richardyao.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Oct 26, 2016 10:17:49 AM
 */
public class MainTest {

	public static Map<String, String> allDevice = new ConcurrentHashMap<>();

	public static void init() {
		for (int i = 0; i < 10; i++) {
			allDevice.put("123" + i, "richard");
		}
	}

	public static void testForMethod(String value) {
		String key = null;
		String val = null;
		for (Entry<String, String> entry : allDevice.entrySet()) {
			key = entry.getKey();
			val = entry.getValue();
			if (key.indexOf(value) > -1) {
				System.out.println("Before change: " + val);
				allDevice.put(key, "111111");
				System.out.println(allDevice.get(key));
				allDevice.put("zzz", "22222");
			}
			System.out.println(key + ":::" + val);
		}
		System.out.println(allDevice.get("123" + value));
	}

	public static void testInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		System.out.println("Enter your value:");
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("your value is :" + str);
	}

	public static void main(String[] args) {
		//init();
		//testForMethod("8");
		testInput();
	}
	
	public void testSwitch(String input) {
		switch(input) {
			case "123": System.out.println("What !");break;
			case "abc": System.out.println("AlphaBet");break;
			case "null": System.out.println("Nothing");break;
			default: System.out.println("Default value");break;
		}
	}
	
	@Test
	public void testSwitch() {
		MainTest test = new MainTest();
		test.testSwitch("123");
	}
}
