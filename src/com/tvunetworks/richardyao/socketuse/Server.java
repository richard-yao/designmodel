package com.tvunetworks.richardyao.socketuse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 21, 2016 5:54:41 PM
 */
public class Server {
	
	public ServerSocket server;
	public Socket socket;
	public BufferedReader reader;
	public PrintWriter writer;
	
	public Server() {
		try {
			server = new ServerSocket(12345);
			while(true) {
				socket = server.accept();
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				writer = new PrintWriter(socket.getOutputStream(), true);
				
				String string = reader.readLine();//读取到客户端发送的数据
				writer.println("Your input is "+string);//将新的数据发送给客户端
				System.out.println("Your input is "+string);
				writer.close();
				reader.close();
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Server();
	}

}
