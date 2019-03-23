package com.draper.itoken.core.util;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


import javax.crypto.SecretKey;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


/**
 * @author draper_hxy
 */
public class RSAUtil {

    private static byte[] publicBytes = null;
    private static byte[] privateKey = null;

    private RSAUtil() {
    }

    // 放在内存中只读，不可写
    public static byte[] getPublicBytes() {
        if (publicBytes == null) {
            publicBytes = readPublicKey();
        }
        return publicBytes;
    }

    public static PrivateKey getPrivateKey(byte[] privateKeyBytes) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            return keyf.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PublicKey getPublicKey(byte[] publicKeyBytes){
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            return keyf.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getPrivateBytes() {
        if (privateKey == null) {
            privateKey = readPrivateKey();
        }
        return privateKey;
    }

    private static byte[] readPublicKey() {
        byte[] bytes = null;
        try {
            File file = new File("publicKey.key");
            // 文件不存在则会生成一对秘钥
            if (!file.exists()) {
                generateRSAKeyPair();
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return bytes;
    }

    private static byte[] readPrivateKey() {
        byte[] bytes = null;
        try {
            File file = new File("privateKey.key");
            if (!file.exists()) {
                generateRSAKeyPair();
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return bytes;
    }

    private static void generateRSAKeyPair() {
        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

        try {
            byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
            FileOutputStream fileOutputStream = new FileOutputStream("publicKey.key");
            fileOutputStream.write(publicKeyBytes);
            fileOutputStream.close();

            byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
            fileOutputStream = new FileOutputStream("privateKey.key");
            fileOutputStream.write(privateKeyBytes);
            fileOutputStream.close();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


}
