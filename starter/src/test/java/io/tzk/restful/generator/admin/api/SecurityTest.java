package io.tzk.restful.generator.admin.api;

import io.tzk.restful.generator.admin.api.domain.dto.TokenBody;
import io.tzk.restful.generator.admin.rest.util.JwtUtil;
import io.tzk.restful.generator.common.util.serialize.JSON;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

@SpringBootTest
public class SecurityTest {

    @Resource
    private JSON serializer;

    @Test
    public void testPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("1234");
        System.out.println(encode);
        boolean matches = passwordEncoder.matches("1234", encode);
        System.out.println(matches);
    }

    @Test
    public void testJwtUtil() {
        TokenBody tokenBody = new TokenBody("tzk", true, true, true, true, Collections.emptyList());
        String subject = serializer.serialize(tokenBody);
        String token = JwtUtil.createToken(subject);
        String parsedToken = JwtUtil.parseToken(token);
        Assertions.assertEquals(subject, parsedToken);
    }
}
