package com.fulln.pips.Controller.ViewController.System;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/home")
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){
        return "/login";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "/welcome";
    }

}