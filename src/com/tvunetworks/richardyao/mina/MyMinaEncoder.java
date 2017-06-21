package com.tvunetworks.richardyao.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Jan 16, 2017 10:24:05 AM
 * 自定义继承自Mina的编码器, 将body长度编码成小字节序byte数组
 */
public class MyMinaEncoder extends ProtocolEncoderAdapter {

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		String msg = (String) message;
		byte[] bytes = msg.getBytes("UTF-8");
		int length = bytes.length;
		byte[] header = LittleEndian.toLittleEnding(length);// 按小字节序转成字节数组
		
		IoBuffer.setUseDirectBuffer(false);//配置申请缓冲时使用堆内存而不是直接内存
		IoBuffer buffer = IoBuffer.allocate(length + 4);//Returns the direct or heap buffer which is capable to store the specified amount of bytes
		buffer.put(header);// header
		buffer.put(bytes);// body
		buffer.flip();
		out.write(buffer);
	}

}
