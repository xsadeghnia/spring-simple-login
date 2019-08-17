package com.example.login.service;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> users;

    public UserService() {
        users = new ArrayList<>();
    }

    public void add(User user){
        for (User u : users){
            if (u.getUserName().equals(user.getUserName())){
                throw new IllegalArgumentException();
            }
        }
        users.add(user);
    }
    public User lookUp(String userName, String password) throws UserNotFoundException {
        for (User u:users) {
            if(userName.equals(u.getUserName()) && password.equals(u.getPassword())){
                return u;
            }
        }

        throw new UserNotFoundException();
    }
}
