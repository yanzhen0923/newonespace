package yz.bean;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
	
	public static String md5EncryptTo32Bits(String p) throws NoSuchAlgorithmException {
		
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(p.getBytes());
		
		BigInteger bigInt = new BigInteger(1,m.digest());
		
		//����32λmd5ֵ
		return bigInt.toString(16);
	}
}
