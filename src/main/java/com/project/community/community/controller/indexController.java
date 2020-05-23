package com.project.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class indexController {
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name",defaultValue = "hahah") String name, Model model){
        model.addAttribute("name",name);


        return "hello";
    }

    @GetMapping("/")
    public String index(){return "index";}
}
