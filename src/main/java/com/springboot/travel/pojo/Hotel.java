package com.springboot.travel.pojo;

public class Hotel {
    private Integer hotelId;

    private String hotelName;

    private String hotelAdress;

    private String hotelIntro;

    private String hotelLink;

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName == null ? null : hotelName.trim();
    }

    public String getHotelAdress() {
        return hotelAdress;
    }

    public void setHotelAdress(String hotelAdress) {
        this.hotelAdress = hotelAdress == null ? null : hotelAdress.trim();
    }

    public String getHotelIntro() {
        return hotelIntro;
    }

    public void setHotelIntro(String hotelIntro) {
        this.hotelIntro = hotelIntro == null ? null : hotelIntro.trim();
    }

    public String getHotelLink() {
        return hotelLink;
    }

    public void setHotelLink(String hotelLink) {
        this.hotelLink = hotelLink == null ? null : hotelLink.trim();
    }
}