package io.tzk.restful.generator.admin.api;

import io.tzk.restful.generator.admin.api.domain.entity.User;
import io.tzk.restful.generator.admin.api.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;
    @Resource
    private  PasswordEncoder passwordEncoder;

    @Test
    public void create() {
        User user = User.builder()
                .account("tzk")
                .password(passwordEncoder.encode("123456"))
                .build();
        userService.save(user);
    }
}
