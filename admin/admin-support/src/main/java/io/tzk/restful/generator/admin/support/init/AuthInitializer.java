package io.tzk.restful.generator.admin.support.init;

import io.tzk.restful.generator.admin.api.domain.entity.Role;
import io.tzk.restful.generator.admin.api.domain.entity.SysUser;
import io.tzk.restful.generator.admin.support.repository.RoleRepository;
import io.tzk.restful.generator.admin.support.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Configuration
public class AuthInitializer implements ApplicationRunner {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final WebApplicationContext applicationContext;

    @Value("${system.users.admin.username}")
    private String adminName;

    @Value("${system.users.admin.password}")
    private String adminPass;

    @Value("${system.users.guest.username}")
    private String guestName;

    @Value("${system.users.guest.password}")
    private String guestPass;

    @Override
    public void run(ApplicationArguments args) {
        Role adminRole = roleRepository.findByRoleName(Role.ADMIN)
                .orElseGet(() -> roleRepository.save(Role.builder()
                        .roleName(Role.ADMIN)
                        .createUser("system")
                        .updateUser("system")
                        .build()));
        Role guestRole = roleRepository.findByRoleName(Role.GUEST)
                .orElseGet(() -> roleRepository.save(Role.builder()
                        .roleName(Role.GUEST)
                        .createUser("system")
                        .updateUser("system")
                        .build()));
        SysUser admin = userRepository.findByUsername(adminName)
                .orElseGet(() -> userRepository.save(SysUser.builder()
                        .username(adminName)
                        .nickname(adminName)
                        .createUser("system")
                        .updateUser("system")
                        .password(passwordEncoder.encode(adminPass))
                        .roles(Set.of(adminRole))
                        .build()));
        SysUser guest = userRepository.findByUsername(guestName)
                .orElseGet(() -> userRepository.save(SysUser.builder()
                        .username(guestName)
                        .nickname(guestName)
                        .createUser("system")
                        .updateUser("system")
                        .password(passwordEncoder.encode(guestPass))
                        .roles(Set.of(guestRole))
                        .build()));
        if (admin.getRoles() == null || !admin.getRoles().contains(adminRole)) {
            admin.setRoles(Set.of(adminRole));
            userRepository.save(admin);
        }
        if (guest.getRoles() == null || !guest.getRoles().contains(guestRole)) {
            guest.setRoles(Set.of(guestRole));
            userRepository.save(guest);
        }
        Set<String> authorities = applicationContext.getBean(RequestMappingHandlerMapping.class)
                .getHandlerMethods()
                .keySet()
                .stream()
                .flatMap(mappingInfo -> {
                    Stream<String> methods = mappingInfo.getMethodsCondition()
                            .getMethods()
                            .stream().map(Enum::name);
                    Stream<String> patterns = Objects.requireNonNull(mappingInfo.getPathPatternsCondition())
                            .getPatterns()
                            .stream().map(PathPattern::getPatternString);
                    return methods
                            .flatMap(method -> patterns.map(pattern -> method + ":" + pattern));
                }).collect(Collectors.toSet());
        if (!Objects.equals(authorities, admin.getAuthorities())) {
            adminRole.setAuthorities(authorities);
            roleRepository.save(adminRole);
        }
        Set<String> guestAuthorities = authorities
                .stream().filter(authority -> authority.startsWith("GET"))
                .collect(Collectors.toSet());
        if (!Objects.equals(guestAuthorities, guestRole.getAuthorities())) {
            guestRole.setAuthorities(guestAuthorities);
            roleRepository.save(guestRole);
        }
    }
}
