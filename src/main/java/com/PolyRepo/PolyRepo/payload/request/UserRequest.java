package com.PolyRepo.PolyRepo.payload.request;

public class UserRequest {
    private int id;
    private String name;
    private int roleId;
    private String email;
    private  String password;
    private String resettoken;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public String getResettoken() {
        return resettoken;
    }

    public void setResettoken(String resettoken) {
        this.resettoken = resettoken;
    }
}
