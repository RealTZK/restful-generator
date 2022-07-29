package io.tzk.restful.generator.admin.rest.util;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    private static final long JWT_TTL = 1000 * 60 * 60L;
    private static final String JWT_KEY = "RESTFULGENERATOR";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String createJWT(String subject) {
        return builder(subject, -1, getUUID()).compact();
    }

    public static String createJWT(String subject, long ttl) {
        return builder(subject, ttl, getUUID()).compact();
    }

    public static String createJWT(String id, String subject, long ttl) {
        return builder(subject, ttl, id).compact();
    }

    private static JwtBuilder builder(String subject, long ttl, String uuid) {
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long now = System.currentTimeMillis();
        if (ttl == -1) {
            ttl = JWT_TTL;
        }
        long expTime = now + ttl;
        return Jwts.builder()
                .setId(uuid)
                .setSubject(subject)
                .setIssuer("restful-generator.admin")
                .setIssuedAt(new Date(now))
                .signWith(algorithm, secretKey)
                .setExpiration(new Date(expTime));
    }

    private static SecretKey generalKey() {
        byte[] decode = Base64.getDecoder().decode(JWT_KEY);
        return new SecretKeySpec(decode, 0, decode.length, "AES");
    }

    public static Claims parseJWT(String jwt) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(generalKey())
                    .parseClaimsJws(jwt)
                    .getBody();
            return body;
        }catch (Exception e){
            throw new JwtException("token has been tampered");
        }
    }
}
