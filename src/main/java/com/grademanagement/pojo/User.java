package com.grademanagement.pojo;

import java.util.List;

public class User {
    private Long id;

    private String name;

    private String password;

    private Long ustate;
    private List<Achievement> achievements;

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Long getUstate() {
        return ustate;
    }

    public void setUstate(Long ustate) {
        this.ustate = ustate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", ustate=" + ustate +
                ", achievements=" + achievements +
                '}';
    }
}