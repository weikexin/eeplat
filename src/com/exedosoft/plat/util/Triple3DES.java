package com.exedosoft.plat.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;



/**
 * @author dushu
 *
 */
public class Triple3DES {
	private static final int BUFFER_SIZE = 8;
	private static final String CIPHER_ALGORITHM = "DESede/ECB/NoPadding";
	private static final String KEY_ALGORITHM = "DESede";
	private Cipher cipher;
	private SecretKey secureKey;
	
	/**
	 * 
	 * @param key 密钥，必须为24位
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws InvalidKeyException
	 */
	public Triple3DES(String key)
	throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException {
		cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		
		byte[] binaryKey = key.getBytes();
		DESedeKeySpec dks = new DESedeKeySpec(binaryKey);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		secureKey = keyFactory.generateSecret(dks);
	}
	
	
	/**
	 * 使用3DES算法加密文件
	 * @param sourceFile 源文件全路径
	 * @param saveFile  目标文件全路径
	 * @throws Exception
	 */
	public void encrypt(String sourceFile,String saveFile) throws Exception {
		
		FileInputStream inf = new FileInputStream(sourceFile);
		FileOutputStream outf = new FileOutputStream(saveFile);
		
		cipher.init(Cipher.ENCRYPT_MODE, secureKey);
		CipherOutputStream out = new CipherOutputStream(outf, cipher);
		
		byte[] buf = new byte[BUFFER_SIZE];
		int numRead = inf.read(buf);
		while(numRead != -1){
			if(numRead < 8){
				for(int i=numRead;i<BUFFER_SIZE;i++){
					buf[i] = 0x0;
				}
			}
			
			out.write(buf, 0, BUFFER_SIZE);
			numRead = inf.read(buf);
		}
		
		inf.close();
		out.close();
	}
	/**
	 * 使用3DES算法解密文件
	 * @param sourceFile 源文件全路径
	 * @param saveFile  目标文件全路径
	 * @throws Exception
	 */
	public void decrypt(String sourceFile,String saveFile) throws Exception {
		FileInputStream inf = new FileInputStream(sourceFile);
		FileOutputStream outf = new FileOutputStream(saveFile);
		
	
		cipher.init(Cipher.DECRYPT_MODE, secureKey);
		//CipherOutputStream out = new CipherOutputStream(outf, cipher);
		
		byte[] buf = new byte[BUFFER_SIZE];
		int numRead = inf.read(buf);
		while(numRead != -1){
			byte[] res = cipher.doFinal(buf);
			int lastIndex = BUFFER_SIZE;
			for(int i=BUFFER_SIZE-1;i>=0;i--){
				if(res[i] == 0x0){
					lastIndex--;
				}else{
					break;
				}
			}
			outf.write(res, 0, lastIndex);
			
			//out.write(buf, 0, numRead);
			numRead = inf.read(buf);
		}
		
		inf.close();
		outf.close();
	}
	
	public static void main(String[] args) throws Exception {
		Triple3DES sample = new Triple3DES("0123456789abcdef12345678");
		//sample.encrypt("D:/cislogs/hedui/TCYXTRCSPS28032011000011.REQ", "D:/cislogs/hedui/TCYXTRCSPS28032011000011.REQ.ENC");
		sample.decrypt("D:/CISTTRCSPS201107310000021.ACK.ENC", "D:/b.txt");
	}
}

