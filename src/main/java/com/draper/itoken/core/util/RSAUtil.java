package com.draper.itoken.core.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.bouncycastle.util.encoders.Base64;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.KeyPair;


/**
 * @author draper_hxy
 */
public class RSAUtil {

    private static byte[] publicBytes = null;
    private static byte[] privateKey = null;

    private RSAUtil() {
    }

    public static byte[] getPublicBytes() {
        if (publicBytes == null) {

        }
        return publicBytes;
    }

    public static byte[] getPrivateKey() {
        if (privateKey == null) {

        }
        return privateKey;
    }

    private static byte[] readPublicKey() {
        try {
            FileChannel fileChannel = new FileInputStream("publicKey").getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1200);
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            int data = -1;
            while (data != 0) {
                byteBuffer.get();
            }


        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }


    // 如果没有可以自行生成
    public static void main(String[] args) {
        generateRSAKeyPair();
    }

    private static void generateRSAKeyPair() {
        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

        try (FileChannel fileChannel = new FileOutputStream("privateKey.key").getChannel()) {
            byte[] privateBytes = keyPair.getPrivate().getEncoded();
            byte[] base64Bytes = Base64.encode(privateBytes);
            ByteBuffer byteBuffer = ByteBuffer.allocate(base64Bytes.length);
            byteBuffer.put(base64Bytes);
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        try (FileChannel fileChannel = new FileOutputStream("publicKey.key").getChannel()) {
            byte[] publicBytes = keyPair.getPublic().getEncoded();
            byte[] base64Bytes = Base64.encode(publicBytes);
            ByteBuffer byteBuffer = ByteBuffer.allocate(base64Bytes.length);
            byteBuffer.put(base64Bytes);
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


}
