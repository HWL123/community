package com.project.community.community.dto;


import com.project.community.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private User user;
    private int id;
    private  String title;
    private  String description;
    private  String tag;
    private  Integer comment_count;
    private  Integer view_count;
    private  Integer like_count;
    private  Integer creator;
    private Long gmt_create;
    private  Long gmt_modified;

}
