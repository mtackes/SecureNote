package edu.mtackes.securenote.util;

import com.sun.istack.Nullable;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

/**
 * Created by mtackes on 11/19/15.
 */
public class Crypto {
    // TODO: Pull algorithms into properties file?
    static String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    static String SECRET_KEY_ALGORITHM = "AES";
    static String MESSAGE_DIGEST_ALGORITHM = "MD5";

    @Nullable
    public static byte[] encrypt(byte[] content, byte[] initializationVector, String password) {
        try {

            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec ivSpec = new IvParameterSpec(initializationVector);

            byte[] passwordHash = hashPassword(password);
            SecretKeySpec secretKey = new SecretKeySpec(passwordHash, SECRET_KEY_ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

            return cipher.doFinal(content, 0, content.length);

        // TODO: Add logging to exceptions
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Nullable
    public static byte[] decrypt(byte[] encryptedContent, byte[] initializationVector, String password) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec ivSpec = new IvParameterSpec(initializationVector);

            byte[] passwordHash = hashPassword(password);
            SecretKeySpec secretKey = new SecretKeySpec(passwordHash, SECRET_KEY_ALGORITHM);

            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            return cipher.doFinal(encryptedContent, 0, encryptedContent.length);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Nullable
    public static byte[] hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance(MESSAGE_DIGEST_ALGORITHM);
            digest.update(password.getBytes());

            return digest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
