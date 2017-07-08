package com.tvunetworks.richardyao.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Jan 13, 2017 3:38:52 PM
 * TCP是基于字节流的协议，它只能保证一方发送和另一方接收到的数据的字节顺序一致，但是，并不能保证一方每发送一条消息，另一方就能完整的接收到一条信息
 */
public class UseMinaNIO {
	
	public final static String SERVER_ADDRESS = "10.12.22.201";
	public final static int SERVER_PORT = 8880;
	
	public static void main(String[] args) throws IOException {
		IoAcceptor acceptor = new NioSocketAcceptor();
		specifyFilter(acceptor, 2);
		acceptor.setHandler(new TcpServerHandle());
		acceptor.bind(new InetSocketAddress(SERVER_ADDRESS, SERVER_PORT));
	}
	
	public static void specifyFilter(IoAcceptor acceptor, int style) {
		switch(style) {
			case 0 : acceptor.getFilterChain().addLast("codec", 
						new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"), "\r\n", "\r\n")));//按行读取,指定分隔符是\r\n
				break;
			case 1 : acceptor.getFilterChain().addLast("codec", 
						new ProtocolCodecFilter(new PrefixedStringCodecFactory(Charset.forName("UTF-8"))));//4字节的Header指定Body的字节数，对这种消息的处理
				break;
			case 2 : acceptor.getFilterChain().addLast("codec", 
						new ProtocolCodecFilter(new MyMinaEncoder(), new MyMinaDecoder()));
				break;
			default:break;
		}
	}

}

class TcpServerHandle extends IoHandlerAdapter {
	
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		cause.printStackTrace();
	}
	
	//接收到新消息时触发事件
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		//testIoBuffer(session, message);
		//testGetStringMsg(session, message);
		testCustomProtocol(session, message);
	}
	
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("sessionCreated");
	}
	
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("sessionClosed");
	}
	
	/**
	 * Receive message with only one send
	 * @param session
	 * @param message
	 * @throws Exception
	 */
	public void testIoBuffer(IoSession session, Object message) throws Exception {
		//接收消息
		IoBuffer ioBuffer = (IoBuffer) message;
		byte[] byteArray = new byte[ioBuffer.limit()];
		ioBuffer.get(byteArray, 0, ioBuffer.limit());
		System.out.println("messageReceived: " + new String(byteArray, "UTF-8"));
		sentBackMsg(session);
	}
	
	public void testGetStringMsg(IoSession session, Object message) throws Exception {
		// 接收客户端的数据，这里接收到的不再是IoBuffer类型，而是字符串
		String line = (String) message;
		System.out.println("messageReceived:" + line);
		sentBackMsg(session);
	}
	
	public void sentBackMsg(IoSession session) throws Exception {
		//发送消息到客户端
		byte[] responseByteArray = "Hello".getBytes("UTF-8");
		IoBuffer responseIoBuffer = IoBuffer.allocate(responseByteArray.length);
		responseIoBuffer.put(responseByteArray);
		responseIoBuffer.flip();
		session.write(responseIoBuffer);
	}
	
	public void testCustomProtocol(IoSession session, Object message) throws Exception {
		// MyMinaDecoder将接收到的数据由IoBuffer转为String
		String line = (String) message;
		System.out.println("messageReceived:" + line);
		
		session.write("收到");
	}
}