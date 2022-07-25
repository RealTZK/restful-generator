package io.tzk.restful.generator.admin.support.service.impl;

import io.tzk.restful.generator.admin.api.domain.entity.User;
import io.tzk.restful.generator.admin.api.service.UserService;
import io.tzk.restful.generator.admin.support.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Long save(User condition) {
        return userRepository.save(condition).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        return userRepository.findByAccount(account);
    }
}
