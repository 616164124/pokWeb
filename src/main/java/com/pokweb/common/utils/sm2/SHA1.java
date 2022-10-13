package com.pokweb.common.utils.sm2;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class SHA1 {

	public static String digest(String content) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			messageDigest.update(content.getBytes("UTF-8"));
			byte[] digestBytes = messageDigest.digest();
			return DatatypeConverter.printBase64Binary(digestBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
