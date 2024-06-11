package com.store.constructor.models;

public class Buyer {

    private String mail;

    public Buyer() {
    }

    public Buyer(String mail) {
        this.mail = mail;
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }



}
