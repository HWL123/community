package com.project.community.community.service;


import com.project.community.community.mapper.UserMapper;
import com.project.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private  UserMapper userMapper;

    public void createOrupdate(User user) {
        User dbuser = userMapper.findbyAccountId(user.getAccount_id());
        if(dbuser == null){
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.insert(user);
        }
        else{
            user.setGmt_modified(System.currentTimeMillis());
            userMapper.updateToken(user);
        }

    }


}
