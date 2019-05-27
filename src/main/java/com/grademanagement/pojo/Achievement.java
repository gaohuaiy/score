package com.grademanagement.pojo;

public class Achievement {
    private Long id;

    private Long cid;

    private Integer score;

    private Long uid;
    private Curriculum curriculum;

    public Curriculum getCurriculum() {

        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "id=" + id +
                ", cid=" + cid +
                ", score=" + score +
                ", uid=" + uid +
                ", curriculum=" + curriculum +
                '}';
    }
}