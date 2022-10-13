package com.pokweb.common.utils.sm2;

import org.bouncycastle.util.encoders.Hex;

import java.util.HashSet;
import java.util.zip.CRC32;


public class SM3Digest {

    /** SM3值的长度 */
    private static final int BYTE_LENGTH = 32;

    /** SM3分组长度 */
    private static final int BLOCK_LENGTH = 64;

    /** 缓冲区长度 */
    private static final int BUFFER_LENGTH = BLOCK_LENGTH * 1;

    /** 缓冲区 */
    private byte[] xBuf = new byte[BUFFER_LENGTH];

    /** 缓冲区偏移量 */
    private int xBufOff;

    /** 初始向量 */
    private byte[] V = SM3.iv.clone();

    private int cntBlock = 0;

    public SM3Digest() {
    }

    public SM3Digest(SM3Digest t) {
        System.arraycopy(t.xBuf, 0, this.xBuf, 0, t.xBuf.length);
        this.xBufOff = t.xBufOff;
        System.arraycopy(t.V, 0, this.V, 0, t.V.length);
    }

    /**
     * SM3结果输出
     * 
     * @param out 保存SM3结构的缓冲区
     * @param outOff 缓冲区偏移量
     * @return
     */
    public int doFinal(byte[] out, int outOff) {
        byte[] tmp = doFinal();
        System.arraycopy(tmp, 0, out, 0, tmp.length);
        return BYTE_LENGTH;
    }

    public void reset() {
        xBufOff = 0;
        cntBlock = 0;
        V = SM3.iv.clone();
    }

    /**
     * 明文输入
     * 
     * @param in 明文输入缓冲区
     * @param inOff 缓冲区偏移量
     * @param len 明文长度
     */
    public void update(byte[] in, int inOff, int len) {
        int partLen = BUFFER_LENGTH - xBufOff;
        int inputLen = len;
        int dPos = inOff;
        if (partLen < inputLen) {
            System.arraycopy(in, dPos, xBuf, xBufOff, partLen);
            inputLen -= partLen;
            dPos += partLen;
            doUpdate();
            while (inputLen > BUFFER_LENGTH) {
                System.arraycopy(in, dPos, xBuf, 0, BUFFER_LENGTH);
                inputLen -= BUFFER_LENGTH;
                dPos += BUFFER_LENGTH;
                doUpdate();
            }
        }

        System.arraycopy(in, dPos, xBuf, xBufOff, inputLen);
        xBufOff += inputLen;
    }

    private void doUpdate() {
        byte[] B = new byte[BLOCK_LENGTH];
        for (int i = 0; i < BUFFER_LENGTH; i += BLOCK_LENGTH) {
            System.arraycopy(xBuf, i, B, 0, B.length);
            doHash(B);
        }
        xBufOff = 0;
    }

    private void doHash(byte[] B) {
        byte[] tmp = SM3.CF(V, B);
        System.arraycopy(tmp, 0, V, 0, V.length);
        cntBlock++;
    }

    private byte[] doFinal() {
        byte[] B = new byte[BLOCK_LENGTH];
        byte[] buffer = new byte[xBufOff];
        System.arraycopy(xBuf, 0, buffer, 0, buffer.length);
        byte[] tmp = SM3.padding(buffer, cntBlock);
        for (int i = 0; i < tmp.length; i += BLOCK_LENGTH) {
            System.arraycopy(tmp, i, B, 0, B.length);
            doHash(B);
        }
        return V;
    }

    public void update(byte in) {
        byte[] buffer = new byte[] { in };
        update(buffer, 0, 1);
    }

    public int getDigestSize() {
        return BYTE_LENGTH;
    }
    
    public String byte2int(byte[] bytes) {
    	String ret="";
    	byte[] oldArray= new byte[]{1,4};
		if (oldArray.length < 4) {
			byte[] b = new byte[] { 0, 0, 0, 0 };
			for (int i = 0; i < oldArray.length; i++) {
				b[i + 4 - oldArray.length] = oldArray[i];
			}
			oldArray= b;
		}
     //   ret=ByteBuffer.wrap(oldArray).getInt();

    	return ret;
    }
    
    public static int bytesToInt(byte[] src,int offset)   
    {   
         int value;    
         value = (int) ((src[offset] & 0xFF)            
            | ((src[offset+1] & 0xFF)<<8)   
            | ((src[offset+2] & 0xFF)<<16)   
            | ((src[offset+3] & 0xFF)<<24));  
         return value;

   }
    
    public static int[] byteToInt(byte[] btarr) {
        if (btarr.length % 4 != 0) {
            return null;
        }
        int[] intarr = new int[btarr.length / 4];

        int i1, i2, i3, i4;
        // j循环int	k循环byte数组
        for (int j = 0, k = 0; j < intarr.length; j++, k += 4) {
            i1 = btarr[k];
            i2 = btarr[k + 1];
            i3 = btarr[k + 2];
            i4 = btarr[k + 3];

            if (i1 < 0) {
                i1 += 256;
            }
            if (i2 < 0) {
                i2 += 256;
            }
            if (i3 < 0) {
                i3 += 256;
            }
            if (i4 < 0) {
                i4 += 256;
            }
            // 保存Int数据类型转换
            intarr[j] = (i1 << 24) + (i2 << 16) + (i3 << 8) + (i4 << 0);

        }
        return intarr;
    }
    
