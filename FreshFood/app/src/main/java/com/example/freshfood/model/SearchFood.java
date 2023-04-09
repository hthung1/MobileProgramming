package com.example.freshfood.model;

public class SearchFood {
    private String tenhang;
    private String id;
    private String image;
    private String mota;
    private String dongia;

    public SearchFood(String tenhang, String id, String image, String mota, String dongia) {
        this.tenhang = tenhang;
        this.id = id;
        this.image = image;
        this.mota = mota;
        this.dongia = dongia;
    }

    public String getTenhang() {
        return tenhang;
    }

    public void setTenhang(String tenhang) {
        this.tenhang = tenhang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getDongia() {
        return dongia;
    }

    public void setDongia(String dongia) {
        this.dongia = dongia;
    }
}
