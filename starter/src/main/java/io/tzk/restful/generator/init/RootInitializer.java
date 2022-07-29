package io.tzk.restful.generator.init;

import io.tzk.restful.generator.admin.api.domain.entity.Role;
import io.tzk.restful.generator.admin.api.domain.entity.User;
import io.tzk.restful.generator.admin.support.repository.RoleRepository;
import io.tzk.restful.generator.admin.support.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Configuration
public class RootInitializer {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final WebApplicationContext applicationContext;

    @Value("${spring.sql.init.username}")
    private String username;

    @Value("${spring.sql.init.password}")
    private String password;

    @PostConstruct
    public void init() {
        Role role = roleRepository.findByRoleName("root")
                .orElseGet(() -> roleRepository.save(Role.builder()
                        .roleName("root")
                        .createUser("system")
                        .updateUser("system")
                        .build()));
        User user = userRepository.findByUsername(username)
                .orElseGet(() -> userRepository.save(User.builder()
                        .username(username)
                        .nickname(password)
                        .createUser("system")
                        .updateUser("system")
                        .password(passwordEncoder.encode(password))
                        .build()));
        if (!user.getRoles().contains(role)) {
            user.setRoles(Set.of(role));
            userRepository.save(user);
        }
        Set<String> authorities = applicationContext.getBean(RequestMappingHandlerMapping.class)
                .getHandlerMethods()
                .keySet()
                .stream()
                .flatMap(mappingInfo -> {
                    Stream<String> methods = mappingInfo.getMethodsCondition()
                            .getMethods()
                            .stream().map(Enum::name);
                    Stream<String> patterns = mappingInfo.getPathPatternsCondition()
                            .getPatterns()
                            .stream().map(PathPattern::getPatternString);
                    return methods
                            .flatMap(method -> patterns.map(pattern -> method + ":" + pattern));
                }).collect(Collectors.toSet());
        if (!Objects.equals(authorities, role.getAuthorities())) {
            role.setAuthorities(authorities);
            roleRepository.save(role);
        }
    }
}