    public static int[] byteToInt2(byte[] btarr) {
        if (btarr.length % 2 != 0) {
            return null;
        }
        int[] intarr = new int[btarr.length / 2];

        int i1, i2, i3, i4;
        // j循环int	k循环byte数组
        for (int j = 0, k = 0; j < intarr.length; j++, k += 2) {
            i1 = btarr[k];
            i2 = btarr[k + 1];
          //  i3 = btarr[k + 2];
          //  i4 = btarr[k + 3];

            if (i1 < 0) {
                i1 += 256;
            }
            if (i2 < 0) {
                i2 += 256;
            }
//            if (i3 < 0) {
//                i3 += 256;
//            }
//            if (i4 < 0) {
//                i4 += 256;
//            }
            // 保存Int数据类型转换
            intarr[j] = (i1 << 24) + (i2 << 16);//+ (i3 << 8) + (i4 << 0);

        }
        return intarr;
    }

    private static String byteToNumString(byte b) {
    	   int _b = b;
    	   if (_b < 0) {
    	      _b = 256 + _b;
    	   }
    	   return String.valueOf(_b);
    	}
    
    private static byte[] getData(byte[] aa) {
        byte[] bb = getCrc16(aa);
        byte[] cc = new byte[aa.length+bb.length];
        System.arraycopy(aa,0,cc,0,aa.length);
        System.arraycopy(bb,0,cc,aa.length,bb.length);
        return cc;
    }

    
    private static byte[] intToBytes(int value)  {
        byte[] src = new byte[2];
        src[1] =  (byte) ((value>>8) & 0xFF);
        src[0] =  (byte) (value & 0xFF);
        return src;
    }

    private static byte[] getCrc16(byte[] arr_buff) {
        int len = arr_buff.length;
 
        // 预置 1 个 16 位的寄存器为十六进制FFFF, 称此寄存器为 CRC寄存器。
        int crc = 0xFFFF;
        int i, j;
        for (i = 0; i < len; i++) {
            // 把第一个 8 位二进制数据 与 16 位的 CRC寄存器的低 8 位相异或, 把结果放于 CRC寄存器
            crc = ((crc & 0xFF00) | (crc & 0x00FF) ^ (arr_buff[i] & 0xFF));
            for (j = 0; j < 8; j++) {
                // 把 CRC 寄存器的内容右移一位( 朝低位)用 0 填补最高位, 并检查右移后的移出位
                if ((crc & 0x0001) > 0) {
                    // 如果移出位为 1, CRC寄存器与多项式A001进行异或
                    crc = crc >> 1;
                    crc = crc ^ 0xA001;
                } else
                    // 如果移出位为 0,再次右移一位
                    crc = crc >> 1;
            }
        }
        return intToBytes(crc);
    }
    
    public static String byteTo16String(byte[] data) {
        StringBuffer buffer = new StringBuffer();
        for (byte b : data) {
            buffer.append(byteTo16String(b));
        }
        return buffer.toString();
    }

    
    public static String byteTo16String(byte b) {
        StringBuffer buffer = new StringBuffer();
        int aa = (int)b;
        if (aa<0) {
            buffer.append(Integer.toString(aa+256, 16)+" ");
        }else if (aa==0) {
            buffer.append("00 ");
        }else if (aa>0 && aa<=15) {
            buffer.append("0"+Integer.toString(aa, 16)+" ");
        }else if (aa>15) {
            buffer.append(Integer.toString(aa, 16)+" ");
        }
        return buffer.toString();
    }
    
    public static String getCRCValues(byte[] b){
        CRC32 c = new CRC32();
        c.reset();//Resets CRC-32 to initial value.
        //将数据丢入CRC32解码器
        c.update(b, 0, b.length);
        //获取CRC32 的值  默认返回值类型为long 用于保证返回值是一个正数
        int value = (int) c.getValue();
        //转为16进制
        String value_string = Integer.toHexString(value);
        return value_string;
    }



    public static void main(String[] args) {
        byte[] md = new byte[32];
        
        HashSet set=new HashSet();
        for(int i=0;i<10000000;i++) {
        	String zf="ererfeiisgod"+(i+1);
        byte[] msg1 = zf.getBytes();
        SM3Digest sm3 = new SM3Digest();
        sm3.update(msg1, 0, msg1.length);
        sm3.doFinal(md, 0);
        String s = new String(Hex.encode(md));
        System.out.println(s);
//        int[] t=byteToInt2(md);
//       for(int i=0;i<t.length;i++) {
//    	   System.out.println((i+1)+":"+t[i]);
//       }
//        byte[] b=getData(md);
//        String jym=byteTo16String(b).replaceAll(" ", "");
//        System.out.println("jym:"+jym);
        
        int rd=(int)(Math.random()*8);
        
        System.out.println(rd);
        
        String fm=s.substring(rd*8,(rd+1)*8);
        
        
        String jym=getCRCValues(md);
        System.out.println("fm="+fm+",jym:"+jym);
        set.add(fm+jym);
        }
        
        System.out.println(set.size());
    }
}
