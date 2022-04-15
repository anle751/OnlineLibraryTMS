package com.tms.web.controllers;

import com.tms.web.entities.security.ROLE;
import com.tms.web.entities.security.User.User;
import com.tms.web.services.entities.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @GetMapping
    public String register() {
        return "register";
    }

    @PostMapping
    public String save(@Valid User user, BindingResult bindingResult, Model model) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() != 0) {
            for (FieldError fieldError : fieldErrors) {
                model.addAttribute(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return "register";
        } else {
            User byUsername = userService.findByUsername(user.getUsername());
            if (Objects.isNull(byUsername)) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRoles(List.of(ROLE.ROLE_USER));
                user.setAccountNonExpired(true);
                user.setAccountNonLocked(true);
                user.setCredentialsNonExpired(true);
                user.setEnabled(true);
                user.setUserLibrary(new ArrayList<>());
                userService.save(user);
                return "login";
            } else {
                model.addAttribute("userExists", "User: " + user.getUsername() + " exist");
                return "register";
            }
        }
    }
}
