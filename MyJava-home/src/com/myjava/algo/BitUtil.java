package com.myjava.algo;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class BitUtil {
	public static byte[] int2byte(int res) {
		byte[] targets = new byte[4];

		targets[0] = (byte) (res & 0xff);// ���λ
		targets[1] = (byte) ((res >> 8) & 0xff);// �ε�λ
		targets[2] = (byte) ((res >> 16) & 0xff);// �θ�λ
		targets[3] = (byte) (res >>> 24);// ���λ,�޷������ơ�
		return targets;
	}

	public static int byte2int(byte[] res) {
		// һ��byte��������24λ���0x??000000��������8λ���0x00??0000

		int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // | ��ʾ��λ��
				| ((res[2] << 24) >>> 8) | (res[3] << 24);
		return targets;
	}

	public static byte[] intToByteArray(int i) throws Exception {
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(buf);
		System.out.println("i:" + i);
		out.writeInt(i);
		byte[] b = buf.toByteArray();
		System.out.println("i:" + b);
		out.close();
		buf.close();
		return b;
	}

	/**
	 * ��16λ��shortת����byte����
	 * 
	 * @param s
	 *            short
	 * @return byte[] ����Ϊ2
	 */
	public static byte[] shortToByteArray(short s) {
		byte[] targets = new byte[2];
		for (int i = 0; i < 2; i++) {
			int offset = (targets.length - 1 - i) * 8;
			targets[i] = (byte) ((s >>> offset) & 0xff);
		}
		return targets;
	}

	/**
	 * ע�ͣ��ֽ����鵽short��ת����
	 * 
	 * @param b
	 * @return
	 */
	public static short byteToShort(byte[] b) {
		short s = 0;
		short s0 = (short) (b[0] & 0xff);// ���λ
		short s1 = (short) (b[1] & 0xff);
		s1 <<= 8;
		s = (short) (s0 | s1);
		return s;
	}

	/**
	 * ��byte[]ת����16���ƽ����ַ���
	 * 
	 * @param b
	 * @return
	 */
	public static String bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}

	/**
	 * byte[]ת����bit
	 * 
	 * @param b
	 * @return
	 */
	public static String bytesToBits(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bytes) {
			sb.append(byteToBits(b));
		}
		return sb.toString();
	}

	/**
	 * byteת����8λbit
	 * 
	 * @param b
	 * @return
	 */
	public static String byteToBits(byte b) {
		int z = b;
		z |= 256;
		String str = Integer.toBinaryString(z);
		int len = str.length();
		return str.substring(len - 8, len);
	}

	/**
	 * ����У���
	 * 
	 * @param bytes
	 * @return
	 */
	public static final int calculateCheckSum(byte[] bytes) {
		int sum = 0;
		for (byte b : bytes) {
			sum += (short) b;
		}
		return sum > 65535 ? (sum - 65535) : sum;
	}
}
