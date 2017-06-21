package com.tvunetworks.richardyao.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Jan 16, 2017 10:14:21 AM
 * 自定义继承自Mina的解码器, header部分4字节, 表示body的长度
 * doDecode的触发, 是在Mina接收到tcp数据时发生, 而根据TCP协议, 字节流的传递只能保证顺序一致而不保证一条消息不被拆开多次发送
 */
public class MyMinaDecoder extends CumulativeProtocolDecoder {

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		// 如果没有接收完4字节的头, 直接返回false
		if(in.remaining() < 4) {
			return false;
		} else {
			// 标记开始位置,如果一条消息没传输完则返回到这个位置
			in.mark();
			
			byte[] bytes = new byte[4];
			in.get(bytes);// 读取4字节的header
			
			int bodyLenth = LittleEndian.getLittleEndianInt(bytes); // 按小字节序转int
			if(in.remaining() < bodyLenth) {
				in.reset();// IoBuffer position回到原来标记地方等待下次读取
				return false;
			} else {
				byte[] bodyBytes = new byte[bodyLenth];
				in.get(bodyBytes);
				String body = new String(bodyBytes, "UTF-8");
				out.write(body);
				return true;// 解析出一条新消息
			}
		}
	}

}
