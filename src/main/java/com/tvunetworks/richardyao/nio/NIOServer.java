package com.tvunetworks.richardyao.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Nov 29, 2016 4:25:36 PM
 * NIO中服务端
 * 对于 ServerSocketChannel来说，accept是唯一的有效操作，而对于 SocketChannel来说，有效操作包括读、写和连接
 */
public class NIOServer {

	
	private Selector selector;
	
	/**
	 * init NIO
	 * @param port
	 */
	public void initServer(int port) {
		try {
			//ServerSocketChannel在调用open方法时才被创建
			ServerSocketChannel serverChannel = ServerSocketChannel.open();
			serverChannel.configureBlocking(false);
			//将该通道对应的ServerSocket绑定到指定的端口
			serverChannel.socket().bind(new InetSocketAddress(port));
			//获得一个可用的通道管理器
			selector = Selector.open();
			//将通道管理器和通道绑定并注册OP_ACCEPT事件
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);//OP_ACCEPT，服务端接收客户端连接事件
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 注册的通道管理器会获得通道上注册的事件
	 */
	public void listen() {
		System.out.println("Start server successfully!");
		while(true) {
			try {
				selector.select();//当注册的事件到达时，该方法继续执行，否则会一直阻塞
				//获得selector中选中项的迭代器，选中项为通道注册的事件OP_ACCEPT
				Iterator<?> iterator = selector.selectedKeys().iterator();
				while(iterator.hasNext()) {
					SelectionKey key = (SelectionKey) iterator.next();
					iterator.remove();//移除接下来要处理的key，防止重复处理
					if(key.isAcceptable()) {//客户端请求连接事件
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						SocketChannel channel = server.accept();//获得和客户端连接的通道
						channel.configureBlocking(false);//这里还需要设置下通道为非阻塞
						channel.write(ByteBuffer.wrap("Connect has built!!!".getBytes("UTF-8")));
						channel.register(selector, SelectionKey.OP_READ);//在和客户端连接成功后，给通道设置读的权限以获取客户端写的信息
					} else if(key.isReadable()) {//获得了可读的事件
						read(key);
					}
				}
				//selector.selectedKeys().clear();//在处理完selectedKeys后清空, 因为selector.select()并不会清空, 而是在已有集合上继续添加键值
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
			System.out.println("Server receive message: "+msg);
			ByteBuffer outBuffer = ByteBuffer.wrap((msg).getBytes("UTF-8"));
			while(outBuffer.hasRemaining()) {
				channel.write(outBuffer);
			}
			outBuffer.clear();
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		NIOServer server = new NIOServer();
		server.initServer(61616);
		server.listen();
	}
	
}
