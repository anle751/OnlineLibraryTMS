package com.tms.web.services.busines;

import org.springframework.stereotype.Service;

@Service
public interface ChangeUserInfoService {
    public Boolean changeNickName(Long userId, String newNickName);
    public Boolean changeNickName(String newNickName);
    public Boolean changePassword(Long userId,String oldPassword, String newPassword);
    public Boolean changePassword(String oldPassword, String newPassword);
}
