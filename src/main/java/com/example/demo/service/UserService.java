package com.example.demo.service;


import com.example.demo.dao.UserDAO;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public Boolean changePass(String username, String oldPass, String newPass){
        User user = userDAO.findByUsername(username);
        if(oldPass.equals(user.getPassword())){
            user.setPassword(newPass);
            userDAO.save(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean isExist(String username) {
        User user = getByName(username);
        return null!=user;
    }

    public User getByName(String username) {
        return userDAO.findByUsername(username);
    }

    public User get(String username, String password){
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public void add(User user) {
        userDAO.save(user);
    }
}
