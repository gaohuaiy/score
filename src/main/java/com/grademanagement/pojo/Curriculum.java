package com.grademanagement.pojo;

public class Curriculum {
    private Long id;

    private String tName;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName == null ? null : tName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "Curriculum{" +
                "id=" + id +
                ", tName='" + tName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}