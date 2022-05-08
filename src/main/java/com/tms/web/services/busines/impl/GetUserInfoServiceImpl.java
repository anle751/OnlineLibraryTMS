package com.tms.web.services.busines.impl;

import com.tms.web.entities.security.User.User;
import com.tms.web.entities.security.User.projections.UserInfo;
import com.tms.web.services.busines.GetUserInfoService;
import com.tms.web.services.entities.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class GetUserInfoServiceImpl implements GetUserInfoService {
    @Autowired
    UserService userService;
    @Override
    public UserInfo get() {
        User user = null;
        UserInfo userInfo =null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            user = userService.get(username);
            userInfo = userService.getUserInfo(user.getId());
        }
        return userInfo;
    }
}
