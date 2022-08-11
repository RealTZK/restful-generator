package io.tzk.restful.generator.admin.api;

import io.tzk.restful.generator.ApplicationStarter;
import io.tzk.restful.generator.admin.api.domain.entity.Role;
import io.tzk.restful.generator.admin.api.domain.entity.User;
import io.tzk.restful.generator.admin.support.repository.RoleRepository;
import io.tzk.restful.generator.admin.support.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Set;

@SpringBootTest(classes = ApplicationStarter.class)
public class JPACascadeTest {

    @Resource
    private UserRepository userRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        userRepository.findByUsername("root")
                .map(user -> new TestingAuthenticationToken(user, null))
                .ifPresent(SecurityContextHolder.getContext()::setAuthentication);
    }

    @Test
    public void save1() {
        var user = User.builder()
                .username("root")
                .nickname("root")
                .createUser("root")
                .updateUser("root")
                .password(passwordEncoder.encode("root"))
                .build();
        userRepository.save(user);
    }

    @Test
    public void save2() {
        var role = Role.builder()
                .roleName("root")
                .build();
        roleRepository.save(role);
    }

    @Test
    public void saveArray() {
        roleRepository
                .findById(4L)
                .ifPresent(role -> {
                    role.setAuthorities(Set.of("ALL"));
                    roleRepository.save(role);
                });
    }

    @Test
    public void testAdd1() {
        var user = userRepository.findByUsername("root").get();
        var role = roleRepository.findByRoleName("root").get();
        user.setRoles(Set.of(role));
        userRepository.save(user);
    }

    @Test
    public void delete1() {
        userRepository.deleteById(1L);
    }

    @Test
    public void delete2() {
        roleRepository.deleteById(1L);
    }
}
