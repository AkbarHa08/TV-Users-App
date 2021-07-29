package com.model;

public class TvUsers {
    
    private Integer id;
    private String fullname;
    private String mobile;
    private String login;
    private String password;
    private String address;

    @Override
    public String toString() {
        return "TvUsers{" + "id=" + id + ", fullname=" + fullname + ", mobile=" + mobile + ", login=" + login + ", password=" + password + ", address=" + address + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public TvUsers() {
    }

    public TvUsers(Integer id, String fullname, String mobile, String login, String password, String address) {
        this.id = id;
        this.fullname = fullname;
        this.mobile = mobile;
        this.login = login;
        this.password = password;
        this.address = address;
    }
}
