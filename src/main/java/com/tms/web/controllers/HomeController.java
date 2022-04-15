package com.tms.web.controllers;

import com.tms.web.services.busines.GetUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private GetUserInfoService getUserInfoService;

    @GetMapping
    public String getHome() {
        return "home";
    }

    @PostMapping("/search")
    public String search(@RequestParam(name = "searchText", required = true) String search, Model model) {
        model.addAttribute("user", getUserInfoService.get());
        model.addAttribute("searchText", search);
        return "SearchResult";
    }
}
