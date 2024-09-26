package com.adacho.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // 올바른 Model 임포트
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String helloPage(Model model) {
        model.addAttribute("greeting", "Hi");
        return "hello";
    }
}
