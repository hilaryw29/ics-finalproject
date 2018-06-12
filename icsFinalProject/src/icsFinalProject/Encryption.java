package icsFinalProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {
	private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
	private static final String ALGORITHM1 = "AES";
	
	public static void encrypt(Serializable o, OutputStream out, byte[] password) throws IOException {
	    try {
	        SecretKeySpec key = new SecretKeySpec(password, ALGORITHM1);
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.ENCRYPT_MODE, key);
	        SealedObject sealedObject = new SealedObject(o, cipher);
	        CipherOutputStream chipherOut = new CipherOutputStream(out, cipher);
	        ObjectOutputStream objectOut = new ObjectOutputStream(chipherOut);
	        objectOut.writeObject(sealedObject);
	        objectOut.close();
	    } catch (IllegalBlockSizeException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
	    	System.out.println("en");
	    }
	}

	public static TransactionList decrypt(InputStream in, byte[] password) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		SecretKeySpec key = new SecretKeySpec(password, ALGORITHM1);
	    Cipher cipher = Cipher.getInstance(ALGORITHM);
	    cipher.init(Cipher.DECRYPT_MODE, key);
	    CipherInputStream cipherIn = new CipherInputStream(in, cipher);
	    try {
	    	ObjectInputStream objectIn = new ObjectInputStream(cipherIn);
	    SealedObject sealedObject;
	        sealedObject = (SealedObject) objectIn.readObject();
	        objectIn.close();
	        return (TransactionList) sealedObject.getObject(cipher);
	    } catch (FileNotFoundException e) {
	    	System.out.println("123");
	    	return null;
	    } catch (ClassNotFoundException | IllegalBlockSizeException | BadPaddingException e) {
	        System.out.println("en2");
	    	return null;
	    }
	}
}
