package com.tms.web.controllers;

import com.tms.web.services.busines.ChangeUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;

@Controller
@RequestMapping("/userAccount")
public class ChangeUserInfoController {
    @Autowired
    private ChangeUserInfoService changeUserInfoService;

    @PostMapping("/changeNickName")
    public String changeNickName(@RequestParam(name = "newNickName") @NotBlank String nickName, Model model) {
        Boolean result = changeUserInfoService.changeNickName(nickName);
        return ("redirect:/userAccount");
    }

    @PostMapping("/changePass")
    public String changePassword(@RequestParam(name = "oldPass") @NotBlank String oldPass,
                                 @RequestParam(name = "newPass") @NotBlank String newPass,
                                 Model model) {
        Boolean result = changeUserInfoService.changePassword(oldPass, newPass);
        return ("redirect:/userAccount");
    }
}
