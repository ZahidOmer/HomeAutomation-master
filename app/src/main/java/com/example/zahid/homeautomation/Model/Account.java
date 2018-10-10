package com.example.zahid.homeautomation.Model;

public class Account {
    private String email;
    private String devicemac;
    private String live;

    public Account(){

    }
    public Account(String email, String devicemac, String live) {
        this.email = email;
        this.devicemac = devicemac;
        this.live = live;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDevicemac() {
        return devicemac;
    }

    public void setDevicemac(String devicemac) {
        this.devicemac = devicemac;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }
}
