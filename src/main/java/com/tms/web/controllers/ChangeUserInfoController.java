package com.tms.web.controllers;

import com.tms.web.services.busines.ChangeUserInfoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.constraints.NotBlank;

@Log4j2
@Controller
@RequestMapping("/userAccount")
public class ChangeUserInfoController {
    @Autowired
    private ChangeUserInfoService changeUserInfoService;

    @PostMapping("/changeNickName")
    public RedirectView changeNickName(@RequestParam(name = "newNickName") @NotBlank String nickName,
                                       RedirectAttributes redirectAttributes) {
        if (changeUserInfoService.changeNickName(nickName)){
            redirectAttributes.addAttribute("info","nickName changed");
        }else {
            redirectAttributes.addAttribute("info","nickName not changed");
        }
        return new RedirectView("/userAccount");
    }

    @PostMapping("/changePass")
    public RedirectView changePassword(@RequestParam(name = "oldPass") @NotBlank String oldPass,
                                 @RequestParam(name = "newPass") @NotBlank String newPass,
                                 RedirectAttributes redirectAttributes) {
        if (changeUserInfoService.changePassword(oldPass, newPass)){
            redirectAttributes.addAttribute("info","password changed");
        }else {
            redirectAttributes.addAttribute("info","password not changed");
        }
        return new RedirectView("/userAccount");
    }

}
