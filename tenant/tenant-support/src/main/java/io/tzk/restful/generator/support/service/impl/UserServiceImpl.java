package io.tzk.restful.generator.support.service.impl;

import io.tzk.restful.generator.api.service.UserService;
import io.tzk.restful.generator.api.entity.User;
import io.tzk.restful.generator.support.repository.UserRepository;
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

}
