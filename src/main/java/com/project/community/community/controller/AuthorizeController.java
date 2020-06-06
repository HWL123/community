package com.project.community.community.controller;


import com.project.community.community.dto.AuthorizeDTO;
import com.project.community.community.dto.GithubUser;
import com.project.community.community.mapper.UserMapper;
import com.project.community.community.model.User;
import com.project.community.community.provider.GithubProvider;
import com.project.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private  UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    String client_id;
    @Value("${github.client.secret}")
    String secret;
    @Value("${github.rediret.uri}")
    String uri;

    @GetMapping("/callback")
    public  String callback(@RequestParam(name="code") String code, @RequestParam(name="state") String state, HttpServletRequest request,
    HttpServletResponse response){
        AuthorizeDTO authorizeDTO = new AuthorizeDTO();
        authorizeDTO.setCode(code);
        authorizeDTO.setRedirect_uri(uri);
        authorizeDTO.setState(state);
        authorizeDTO.setClient_id(client_id);
        authorizeDTO.setClient_secret(secret);

        String accesstoken = githubProvider.getAccessToken(authorizeDTO);
        GithubUser user = githubProvider.getUser(accesstoken);
        System.out.print(user.getLogin() + user.getId());
        if(user == null){
            return "redirect:/";

        }else{

            User user_local = new User();

            String token = UUID.randomUUID().toString();
            user_local.setToken(token);
            user_local.setName(user.getLogin());
            user_local.setAccount_id(String.valueOf(user.getId()));

            user_local.setAvatar_url(user.getAvatar_url());
           // userMapper.insert(user_local);
            userService.createOrupdate(user_local);
            response.addCookie(new Cookie("token",token));

            //request.getSession().setAttribute("user",user);
            return "redirect:/";
        }

    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/";
    }
}
