package io.tzk.restful.generator.admin.support.service.impl;

import io.tzk.restful.generator.admin.api.domain.dto.req.UserCReq;
import io.tzk.restful.generator.admin.api.domain.dto.res.UserRes;
import io.tzk.restful.generator.admin.api.domain.entity.Role;
import io.tzk.restful.generator.admin.api.domain.entity.User;
import io.tzk.restful.generator.admin.api.service.UserService;
import io.tzk.restful.generator.admin.support.repository.RoleRepository;
import io.tzk.restful.generator.admin.support.repository.UserRepository;
import io.tzk.restful.generator.admin.support.util.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserConverter userConverter;

    @Override
    public UserRes getById(Long id) {
        return userRepository.findById(id)
                .map(userConverter::convert)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                        "can't find user for id: %s".formatted(id)));
    }

    @Override
    public Long save(UserCReq condition) {
        User user = userConverter.convert(condition);
        user.setPassword(passwordEncoder.encode(condition.password()));
        Set<Role> defaultRole = roleRepository.findByRoleName(Role.READ_ONLY).stream().collect(Collectors.toSet());
        user.setRoles(defaultRole);
        return userRepository.save(user).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
