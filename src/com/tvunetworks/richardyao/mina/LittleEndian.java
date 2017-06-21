package com.tvunetworks.richardyao.mina;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Jan 16, 2017 9:54:53 AM
 */
public class LittleEndian {

	/**
	 * 将int转换成4字节的小字节序字节数组
	 * @param i
	 * @return
	 */
	public static byte[] toLittleEnding(int i) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) i;//在把int赋值费byte类型时, 只取32位的低八位赋值
		bytes[1] = (byte) (i >>> 8);
		bytes[2] = (byte) (i >>> 16);
		bytes[3] = (byte) (i >>> 24);
		return bytes;
	}
	
	/**
	 * 将小字节序的4字节数组转成int
	 * @param bytes
	 * @return
	 */
	public static int getLittleEndianInt(byte[] bytes) {
		int b0 = bytes[0] & 0xFF; //在与0xff做逻辑运算之前,byte[0]被转换成int,与0xff做与运算, 清空高24位
		int b1 = bytes[1] & 0xFF;
		int b2 = bytes[2] & 0xFF;
		int b3 = bytes[3] & 0xFF;
		return b0 + (b1 << 8) + (b2 << 16) + (b3 << 24);
	}
	
	public static void main(String[] args) {
		byte[] result = toLittleEnding(8);
		System.out.println(result[0]+", "+result[1]);
	}
}
