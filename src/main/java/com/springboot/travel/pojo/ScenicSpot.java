package com.springboot.travel.pojo;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;

public class ScenicSpot {
    private Integer scenicSpotId;

    private String scenicSpotName;

    private String scenicSpotIntro;

    private String scenicSpotImage;

    private Integer scenicSpotHot;

    private Date createTime;

    private Integer setCard;

    private String scenicSpotAddress;

    @TableField(exist = false)
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSetCard() {
        return setCard;
    }

    public void setSetCard(Integer setCard) {
        this.setCard = setCard;
    }

    public Integer getScenicSpotId() {
        return scenicSpotId;
    }

    public void setScenicSpotId(Integer scenicSpotId) {
        this.scenicSpotId = scenicSpotId;
    }

    public String getScenicSpotName() {
        return scenicSpotName;
    }

    public void setScenicSpotName(String scenicSpotName) {
        this.scenicSpotName = scenicSpotName == null ? null : scenicSpotName.trim();
    }

    public String getScenicSpotIntro() {
        return scenicSpotIntro;
    }

    public void setScenicSpotIntro(String scenicSpotIntro) {
        this.scenicSpotIntro = scenicSpotIntro == null ? null : scenicSpotIntro.trim();
    }

    public String getScenicSpotImage() {
        return scenicSpotImage;
    }

    public void setScenicSpotImage(String scenicSpotImage) {
        this.scenicSpotImage = scenicSpotImage == null ? null : scenicSpotImage.trim();
    }

    public Integer getScenicSpotHot() {
        return scenicSpotHot;
    }

    public void setScenicSpotHot(Integer scenicSpotHot) {
        this.scenicSpotHot = scenicSpotHot;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getScenicSpotAddress() {
        return scenicSpotAddress;
    }

    public void setScenicSpotAddress(String scenicSpotAddress) {
        this.scenicSpotAddress = scenicSpotAddress == null ? null : scenicSpotAddress.trim();
    }
}