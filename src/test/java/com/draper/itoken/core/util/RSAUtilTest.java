package com.draper.itoken.core.util;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;

/**
 * @author draper_hxy
 */
public class RSAUtilTest {

    @Test
    public void getPublicBytes() {
        System.out.println(new String(Base64.encode(RSAUtil.getPublicBytes())));

    }

    @Test
    public void getPrivateKey() {
        System.out.println(new String(Base64.encode(RSAUtil.getPrivateBytes())));
    }

}