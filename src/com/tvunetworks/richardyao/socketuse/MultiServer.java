package com.tvunetworks.richardyao.socketuse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 22, 2016 10:56:02 AM
 */
public class MultiServer extends ServerSocket {

	public static final int port = 12345;
	
	public MultiServer() throws IOException {
		super(port);
		try {
			while(true) {
				Socket socket = accept();
				new CreateThread(socket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Start server successfully!");
		try {
			new MultiServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class CreateThread extends Thread {
	
	public Socket client;
	public BufferedReader in;
	public PrintWriter out;

	public CreateThread(Socket socket) {
		client = socket;
		try {
			printOutReceive();
			start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void printOutReceive() throws IOException {
		in = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
		String resultData = null;
		String inputLine = null;
		while (((inputLine = in.readLine()) != null)) {
			resultData += inputLine;
		}
		in.close();
		System.out.println(resultData);
	}
	
	public void run() {
		try {
			out = new PrintWriter(client.getOutputStream());
			out.write("----Welcom----");
			out.flush();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}