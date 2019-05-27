package com.grademanagement.pojo;

public class ExcelDto {
    private int id;
        private String name;
        private String cname;
        private int score;
        private String tname;
        private int cid;

    public int getCid() {

        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }


    @Override
    public String toString() {
        return "ExcelDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cname='" + cname + '\'' +
                ", score=" + score +
                ", tname='" + tname + '\'' +
                ", cid=" + cid +
                '}';
    }
}
