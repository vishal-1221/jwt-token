package com.security.jwt.service;

import com.security.jwt.model.Users;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {


    List<Users> userlist = new ArrayList<>();

    public UserService() {
        userlist.add(new Users(UUID.randomUUID(), "Vishal", "vishalbisht00@gmail.com"));
        userlist.add(new Users(UUID.randomUUID(), "Neha", "neha.patil@gmail.com"));
        userlist.add(new Users(UUID.randomUUID(), "Nayan", "nayan.patil@gmail.com"));
        userlist.add(new Users(UUID.randomUUID(), "Harsh", "harsh.desai@gmail.com"));
        userlist.add(new Users(UUID.randomUUID(), "Dhriti", "dhriti.porter@gmail.com"));
    }

    public List<Users> getUserlist() {
        return this.userlist;
    }


}
