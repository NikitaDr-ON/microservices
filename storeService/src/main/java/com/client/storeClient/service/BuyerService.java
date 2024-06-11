package com.client.storeClient.service;


import com.client.storeClient.Repository.BuyerImpl;
import com.client.storeClient.model.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BuyerService{ //implements UserDetailsService {
    /*
    @Autowired
    BuyerImpl buyerImpl;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        Buyer user = buyerImpl.findByMail(mail);

        if (user == null) {
            System.out.println("user not found");
            throw new UsernameNotFoundException("User not found");
        }

        System.out.println(user.getPassword());
        return user;
    }

    public Buyer findUserById(Long userId) {
        Buyer userFromDb = buyerImpl.getById(userId);
        return userFromDb;
    }

    public boolean saveUser(String fio,String mail,String password) {
        Buyer userFromDB = buyerImpl.findByMail(mail);
        if (userFromDB != null) {
            return false;
        }
        else
        {
            Buyer newUser = new Buyer(fio,mail,password);
            newUser.setPassword( new BCryptPasswordEncoder().encode(password));
            newUser.setActivation(false);
            newUser.setActivationCode(UUID.randomUUID().toString());
            buyerImpl.create(newUser);
             if(!StringUtils.isEmpty(newUser.getMail()))
            {
                newUser.getUsername();
                newUser.getActivationCode();
                String message=String.format(newUser.getActivationCode());
                mailSender.send(newUser.getMail(),"Activation code", message);

            return true;
        }
    }
    */
}
