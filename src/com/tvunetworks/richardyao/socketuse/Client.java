package com.tvunetworks.richardyao.socketuse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 21, 2016 4:42:57 PM
 */
public class Client {

	Socket socket;
	BufferedReader in;
	PrintWriter out;

	public Client() {
		try {
			socket = new Socket("10.12.22.201", 12345); // 服务器地址和端口号
			in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			out = new PrintWriter(socket.getOutputStream());
			//BufferedReader line = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
			String value = String.valueOf(System.currentTimeMillis());
			/*System.out.println("Please input your characters: ");
			value = line.readLine();*/
			out.write(value);//将数据发送给服务端
			out.flush();
			//line.close();
			System.out.println(in.readLine());//读取服务端返回数据
			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		for(int i=0;i<5;i++) {
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					new Client();
				}
			});
			thread.start();
		}
	}
}
