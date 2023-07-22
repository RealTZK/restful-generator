package io.tzk.restful.generator.admin.rest.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtil {

    public final static String TOKEN_PREFIX = "Bearer ";

    private final static String SECRET = "RESTFUL##GENERATOR##JSON##WEB##TOKEN==";
    private final static long JWT_TTL = 1000 * 60 * 60L;
    private final static SecretKey SECRET_KEY = new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

    public static String createToken(String subject) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + JWT_TTL))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static String parseToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
