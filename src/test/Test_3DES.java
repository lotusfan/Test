package test;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Test_3DES {

	private void mian() {

		// TODO Auto-generated method stub

	}

	public static String encryptKey(String mainKey, String plainKey) {

		String encryptKey = "";
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			String Algorithm = "DESede/ECB/NoPadding";
			byte[] hb = hex2byte(mainKey.getBytes());
			byte[] k = new byte[24];
			System.arraycopy(hb, 0, k, 0, 16);
			System.arraycopy(hb, 0, k, 16, 8);
			SecretKey deskey = new SecretKeySpec(k, Algorithm);
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			encryptKey = byte2hex(c1.doFinal(hex2byte(plainKey.getBytes())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptKey;
	}
}
