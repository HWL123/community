package com.project.community.community.dto;



public class GithubUser {
    private String login;
    private Long id;
    private String bio;

    public String getBio() {
        return bio;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setBio(String bio) {
        this.bio = bio;

    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
