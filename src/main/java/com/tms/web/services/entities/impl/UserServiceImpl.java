package com.tms.web.services.entities.impl;

import com.tms.web.entities.repositories.UserRepository;
import com.tms.web.entities.security.User.User;
import com.tms.web.entities.security.User.projections.UserInfo;
import com.tms.web.services.entities.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User get(Long id) {
        User user = userRepository.getById(id);
        return user;
    }

    @Override
    public User get(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserInfo getUserInfo(Long id) {
        return userRepository.getUserById(id);
    }

    @Transactional
    @Override
    public void updateNickName(Long userId, String newNickName) {
        userRepository.getById(userId).setNickName(newNickName);
    }

    @Transactional
    @Override
    public void updatePassword(Long userId, String newPassword) {
        userRepository.getById(userId).setPassword(newPassword);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

}