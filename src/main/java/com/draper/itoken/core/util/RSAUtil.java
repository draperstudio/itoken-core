package com.draper.itoken.core.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


import java.io.*;
import java.security.KeyPair;


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

    public static byte[] getPrivateKey() {
        if (privateKey == null) {
            privateKey = readPrivateKey();
        }
        return privateKey;
    }

    private static byte[] readPublicKey() {
        try {
            File file = new File("publicKey.key");
            // 文件不存在则会生成一对秘钥
            if (!file.exists()) {
                generateRSAKeyPair();
            }
            FileReader fileReader = new FileReader("publicKey.key");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str = null;
            String result = null;
            while ((str = bufferedReader.readLine()) != null) {
                result = result + str;
            }
            return result.getBytes();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    private static byte[] readPrivateKey() {
        try {
            File file = new File("privateKey.key");
            if (!file.exists()) {
                generateRSAKeyPair();
            }
            FileReader fileReader = new FileReader("privateKey.key");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str = null;
            String result = null;
            while ((str = bufferedReader.readLine()) != null) {
                result = result + str;
            }
            return result.getBytes();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    private static void generateRSAKeyPair() {
        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

        try {
            byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
            System.out.println(publicKeyBytes.length);
            FileWriter fileWriter = new FileWriter("publicKey.key");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(new String(publicKeyBytes));
            bufferedWriter.flush();
            bufferedWriter.close();

            byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
            System.out.println(privateKeyBytes.length);
            fileWriter = new FileWriter("privateKey.key");
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(new String(privateKeyBytes));
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


}
