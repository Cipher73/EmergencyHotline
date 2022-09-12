package com.example.emergencyhotline;

public class UserClass {
    public  String email,id,name,password;
    public Boolean isAdmin;

    public String getName() {
        return name;
    }

    public UserClass() {
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getIsAdmin() {
        return isAdmin+"";
    }
}
