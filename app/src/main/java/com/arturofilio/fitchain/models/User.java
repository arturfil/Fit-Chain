package com.arturofilio.fitchain.models;

public class User {

    private String Name;
    private String Password;
    private String username;
    private String isAdmin;

    public User() {

    }

    public User(String name, String password) {
        Name = name;
        Password = password;
        isAdmin = "false";
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }
}
