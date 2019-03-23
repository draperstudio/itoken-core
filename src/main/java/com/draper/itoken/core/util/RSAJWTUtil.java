package com.draper.itoken.core.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author draper_hxy
 */
public class RSAJWTUtil {

    private RSAJWTUtil() {
    }

    public static String build(Claims claims, PrivateKey privateKey) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public static Jws<Claims> parse(String jwts, PublicKey publicKey) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(jwts);
        return claimsJws;
    }

}
