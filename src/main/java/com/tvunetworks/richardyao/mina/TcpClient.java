package com.tvunetworks.richardyao.mina;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Jan 13, 2017 4:10:52 PM
 */
public class TcpClient {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Socket socket = null;
		//OutputStream out = null;
		DataOutputStream out = null;
		InputStream in = null;
		
		try {
			socket = new Socket(UseMinaNIO.SERVER_ADDRESS, UseMinaNIO.SERVER_PORT); 
			out = new DataOutputStream(socket.getOutputStream());
			in = socket.getInputStream();
			//sentTwoMsgOut(socket, out, in);
			//sentMsg4TestSplit(socket, out, in);
			//sentMsg4TestSplitHard(out);
			//sentWith4WordHeader(out);
			testCustomProtocol(out, in);
		} finally {
			in.close();
			out.close();
			socket.close();
		}

	}
	
	/**
	 * Sent two messages out
	 * @param socket
	 * @param out
	 * @param in
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void sentTwoMsgOut(Socket socket, OutputStream out, InputStream in) throws IOException, InterruptedException {
		//请求服务器
		out.write("The first request".getBytes("UTF-8"));
		out.flush();
		
		//获取服务器响应,输出
		byte[] byteArray = new byte[1024];
		int length = in.read(byteArray);
		System.out.println(new String(byteArray, 0, length, "UTF-8"));
		
		Thread.sleep(5000);
		
		//再次请求服务器
		out.write("The second request".getBytes("UTF-8"));
		out.flush();
		
		//再次响应并输出
		byteArray = new byte[1024];
		length = in.read(byteArray);
		System.out.println(new String(byteArray, 0, length, "UTF-8"));
	}
	
	public static void sentMsg4TestSplit(Socket socket, OutputStream out, InputStream in) throws IOException {
		// 请求服务器
		String lines = "床前明月光\r\n疑是地上霜\r\n举头望明月\r\n低头思故乡\r\n";
		byte[] outputBytes = lines.getBytes("UTF-8");
		out.write(outputBytes);
		out.flush();
		
		//获取服务器响应,输出
		byte[] byteArray = new byte[1024];
		int length = in.read(byteArray);
		System.out.println(new String(byteArray, 0, length, "UTF-8"));
	}
	
	public static void sentMsg4TestSplitHard(OutputStream out) throws IOException, InterruptedException {
		String lines = "床前";
		byte[] outputBytes = lines.getBytes("UTF-8");
		out.write(outputBytes);
		out.flush();
		
		Thread.sleep(1000);
		
		lines = "明月";
		outputBytes = lines.getBytes("UTF-8");
		out.write(outputBytes);
		out.flush();
		
		Thread.sleep(1000);
		
		lines = "光\r\n疑是地上霜\r\n举头";
		outputBytes = lines.getBytes("UTF-8");
		out.write(outputBytes);
		out.flush();
		
		Thread.sleep(1000);
		
		lines = "望明月\r\n低头思故乡\r\n";
		outputBytes = lines.getBytes("UTF-8");
		out.write(outputBytes);
		out.flush();
	}
	
	public static void sentWith4WordHeader(DataOutputStream out) throws IOException, InterruptedException {
		// 请求服务器
		String data1 = "牛顿";
		byte[] outputBytes1 = data1.getBytes("UTF-8");
		out.writeInt(outputBytes1.length); // write header
		out.write(outputBytes1); // write body
		
		String data2 = "爱因斯坦";
		byte[] outputBytes2 = data2.getBytes("UTF-8");
		out.writeInt(outputBytes2.length); // write header
		out.write(outputBytes2); // write body
		
		out.flush();
	}
	
	public static void testCustomProtocol(OutputStream out, InputStream in) throws IOException {
		// 请求服务器
		String data = "我是客户端";
		byte[] outputBytes = data.getBytes("UTF-8");
		out.write(LittleEndian.toLittleEnding(outputBytes.length)); // write header
		out.write(outputBytes); // write body
		out.flush();
		
		// 获取响应
		byte[] inputBytes = new byte[1024];
		int length = in.read(inputBytes);
		if(length >= 4) {
			int bodyLength = LittleEndian.getLittleEndianInt(inputBytes);
			if(length >= 4 + bodyLength) {
				byte[] bodyBytes = Arrays.copyOfRange(inputBytes, 4, 4 + bodyLength);
				System.out.println("Header:" + bodyLength);
				System.out.println("Body:" + new String(bodyBytes, "UTf-8"));
			}
		}
	}

}
