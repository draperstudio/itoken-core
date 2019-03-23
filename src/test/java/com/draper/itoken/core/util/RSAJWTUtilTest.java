package com.draper.itoken.core.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.impl.DefaultClaims;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;


/**
 * @author draper_hxy
 */
public class RSAJWTUtilTest {

    @Test
    public void testBuild() throws IOException {
        Claims claims = new DefaultClaims();
        claims.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 365 * 100));
        claims.setSubject("draper");
        String jwts = RSAJWTUtil.build(claims,RSAUtil.getPrivateKey(RSAUtil.getPrivateBytes()));
        System.out.println(jwts);
    }

    @Test
    public void testVe¢rify() {
        String jwts = "eyJhbGciOiJSUzI1NiJ9.eyJleHAiOjE1NTQ0NDkyMzEsInN1YiI6ImRyYXBlciJ9.e2nUFViRcHZKTZDK6AT7l3RISEVKQrw9eLhqGbAR7tU62q_nJZL0P4HBkbWlO48_i3qnOiiryXdMKgr6RDThdBO-l_JbUKbk2l3Nws8_wy0wk3STKFu9WlVTIqh7e9vPdouv2b97-1JyR1SBq0ZkppQKBniVKtH32vQw1FAwIWL93mmhb0-XQGo87CGun81_k1tzCpWQ9tAqBgt25HU_SQIA3GyIzkb9t7dT8qeMip12Ed6iaYZzHM0bQxhEBLQvdPW3IMLM1mkAff2ppN_qgfTbKRD9Raq6uG0pHUzKwGAN_uvKaAEB90M9QT8a3-0FKCU7yDLZsNUDHDcCzO7QlQ";
        Jws<Claims> claimsJws = RSAJWTUtil.parse(jwts,RSAUtil.getPublicKey(RSAUtil.getPublicBytes()));
        System.out.println(claimsJws.getBody().getSubject());
        System.out.println(claimsJws.getBody().getExpiration());
    }

}