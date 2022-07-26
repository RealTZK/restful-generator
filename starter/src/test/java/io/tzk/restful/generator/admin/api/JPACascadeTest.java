package io.tzk.restful.generator.admin.api;

import io.tzk.restful.generator.ApplicationStarter;
import io.tzk.restful.generator.admin.api.domain.entity.Role;
import io.tzk.restful.generator.admin.api.domain.entity.User;
import io.tzk.restful.generator.admin.support.repository.RoleRepository;
import io.tzk.restful.generator.admin.support.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
public class JPACascadeTest {

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @Test
    public void save1() {
        var user = User.builder()
                .username("root")
                .account("root")
                .password("generator")
                .build();
        userRepository.save(user);
    }

    @Test
    public void save2() {
        var role = Role.builder()
                .roleName("超级管理员")
                .build();
        roleRepository.save(role);
    }

    @Test
    public void testAdd1() {
        var user = userRepository.findById(1L).get();
        var role = roleRepository.findById(1L).get();
        user.setRoles(Set.of(role));
        userRepository.save(user);
    }

    @Test
    public void testAdd2() {
        var user = userRepository.findById(1L).get();
        var role = roleRepository.findById(1L).get();
        role.setUsers(Set.of(user));
        roleRepository.save(role);
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
