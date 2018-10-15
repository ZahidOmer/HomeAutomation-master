package com.example.zahid.homeautomation.Model;

public class Account {
    private String email;
    private String devicemac;
    private String liverequest;

    public String getLiverequest() {
        return liverequest;
    }

    public void setLiverequest(String liverequest) {
        this.liverequest = liverequest;
    }

    public Account(){


    }
    public Account(String email, String devicemac, String liverequest) {
        this.email = email;
        this.devicemac = devicemac;
        this.liverequest = liverequest;
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


}
