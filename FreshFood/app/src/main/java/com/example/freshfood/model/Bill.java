package com.example.freshfood.model;

public class Bill {
    private String json;
    private String tongtien;
    private String id_user;

    public Bill(String json, String tongtien, String id_user) {
        this.json = json;
        this.tongtien = tongtien;
        this.id_user = id_user;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
