package com.client.storeClient.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

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
