package com.example.freshfood.model;

public class PopularFood {
    private String tenhang,dongia,image,id,mota;

    public PopularFood(String tenhang, String dongia, String image, String id, String mota) {
        this.tenhang = tenhang;
        this.dongia = dongia;
        this.image = image;
        this.id = id;
        this.mota = mota;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
