package com.springboot.travel.pojo;

import java.util.Date;

public class Thumbs {
    private Integer thumbsId;

    private Integer scenicSpotId;

    private Integer userId;

    private Integer state;

    private Date createTime;

    public Integer getThumbsId() {
        return thumbsId;
    }

    public void setThumbsId(Integer thumbsId) {
        this.thumbsId = thumbsId;
    }

    public Integer getScenicSpotId() {
        return scenicSpotId;
    }

    public void setScenicSpotId(Integer scenicSpotId) {
        this.scenicSpotId = scenicSpotId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}