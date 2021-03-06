package com.project.community.community.provider;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.project.community.community.dto.AuthorizeDTO;
import com.project.community.community.dto.GithubUser;
import jdk.nashorn.internal.parser.JSONParser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;


@Component
public class GithubProvider {
    public String getAccessToken(AuthorizeDTO adto){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(adto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string  = response.body().string();
            System.out.println(string);

            String[] split = string.split("&");
            String tokenstr = split[0];
            String token = tokenstr.split("=")[1];
            System.out.println(token);
            return  token;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(" https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println(string);
            GithubUser user = JSON.parseObject(string, GithubUser.class);

            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
