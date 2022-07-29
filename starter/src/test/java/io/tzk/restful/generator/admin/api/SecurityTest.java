package io.tzk.restful.generator.admin.api;

import io.jsonwebtoken.Claims;
import io.tzk.restful.generator.ApplicationStarter;
import io.tzk.restful.generator.admin.rest.util.JwtUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest(classes = ApplicationStarter.class)
public class SecurityTest {

    @Test
    public void testPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("1234");
        System.out.println(encode);
        boolean matches = passwordEncoder.matches("1234", encode);
        System.out.println(matches);
    }

    @Test
    public void testTokenParser(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyZGJhNTBlYzdhMmE0MGEyODk4NDhlMDc3MWZlZDQwZiIsInN1YiI6IntcImFjY291bnRcIjpudWxsLFwidXNlcm5hbWVcIjpudWxsLFwiYWNjb3VudE5vbkV4cGlyZWRcIjp0cnVlLFwiYWNjb3VudE5vbkxvY2tlZFwiOnRydWUsXCJjcmVkZW50aWFsc05vbkV4cGlyZWRcIjp0cnVlLFwiZW5hYmxlZFwiOnRydWUsXCJhdXRob3JpdGllc1wiOltdfSIsImlzcyI6InJlc3RmdWwtZ2VuZXJhdG9yLmFkbWluIiwiaWF0IjoxNjU4OTg3MDQzLCJleHAiOjE2NTg5OTA2NDN9.LzEuGKQa4vyot1rYXQjAhmGq6yuwc19dViKymBGo0Ac";
        Claims claims = JwtUtil.parseJWT(token);
        System.out.println(claims);
    }
}
