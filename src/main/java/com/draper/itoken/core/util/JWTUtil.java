package com.draper.itoken.core.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * @author draper_hxy
 */
public class JWTUtil {
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    private static final Key key = Keys.secretKeyFor(signatureAlgorithm);

    public static String buildJwts(Map<String, Object> claims, Long expireTime) {
        String jwts = Jwts.builder()
                .setClaims(claims)
                //在这里其实可以覆写 key 和重新选择算法
                .signWith(key, signatureAlgorithm)
                .setExpiration(new Date(expireTime))
                .compact();
        return jwts;
    }

    public static boolean verifyJwts(String jwts) {
        Jws<Claims> jws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwts);
        return jws.getBody().getExpiration().getTime() > System.currentTimeMillis();
    }

    public static Object get(String name, String jwts) {
        Jws<Claims> jws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwts);
        return jws.getBody().get(name);
    }

    public static Key getKey(){
        return key;
    }

}
