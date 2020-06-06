package com.project.community.community.controller;


import com.project.community.community.dto.PagequestionDTO;
import com.project.community.community.mapper.UserMapper;
import com.project.community.community.model.User;
import com.project.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class profileController {
    @Autowired
    private UserMapper userMapper;


    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action , Model model, HttpServletRequest  request,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "2") Integer size
    ){

        User user = (User) request.getSession().getAttribute("user");
       if (user == null)return "redirect:/";

       if("questions".contains(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","My Questions");

        }
        else if("replies".contains(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","My Replies");

        }

        PagequestionDTO pagequestion = questionService.list(user.getId(),page,size);
        model.addAttribute("questions",pagequestion);


        return "profile";
    }
}
