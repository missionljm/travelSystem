package com.springboot.travel.pojo;

import java.util.Date;

public class Guide {
    private Integer guideId;

    private String guideName;

    private String age;

    private String guideIntro;

    private Date createTime;

    public Integer getGuideId() {
        return guideId;
    }

    public void setGuideId(Integer guideId) {
        this.guideId = guideId;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName == null ? null : guideName.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getGuideIntro() {
        return guideIntro;
    }

    public void setGuideIntro(String guideIntro) {
        this.guideIntro = guideIntro == null ? null : guideIntro.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}