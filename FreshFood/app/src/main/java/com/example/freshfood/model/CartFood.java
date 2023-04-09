package com.example.freshfood.model;

import com.google.gson.annotations.SerializedName;

public class CartFood {
    @SerializedName("tenhang")
    private String tenhang;
    @SerializedName("dongia")
    private String dongia;
    @SerializedName("image")
    private String image;
    @SerializedName("id")
    private String id;
    private String Soluong;


    public CartFood(String tenhang, String dongia, String image, String id, String soluong) {
        this.tenhang = tenhang;
        this.dongia = dongia;
        this.image = image;
        this.id = id;
        Soluong = soluong;
    }

    public String getSoluong() {
        return Soluong;
    }

    public void setSoluong(String soluong) {
        Soluong = soluong;
    }

    public String getTenhang() {
        return tenhang;
    }

    public void setTenhang(String tenhang) {
        this.tenhang = tenhang;
    }

    public String getDongia() {
        return dongia;
    }

    public void setDongia(String dongia) {
        this.dongia = dongia;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
