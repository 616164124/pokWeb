package com.pokweb.common.utils.sm2;

import org.bouncycastle.util.encoders.Hex;

public class GenerateJym {

	public static String getJym(String content) {
		String ret="";
		byte[] md = new byte[32];
		SM3Digest sm3 = new SM3Digest();
		byte[] msg = content.getBytes();
	    sm3.update(msg, 0, msg.length);
	    sm3.doFinal(md, 0);
	    String digest = new String(Hex.encode(md));
	    
	    int rd=(int)(Math.random()*8);
        
        String fm=digest.substring(rd*8,(rd+1)*8);
        
        String jym=sm3.getCRCValues(md);
        
        ret=fm+jym;
		return ret;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String content="测试1234hello";
		
		String jym=getJym(content);
		System.out.println(jym);
	}

}
