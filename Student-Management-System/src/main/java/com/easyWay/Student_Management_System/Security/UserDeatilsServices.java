package com.easyWay.Student_Management_System.Security;

import com.easyWay.Student_Management_System.Feign.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDeatilsServices implements UserDetailsService {


   @Autowired
   DatabaseService databaseService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = databaseService.findByMail(username);

        if(user == null){
            System.out.println(" user not found ");
            throw  new UsernameNotFoundException("User not found "+ username );
        }
        System.out.println(user.toString());
        return new LoggedInUser(user);
    }
}