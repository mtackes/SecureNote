package edu.mtackes.securenote;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by mtackes on 11/5/15.
 */
public class Experiments {
    public static void main(String[] args) {
        superShort();
        shortVersion();
        longVersion();
    }

    public static void superShort() {


        UUID uuid = UUID.fromString("903d9f69-0bf4-44a8-924d-d14780852741");

        String keyString = "this-is-my-password";
        byte[] keyBytes = {};

        String badString = "not the password!";
        byte[] badBytes = {};

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(keyString.getBytes());
            keyBytes = digest.digest();

            MessageDigest badDigest = MessageDigest.getInstance("MD5");
            badDigest.update(badString.getBytes());
            badBytes = badDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE * 2 / Byte.SIZE);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(Long.SIZE / Byte.SIZE, uuid.getLeastSignificantBits());

        byte[] byteArr = buffer.array();

//        System.out.println(DatatypeConverter.printHexBinary(byteArr));
//        System.out.println(byteArr.length * Byte.SIZE);

        String testInput = "abcdefghijklmnop";//"This is just a short string to be encrypted in this example code.";

        try {

            byte[] key = byteArr;
            byte[] dataToSend = testInput.getBytes();

            System.out.println(new String(dataToSend));

            Cipher c = Cipher.getInstance("AES");
            SecretKeySpec k = new SecretKeySpec(key, "AES");
            c.init(Cipher.ENCRYPT_MODE, k);
            byte[] encryptedData = c.doFinal(dataToSend);

//            System.out.println(new String(encryptedData));
            for (int i = 0; i < encryptedData.length; i++) {
                System.out.printf("%02X", encryptedData[i]);
            }
            System.out.println();


            c.init(Cipher.DECRYPT_MODE, k);
            byte[] data = c.doFinal(encryptedData);

            System.out.println(new String(data));


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // Consider this a "wrong password" exception
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
//        } catch (InvalidAlgorithmParameterException e) {
//            e.printStackTrace();
//        } catch (ShortBufferException e) {
//            e.printStackTrace();
        }


    }

    public static void shortVersion() {


        UUID uuid = UUID.fromString("903d9f69-0bf4-44a8-924d-d14780852741");

        String keyString = "this-is-my-password";
        byte[] keyBytes = {};

        String badString = "not the password!";
        byte[] badBytes = {};

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(keyString.getBytes());
            keyBytes = digest.digest();

            MessageDigest badDigest = MessageDigest.getInstance("MD5");
            badDigest.update(badString.getBytes());
            badBytes = badDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE * 2 / Byte.SIZE);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(Long.SIZE / Byte.SIZE, uuid.getLeastSignificantBits());

        byte[] byteArr = buffer.array();

//        System.out.println(DatatypeConverter.printHexBinary(byteArr));
//        System.out.println(byteArr.length * Byte.SIZE);

        String testInput = "abcdefghijklmnop";//"This is just a short string to be encrypted in this example code.";

        try {

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivSpec = new IvParameterSpec(byteArr);
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
            byte[] encrypted = cipher.doFinal(testInput.getBytes(), 0, testInput.length());
//            enc_len += cipher.doFinal(encrypted, enc_len);

            String encString = new String(encrypted, Charset.forName("UTF-8"));
//            System.out.println("enc_len = " + enc_len);
            System.out.println("encrypted.length = " + encrypted.length);
            for (int i = 0; i < encrypted.length; i++) {
                System.out.printf("%02X", encrypted[i]);
            }
            System.out.println();
            System.out.println("encString.length() = " + encString.length());
            System.out.println("encString.getBytes().length = " + encString.getBytes().length);

            SecretKeySpec badKey = new SecretKeySpec(badBytes, "AES");

            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            byte[] decrypted = cipher.doFinal(encrypted, 0, encrypted.length);
//            dec_len += cipher.doFinal(decrypted, dec_len);

            System.out.println(new String(decrypted, 0, decrypted.length));
            System.out.println(new String(decrypted)/*.replaceAll("\0+$", "")*/);
//            System.out.println(dec_len);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // Consider this a "wrong password" exception
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
//        } catch (ShortBufferException e) {
//            e.printStackTrace();
        }
    }

    public static void longVersion() {

        UUID uuid = UUID.fromString("903d9f69-0bf4-44a8-924d-d14780852741");

        String keyString = "this-is-my-password";
        byte[] keyBytes = {};

        String badString = "not the password!";
        byte[] badBytes = {};

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(keyString.getBytes());
            keyBytes = digest.digest();

            MessageDigest badDigest = MessageDigest.getInstance("MD5");
            badDigest.update(badString.getBytes());
            badBytes = badDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE * 2 / Byte.SIZE);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(Long.SIZE / Byte.SIZE, uuid.getLeastSignificantBits());

        byte[] byteArr = buffer.array();

//        System.out.println(DatatypeConverter.printHexBinary(byteArr));
//        System.out.println(byteArr.length * Byte.SIZE);

        String testInput = "abcdefghijklmnop";//"This is just a short string to be encrypted in this example code.";

        try {

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivSpec = new IvParameterSpec(byteArr);
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
            byte[] encrypted = new byte[cipher.getOutputSize(testInput.length())];

            int enc_len = cipher.doFinal(testInput.getBytes(), 0, testInput.length(), encrypted, 0);
//            enc_len += cipher.doFinal(encrypted, enc_len);

            String encString = new String(encrypted, Charset.forName("UTF-8"));
            System.out.println("enc_len = " + enc_len);
            System.out.println("encrypted.length = " + encrypted.length);
            for (int i = 0; i < encrypted.length; i++) {
                System.out.printf("%02X", encrypted[i]);
            }
            System.out.println();
            System.out.println("encString.length() = " + encString.length());
            System.out.println("encString.getBytes().length = " + encString.getBytes().length);

            SecretKeySpec badKey = new SecretKeySpec(badBytes, "AES");

            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            byte[] decrypted = new byte[cipher.getOutputSize(enc_len)];
            int dec_len = cipher.doFinal(encrypted, 0, enc_len, decrypted, 0);
//            dec_len += cipher.doFinal(decrypted, dec_len);

            System.out.println(new String(decrypted, 0, dec_len));
            System.out.println(new String(decrypted)/*.replaceAll("\0+$", "")*/);
            System.out.println(dec_len);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // Consider this a "wrong password" exception
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (ShortBufferException e) {
            e.printStackTrace();
        }
    }
}

/*
            Cipher encCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec key = new SecretKeySpec(byteArr, "AES");
            encCipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = new byte[encCipher.getOutputSize(testInput.length())];

            int enc_len = encCipher.update(testInput.getBytes(), 0, testInput.length(), encrypted, 0);
            enc_len += encCipher.doFinal(encrypted, enc_len);

            System.out.println(encrypted);

            Cipher decCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            decCipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypted = new byte[decCipher.getOutputSize(enc_len)];
            int dec_len = decCipher.update(encrypted, 0, enc_len, decrypted, 0);
            dec_len += decCipher.doFinal(decrypted, dec_len);

            System.out.println(decrypted);
*/
