package com.example.controller;

import com.example.exceptions.UserServiceException;
import com.example.modelRequest.UpdateUserDetailsRequestModel;
import com.example.modelRequest.UserDetailsRequestModel;
import com.example.modelResponse.UserRest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    Map<String, UserRest> users;

    @GetMapping
    public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "limit", defaultValue = "10") int limit,
                           @RequestParam(value = "sort", defaultValue = "asc", required = false) String sort) {
        return String.format("Users are returned with page = %d , limit = %d and sort = %s", page, limit, sort);
    }

    @GetMapping(path = "/{userId}",
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) throws Exception {

        if(users==null || !users.containsKey(userId))throw new UserServiceException("User not found!");

        return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);


    }

    @PostMapping(path = "/",
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetailsRequestModel) {
        UserRest userRest = new UserRest();
        userRest.setEmail(userDetailsRequestModel.getEmail());
        userRest.setFirstName(userDetailsRequestModel.getFirstName());
        userRest.setLastName(userDetailsRequestModel.getLastName());
        String userId = UUID.randomUUID().toString();
        userRest.setUserId(userId);
        if (users == null) users = new HashMap<>();
        users.put(userId, userRest);
        return new ResponseEntity<>(userRest, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{userId}",
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<UserRest> updateUser(@PathVariable(name = "userId") String userId, @Valid @RequestBody UpdateUserDetailsRequestModel updateUserDetailsRequestModel) {
        if (!users.containsKey(userId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        UserRest userRest = users.get(userId);
        userRest.setFirstName(updateUserDetailsRequestModel.getFirstName());
        userRest.setLastName(updateUserDetailsRequestModel.getLastName());
        users.put(userId, userRest);
        return new ResponseEntity<>(userRest, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "userId") String userId) {
        if (!users.containsKey(userId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        users.remove(userId);
        return ResponseEntity.noContent().build();
    }

}
