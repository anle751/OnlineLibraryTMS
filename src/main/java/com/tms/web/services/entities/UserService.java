package com.tms.web.services.entities;

import com.tms.web.entities.security.User.User;
import com.tms.web.entities.security.User.projections.UserInfo;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User get(Long id);
    public User get(String username);
    public UserInfo getUserInfo(Long id);
    public void updateNickName(Long userId,String newNickName);
    public void updatePassword(Long userId, String newPassword);
    public User findByUsername(String username);
    public User save(User user);
}
