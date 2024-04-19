package com.example.modelRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDetailsRequestModel {
    @NotNull(message="First Name cannot be null")
    @Size(min=2,message="First name must be atleast 2 characters long")
    private String firstName;

    @NotNull(message="Last Name cannot be null")
    @Size(min=2,message="Last name must be atleast 2 characters long")
    private String lastName;

    @NotNull(message="Email cannot be null")
    @Email
    private String email;

    @NotNull(message="Password cannot be null")
    @Size(min=8,max=16,message="Password must be atleast 8 characters long and not more than 16 charaters")
    private String password;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}