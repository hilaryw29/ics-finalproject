package icsFinalProject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5{
	private static final String ALGORITHM ="MD5";
	
	public static String getMD5(String fileName) {
		try {
			byte[] read = Files.readAllBytes(Paths.get(fileName));
			byte[] hash = MessageDigest.getInstance(ALGORITHM).digest(read);
			StringBuffer value = new StringBuffer();
		    for (int i = 0; i < hash.length; i++) {
		        value.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
		    }
			return value.toString();
		}catch (NoSuchAlgorithmException e) {
		}catch (IOException e) {
		}
		return "0";
	}
	
	public static boolean compareMD5(String fileName, String md5) {
		return md5.equals(getMD5(fileName)) ? true : false;
	}
	
	public static byte[] getMd5(String pin) {
		try {
			 byte[] hash = MessageDigest.getInstance(ALGORITHM).digest(pin.getBytes(StandardCharsets.UTF_8));
			 StringBuffer value = new StringBuffer();
			 for (int i = 0; i < hash.length; i++) {
				 value.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
			 }
			 System.out.println(value.toString());
			return value.toString().getBytes(StandardCharsets.UTF_8);
		}catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}