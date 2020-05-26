package com.project.community.community.controller;


import com.project.community.community.dto.AuthorizeDTO;
import com.project.community.community.dto.GithubUser;
import com.project.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    String client_id;
    @Value("${github.client.secret}")
    String secret;
    @Value("${github.rediret.uri}")
    String uri;

    @GetMapping("/callback")
    public  String callback(@RequestParam(name="code") String code,@RequestParam(name="state") String state){
        AuthorizeDTO authorizeDTO = new AuthorizeDTO();
        authorizeDTO.setCode(code);
        authorizeDTO.setRedirect_uri(uri);
        authorizeDTO.setState(state);
        authorizeDTO.setClient_id(client_id);
        authorizeDTO.setClient_secret(secret);

        String accesstoken = githubProvider.getAccessToken(authorizeDTO);
        GithubUser user = githubProvider.getUser(accesstoken);
        System.out.print(user.getLogin() + user.getId());
        return "index";
    }
}
