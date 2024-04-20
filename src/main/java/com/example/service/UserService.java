package com.example.service;

import com.example.modelRequest.UserDetailsRequestModel;
import com.example.modelResponse.UserRest;

public interface UserService {
    UserRest createUser(UserDetailsRequestModel userDetailsRequestModel);
}
