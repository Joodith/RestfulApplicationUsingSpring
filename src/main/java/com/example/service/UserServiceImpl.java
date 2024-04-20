package com.example.service;

import com.example.modelRequest.UserDetailsRequestModel;
import com.example.modelResponse.UserRest;
import com.example.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    Utils utils;
    Map<String, UserRest> users;

    public UserServiceImpl() {

    }

    @Autowired
    public UserServiceImpl(Utils utils) {
        this.utils=utils;
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetailsRequestModel) {
        UserRest userRest = new UserRest();
        userRest.setEmail(userDetailsRequestModel.getEmail());
        userRest.setFirstName(userDetailsRequestModel.getFirstName());
        userRest.setLastName(userDetailsRequestModel.getLastName());
        String userId = utils.generateId();
        userRest.setUserId(userId);
        if (users == null) users = new HashMap<>();
        users.put(userId, userRest);
        return userRest;
    }
}
