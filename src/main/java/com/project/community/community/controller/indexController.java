package com.project.community.community.controller;

import com.project.community.community.dto.PagequestionDTO;
import com.project.community.community.dto.QuestionDTO;
import com.project.community.community.mapper.QuestionMapper;
import com.project.community.community.mapper.UserMapper;
import com.project.community.community.model.Question;
import com.project.community.community.model.User;
import com.project.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class indexController {
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name",defaultValue = "hahah") String name, Model model){
        model.addAttribute("name",name);


        return "hello";
    }

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "2") Integer size
    ){


        PagequestionDTO pagequestion = questionService.list(page,size);
        model.addAttribute("questions",pagequestion);


        return "index";
    }
}
