package com.tyyd.zhuhao.rsa;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/**
 * @author <a href="mailto:zhuhao@189read.com.">���</a>
 * 2015��11��4������11:36:23
 * @version 1.0
 */
public class rsaTest {

	private static String src = "imooc security rsa";
	
	public static void main(String[] args) {
		jdkRSA();
	}
	
	@SuppressWarnings("static-access")
	public static void jdkRSA() {
		//1.��ʼ����Կ
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(512);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPublicKey rsaPublicKey = (RSAPublicKey)keyPair.getPublic();
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)keyPair.getPrivate();
			System.out.println("Public key :" + Base64.encodeBase64String(rsaPublicKey.getEncoded()));
			System.out.println("Private key :" + Base64.encodeBase64String(rsaPrivateKey.getEncoded()));
			
			//2.˽Կ���ܣ���Կ���ܡ�������
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("˽Կ���ܣ���Կ���ܡ�������:" + Base64.encodeBase64String(result));
			
			//3.˽Կ���ܣ���Կ���ܡ�������
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			cipher = cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			result = cipher.doFinal(result);
			System.out.println("˽Կ���ܣ���Կ���ܡ�������:" + new String(result));
			
			//4.��Կ���ܣ�˽Կ���ܡ�������
			x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("RSA");
			publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			result = cipher.doFinal(src.getBytes());
			System.out.println("��Կ���ܣ�˽Կ���ܡ�������:" + Base64.encodeBase64String(result));
			
			//5.��Կ���ܣ�˽Կ���ܡ�������
			pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
			keyFactory = KeyFactory.getInstance("RSA");
			privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			result = cipher.doFinal(result);
			System.out.println("��Կ���ܣ�˽Կ���ܡ�������:" + new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
