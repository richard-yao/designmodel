package com.tvunetworks.richardyao.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 29, 2016 5:02:11 PM
 * NIO双向通道，双向注册
 */
public class NIOClient {

	private Selector selector;
	private SocketChannel channel;
	
	public void initClient(String ip, int port) {
		try {
			channel = SocketChannel.open();
			channel.configureBlocking(false);
			selector = Selector.open();
			//客户端连接服务器，真正执行事件是在listen()中调用channel.finishConnect()
			channel.connect(new InetSocketAddress(ip, port));
			channel.register(selector, SelectionKey.OP_CONNECT);//客户端注册连接事件
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void listen() {
		while(true) {
			try {
				selector.select();
				Iterator<?> iterator = selector.selectedKeys().iterator();
				while(iterator.hasNext()) {
					SelectionKey key = (SelectionKey) iterator.next();
					iterator.remove();
					if(key.isConnectable()) {
						SocketChannel channel = (SocketChannel) key.channel();
						if(channel.isConnectionPending()) {
							channel.finishConnect();//真正连接了服务器上
						}
						channel.configureBlocking(false);
						channel.write(ByteBuffer.wrap("Connect has built!!!".getBytes("UTF-8")));
						channel.register(selector, SelectionKey.OP_READ);
					} else if(key.isReadable()) {
						read(key);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 处理客户端发送到服务端的信息
	 * @param key
	 */
	public void read(SelectionKey key) {
		//服务器可读取消息，得到事件发生的Socket通道
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);//创建读取的缓冲区
		try {
			channel.read(buffer);
			byte[] data = buffer.array();
			String msg = new String(data).trim();
			buffer.clear();
			System.out.println("Client receive message: "+msg);
			/*ByteBuffer outBuffer = ByteBuffer.wrap((msg).getBytes("UTF-8"));
			channel.write(outBuffer);
			outBuffer.clear();*/
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		for(int i=0;i<1;i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					NIOClient client = new NIOClient();
					client.initClient("localhost", 61616);
					client.listen();
					/*try {
						client.channel.close();
					} catch (IOException e) {
						e.printStackTrace();
					}*/
				}
			}).start();
		}
		
	}
	
}
