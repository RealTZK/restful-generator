package io.tzk.restful.generator.admin.support.service.impl;

import io.tzk.restful.generator.admin.api.domain.dto.req.UserCReq;
import io.tzk.restful.generator.admin.api.domain.dto.res.UserRes;
import io.tzk.restful.generator.admin.api.domain.entity.Role;
import io.tzk.restful.generator.admin.api.domain.entity.SysUser;
import io.tzk.restful.generator.admin.api.service.UserService;
import io.tzk.restful.generator.admin.support.repository.RoleRepository;
import io.tzk.restful.generator.admin.support.repository.UserRepository;
import io.tzk.restful.generator.admin.support.util.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
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
    public UserRes get(Long id) {
        return userRepository.findById(id)
                .map(userConverter::convert)
                .orElseThrow(() -> new NoSuchElementException("The user with id [%d] does not exist".formatted(id)));
    }

    @Override
    public Long create(UserCReq req) {
        SysUser sysUser = userConverter.convert(req);
        sysUser.setPassword(passwordEncoder.encode(req.password()));
        Set<Role> defaultRole = roleRepository.findByRoleName(Role.READ_ONLY).stream().collect(Collectors.toSet());
        sysUser.setRoles(defaultRole);
        return userRepository.save(sysUser).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
