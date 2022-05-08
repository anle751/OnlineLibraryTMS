package com.tms.web.services.busines.impl;

import com.tms.web.entities.security.User.User;
import com.tms.web.services.busines.ChangeUserInfoService;
import com.tms.web.services.busines.GetUserInfoService;
import com.tms.web.services.entities.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ChangeUserInfoServiceImpl implements ChangeUserInfoService {
    @Autowired
    private UserService userService;
    @Autowired
    private GetUserInfoService getUserInfoService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Boolean changeNickName(Long userId, String newNickName) {
        try {
            userService.updateNickName(userId, newNickName);
            return true;
        } catch (Exception e) {
            log.fatal("error change nickname" + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean changeNickName(String newNickName) {
        try {
            Long userId = getUserInfoService.get().getId();
            userService.updateNickName(userId, newNickName);
            return true;
        } catch (Exception e) {
            log.fatal("error change nickname" + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean changePassword(Long userId, String oldPassword, String newPassword) {
        try {
            User user = userService.get(userId);
            String password = user.getPassword();
            oldPassword = passwordEncoder.encode(oldPassword);
            if (password.equals(oldPassword)) {
                userService.updatePassword(userId, newPassword);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean changePassword(String oldPassword, String newPassword) {
        try {
            Long id = getUserInfoService.get().getId();
            String password = userService.get(id).getPassword();
            boolean matches = passwordEncoder.matches(oldPassword, password);
            newPassword = passwordEncoder.encode(newPassword);

            if (matches) {
                userService.updatePassword(id, newPassword);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.fatal("error: change p");
            return false;
        }
    }
}
