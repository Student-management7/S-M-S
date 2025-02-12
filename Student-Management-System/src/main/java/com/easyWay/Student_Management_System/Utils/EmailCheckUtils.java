package com.easyWay.Student_Management_System.Utils;

import com.easyWay.Student_Management_System.Entity.Users;
import com.easyWay.Student_Management_System.Repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class EmailCheckUtils {

    @Autowired
    UsersRepo usersRepo;

    public boolean isEmailAlreadyRegistered(String email, String code ) {
        Users user = usersRepo.findUsersByEmail(email, code);

        if (ObjectUtils.isEmpty(user)){
            return false;
        }else {
            return true;
        }
    }

}
