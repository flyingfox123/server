package com.manyi.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Md5Util {
	/**
	 * 默认的密码字符串组合，apache校验下载的文件的正确性用的就是默认的这个组合
	 */
	private static final char HEX_DIGISTS[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	protected static MessageDigest messagedigest = null;

	public static final  int DOUBLE = 2;
	public static final  int NUM_256 = 256;
	public static final  int NUM_16 = 16;
	public static final  int  NUM_4 = 4 ;

	private static final Logger logger = LoggerFactory.getLogger(Md5Util.class);

	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {

			logger.error(Md5Util.class.getName()
					+ "初始化失败，MessageDigest不支持MD5Util。",e);
		}
	}
	
	/**
	 * md5文件加密校验
	 * @param localFile 本地文件或文件夹
	 * @param ftpFile	远程文件或文件架(也可以是本地文件)
	 * @throws java.io.IOException
	 */
	public static boolean Md5FileCompare(String localFile, String ftpFile) throws IOException {

	    File localBig = new File(localFile);
	    File ftpBig = new File(ftpFile);
	    String localmd5=getFileMD5String(localBig);
	    String ftpmd5 =getFileMD5String(ftpBig);
	    return localmd5.equals(ftpmd5);
	}
	
	/**
	 * 文件加密与加密串校验
	 * @param downFile 下载的Ftp文件
	 * @param md5Code  发布时生成的文件md5值
	 * @throws java.io.IOException
	 */
	public static boolean FileCompareMd5(String downFile, String md5Code) throws IOException {

		File localBig = new File(downFile);
		String localmd5=getFileMD5String(localBig);
		return localmd5.equals(md5Code);
	}
	
	/**
	 * 文件签名
	 * 
	 * @param file
	 * @return
	 * @throws java.io.IOException
	 */
	public static String getFileMD5String(File file) throws IOException {
		String str = "";
		FileInputStream input = new FileInputStream(file);
		FileChannel  channel= input.getChannel();
		MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0,
				file.length());
		messagedigest.update(byteBuffer);
		str = bufferToHex(messagedigest.digest());
		input.close();
		channel.close();
		return str;
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int mount1, int mount2) {
		StringBuilder stringbuffer = new StringBuilder(DOUBLE * mount2);
		int mount3 = mount1 + mount2;
		for (int l = mount1; l < mount3; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte byteValue, StringBuilder stringbuffer) {
		char charValue1 = HEX_DIGISTS[(byteValue & 0xf0) >> NUM_4];
		char charValue2 = HEX_DIGISTS[byteValue & 0xf];
		stringbuffer.append(charValue1);
		stringbuffer.append(charValue2);
	}
	
	/**
	 * 根据MD5算法转换字符串
	 * 
	 * @param oriStr
	 *            要转换的字符串
	 * @throws java.security.NoSuchAlgorithmException
	 *             函数抛出的错误，表示不支持该种算法。
	 * @return String 转换结果字符串
	 */
	public static String getMD5Str(String oriStr) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = null;
		messageDigest = MessageDigest.getInstance("MD5");
		String tarStr = byteArrayToHexString(messageDigest.digest(oriStr.getBytes()));
		return tarStr;
	}
	
	/**
	 * 将字节数组转换成16进制字符串。
	 * 
	 * @param bytes
	 * @return String 16进制字符串
	 */
	private static String byteArrayToHexString(byte[] bytes) {
		StringBuilder resultSb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			resultSb.append(byteToHexString(bytes[i]));
		}
		return resultSb.toString();
	}
	
	/**
	 * 将一个字节转换成16进制字符串。
	 */
	private static String byteToHexString(byte byteValue) {
		int number = byteValue;
		if (number < 0)
			number = NUM_256 + number;
		int digest1 = number / NUM_16;
		int digest2 = number % NUM_16;
		return HEX_DIGISTS[digest1] +""+ HEX_DIGISTS[digest2];
	}

}
